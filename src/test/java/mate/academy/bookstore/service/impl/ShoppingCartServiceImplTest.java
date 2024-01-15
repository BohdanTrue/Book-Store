package mate.academy.bookstore.service.impl;

import static org.mockito.Mockito.when;

import java.util.Optional;
import mate.academy.bookstore.dto.cart.ShoppingCartResponseDto;
import mate.academy.bookstore.mapper.ShoppingCartMapper;
import mate.academy.bookstore.model.ShoppingCart;
import mate.academy.bookstore.model.User;
import mate.academy.bookstore.repository.shoppingcart.ShoppingCartRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceImplTest {
    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @Mock
    private ShoppingCartMapper shoppingCartMapper;

    @InjectMocks
    private ShoppingCartServiceImpl shoppingCartService;
    private User user;
    private ShoppingCart shoppingCart;
    private ShoppingCartResponseDto responseDto;

    @BeforeEach
    void setUp() {
        user = new User()
                .setId(2L)
                .setEmail("bohdan@gmail.com")
                .setPassword("$2a$10$2P9C9iZmpeNBNt2qrNKHcO7mxE/DcDV62TVvHa1OZpa1Ha3Hzi1Va")
                .setFirstName("Bohdan")
                .setLastName("Bilko")
                .setShippingAddress("John McCain 32");

        shoppingCart = new ShoppingCart()
                .setId(1L)
                .setUser(user);

        responseDto = new ShoppingCartResponseDto()
                .setId(shoppingCart.getId())
                .setUserId(user.getId());
    }

    @Test
    void getShoppingCart_WithValidId_Success() {
        when(shoppingCartRepository.getShoppingCartByUserId(user.getId()))
                .thenReturn(Optional.of(shoppingCart));
        when(shoppingCartMapper.toDto(shoppingCart))
                .thenReturn(responseDto);

        ShoppingCartResponseDto actual = shoppingCartService.getShoppingCart(user.getId());

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(responseDto, actual);
    }
}
