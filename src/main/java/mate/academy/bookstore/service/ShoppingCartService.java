package mate.academy.bookstore.service;

import mate.academy.bookstore.dto.cart.AddQuantityDto;
import mate.academy.bookstore.dto.cart.CartItemRequestDto;
import mate.academy.bookstore.dto.cart.ShoppingCartResponseDto;

public interface ShoppingCartService {
    ShoppingCartResponseDto addBookToShoppingCart(CartItemRequestDto requestDto, Long userId);

    ShoppingCartResponseDto removeBookFromShoppingCart(Long cartItemId, Long userId);

    ShoppingCartResponseDto getShoppingCart(Long userId);

    ShoppingCartResponseDto updateQuantity(Long cartItemId, AddQuantityDto requestDto, Long userId);
}
