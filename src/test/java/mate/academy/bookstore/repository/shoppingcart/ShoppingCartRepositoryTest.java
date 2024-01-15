package mate.academy.bookstore.repository.shoppingcart;

import static org.junit.Assert.assertTrue;

import java.util.Optional;
import mate.academy.bookstore.model.ShoppingCart;
import mate.academy.bookstore.model.User;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ShoppingCartRepositoryTest {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    private User user;
    private ShoppingCart shoppingCart;
    @BeforeEach
    void setUp() {
        user = new User()
                .setId(2L)
                .setEmail("bohdan@gmail.com")
                .setPassword("$2a$10$2P9C9iZmpeNBNt2qrNKHcO7mxE/DcDV62TVvHa1OZpa1Ha3Hzi1Va")
                .setFirstName("Bohdan")
                .setLastName("Bilko")
                .setShippingAddress("Kyiv");

        shoppingCart = new ShoppingCart()
                .setId(2L)
                .setUser(user);
    }

    @Test
    @DisplayName("""
             Get shopping cart with valid user id
            """)
    @Sql(scripts = {
            "classpath:database/books/add-books.sql",
            "classpath:database/users/add-user.sql",
            "classpath:database/shopping_carts/add-shopping-carts.sql",
            "classpath:database/cart_items/add-cart-item.sql"
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/cart_items/delete-cart-items.sql",
            "classpath:database/shopping_carts/delete-shopping-carts.sql",
            "classpath:database/users/delete-users.sql",
            "classpath:database/books/delete-books.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void getShoppingCartByUserId_WithValidUserId_Success() {

        ShoppingCart expected = shoppingCart;

        Optional<ShoppingCart> actual = shoppingCartRepository.getShoppingCartByUserId(2L);

        assertTrue(actual.isPresent());
        EqualsBuilder.reflectionEquals(expected, actual.orElse(null), "id");
    }
}
