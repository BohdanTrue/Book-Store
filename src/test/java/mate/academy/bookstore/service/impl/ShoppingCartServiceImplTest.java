package mate.academy.bookstore.service.impl;

import static org.mockito.Mockito.when;

import java.util.Optional;
import mate.academy.bookstore.dto.cart.ShoppingCartResponseDto;
import mate.academy.bookstore.mapper.ShoppingCartMapper;
import mate.academy.bookstore.model.ShoppingCart;
import mate.academy.bookstore.model.User;
import mate.academy.bookstore.repository.shoppingcart.ShoppingCartRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceImplTest {
    @InjectMocks
    private ShoppingCartServiceImpl shoppingCartService;

    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @Mock
    private ShoppingCartMapper shoppingCartMapper;

    @Test
    void getShoppingCart_WithValidId_Success() {
        User user = new User();
        user.setId(2L);
        user.setEmail("bohdan@gmail.com");
        user.setPassword("$2a$10$2P9C9iZmpeNBNt2qrNKHcO7mxE/DcDV62TVvHa1OZpa1Ha3Hzi1Va");
        user.setFirstName("Bohdan");
        user.setLastName("Bilko");
        user.setShippingAddress("John McCain 32");

        ShoppingCart shoppingCart
                = new ShoppingCart()
                .setId(1L)
                .setUser(user);

        ShoppingCartResponseDto responseDto
                = new ShoppingCartResponseDto()
                .setId(shoppingCart.getId())
                .setUserId(user.getId());

        when(shoppingCartRepository.getShoppingCartByUserId(user.getId()))
                .thenReturn(Optional.of(shoppingCart));
        when(shoppingCartMapper.toDto(shoppingCart))
                .thenReturn(responseDto);

        ShoppingCartResponseDto actual = shoppingCartService.getShoppingCart(user.getId());

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(responseDto, actual);
    }
}
