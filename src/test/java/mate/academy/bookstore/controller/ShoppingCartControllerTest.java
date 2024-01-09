//package mate.academy.bookstore.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.SneakyThrows;
//import mate.academy.bookstore.dto.cart.ShoppingCartResponseDto;
//import mate.academy.bookstore.model.User;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.jdbc.datasource.init.ScriptUtils;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.SQLException;
//
//import static org.springframework.security.test
// .web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
//class ShoppingCartControllerTest {
//    protected static MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @BeforeAll
//    static void beforeAll(
//            @Autowired DataSource dataSource,
//            @Autowired WebApplicationContext applicationContext
//    ) throws SQLException {
//        teardown(dataSource);
//        mockMvc = MockMvcBuilders
//                .webAppContextSetup(applicationContext)
//                .apply(springSecurity())
//                .build();
//        try (Connection connection = dataSource.getConnection()) {
//            connection.setAutoCommit(true);
//            ScriptUtils.executeSqlScript(
//                    connection,
//                    new ClassPathResource("database/users/add-user.sql")
//            );
//            ScriptUtils.executeSqlScript(
//                    connection,
//                    new ClassPathResource("database/books/add-books.sql")
//            );
//            ScriptUtils.executeSqlScript(
//                    connection,
//                    new ClassPathResource("database/categories/add-categories.sql")
//            );
//            ScriptUtils.executeSqlScript(
//                    connection,
//                    new ClassPathResource("database/books_categories/add-books_categories.sql")
//            );
//            ScriptUtils.executeSqlScript(
//                    connection,
//                    new ClassPathResource("database/shopping_carts/add-shopping-carts.sql")
//            );
//            ScriptUtils.executeSqlScript(
//                    connection,
//                    new ClassPathResource("database/cart_items/add-cart-item.sql")
//            );
//        }
//    }
//
//    @AfterAll
//    static void afterAll(
//            @Autowired DataSource dataSource
//    ) {
//        teardown(dataSource);
//    }
//
//    @SneakyThrows
//    static void teardown(DataSource dataSource) {
//        try (Connection connection = dataSource.getConnection()) {
//            connection.setAutoCommit(true);
//            ScriptUtils.executeSqlScript(
//                    connection,
//                    new ClassPathResource("database/cart_items/delete-cart-items.sql")
//            );
//            ScriptUtils.executeSqlScript(
//                    connection,
//                    new ClassPathResource("database/shopping_carts/delete-shopping-carts.sql")
//            );
//            ScriptUtils.executeSqlScript(
//                    connection,
//                    new ClassPathResource("database/books_categories/delete-books_categories.sql")
//            );
//            ScriptUtils.executeSqlScript(
//                    connection,
//                    new ClassPathResource("database/categories/delete-categories.sql")
//            );
//            ScriptUtils.executeSqlScript(
//                    connection,
//                    new ClassPathResource("database/books/delete-books.sql")
//            );
//            ScriptUtils.executeSqlScript(
//                    connection,
//                    new ClassPathResource("database/users/delete-users.sql")
//            );
//        }
//    }
//
//    @Test
//    @DisplayName("Get shopping cart with valid user")
//    @WithMockUser(username = "user", roles = "USER")
//    void getShoppingCart_WithValidUser_Success() throws Exception {
//        User user = new User().setId(2L);
//
//        MvcResult result = mockMvc.perform(
//                        get("/cart")
//                )
//                .andExpect(status().isOk())
//                .andReturn();
//
//        ShoppingCartResponseDto actual = objectMapper.readValue(
//                result.getResponse().getContentAsString(),
//                ShoppingCartResponseDto.class
//        );
//    }
//}
