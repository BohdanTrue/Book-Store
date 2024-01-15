package mate.academy.bookstore.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;
import javax.sql.DataSource;
import lombok.SneakyThrows;
import mate.academy.bookstore.dto.cart.AddQuantityDto;
import mate.academy.bookstore.dto.cart.CartItemResponseDto;
import mate.academy.bookstore.dto.cart.ShoppingCartResponseDto;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.model.Role;
import mate.academy.bookstore.model.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class ShoppingCartControllerTest {
    protected static MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;
    private User user;
    private Role userRole;
    private Book book;
    private CartItemResponseDto updatedCartItemResponseDto;

    @BeforeEach
    void setUp() {
        userRole = new Role().setRole(Role.RoleName.ROLE_USER);

        user = new User()
                .setId(2L)
                .setEmail("bohdan@gmail.com")
                .setPassword("$2a$10$2P9C9iZmpeNBNt2qrNKHcO7mxE/DcDV62TVvHa1OZpa1Ha3Hzi1Va")
                .setFirstName("Bohdan")
                .setLastName("Bilko")
                .setShippingAddress("Kyiv")
                .setRoles(Set.of(userRole));

        book = new Book()
                .setId(1L)
                .setAuthor("Robert Martin")
                .setTitle("Clean Code")
                .setIsbn("9780307474278")
                .setPrice(new BigDecimal("1111"))
                .setDescription("description");

        updatedCartItemResponseDto = new CartItemResponseDto()
                .setBookId(book.getId())
                .setBookTitle(book.getTitle())
                .setQuantity(20);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                        user, user.getPassword(), user.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @BeforeAll
    static void beforeAll(
            @Autowired DataSource dataSource,
            @Autowired WebApplicationContext applicationContext
    ) throws SQLException {
        teardown(dataSource);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .apply(springSecurity())
                .build();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(true);
            ScriptUtils.executeSqlScript(
                    connection,
                    new ClassPathResource("database/users/add-user.sql")
            );
            ScriptUtils.executeSqlScript(
                    connection,
                    new ClassPathResource("database/users_roles/add-role-for-user.sql")
            );
            ScriptUtils.executeSqlScript(
                    connection,
                    new ClassPathResource("database/books/add-books.sql")
            );
            ScriptUtils.executeSqlScript(
                    connection,
                    new ClassPathResource("database/categories/add-categories.sql")
            );
            ScriptUtils.executeSqlScript(
                    connection,
                    new ClassPathResource("database/books_categories/add-books_categories.sql")
            );
            ScriptUtils.executeSqlScript(
                    connection,
                    new ClassPathResource("database/shopping_carts/add-shopping-carts.sql")
            );
            ScriptUtils.executeSqlScript(
                    connection,
                    new ClassPathResource("database/cart_items/add-cart-item.sql")
            );
        }
    }

    @AfterAll
    static void afterAll(
            @Autowired DataSource dataSource
    ) {
        teardown(dataSource);
    }

    @SneakyThrows
    static void teardown(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(true);
            ScriptUtils.executeSqlScript(
                    connection,
                    new ClassPathResource("database/cart_items/delete-cart-items.sql")
            );
            ScriptUtils.executeSqlScript(
                    connection,
                    new ClassPathResource("database/shopping_carts/delete-shopping-carts.sql")
            );
            ScriptUtils.executeSqlScript(
                    connection,
                    new ClassPathResource("database/books_categories/delete-books_categories.sql")
            );
            ScriptUtils.executeSqlScript(
                    connection,
                    new ClassPathResource("database/categories/delete-categories.sql")
            );
            ScriptUtils.executeSqlScript(
                    connection,
                    new ClassPathResource("database/books/delete-books.sql")
            );
            ScriptUtils.executeSqlScript(
                    connection,
                    new ClassPathResource("database/users_roles/delete-role-for-user.sql")
            );
            ScriptUtils.executeSqlScript(
                    connection,
                    new ClassPathResource("database/users/delete-users.sql")
            );
        }
    }

    @Test
    @DisplayName("Update quantity")
    @WithMockUser(username = "user", roles = {"USER"})
    void updateCartItemQuantity_Success() throws Exception {
        Long cartItemId = 1L;
        AddQuantityDto itemRequestDto = new AddQuantityDto().setQuantity(20);

        MvcResult result = mockMvc.perform(
                        put("/cart/cart-items/{cartItemId}", cartItemId)
                                .content(objectMapper.writeValueAsString(itemRequestDto))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andReturn();

        ShoppingCartResponseDto expected = new ShoppingCartResponseDto()
                .setId(1L)
                .setUserId(user.getId())
                .setCartItems(Set.of(updatedCartItemResponseDto));

        ShoppingCartResponseDto actual = objectMapper.readValue(
                result.getResponse().getContentAsByteArray(),
                ShoppingCartResponseDto.class
        );

        EqualsBuilder.reflectionEquals(expected, actual, "id");
    }
}
