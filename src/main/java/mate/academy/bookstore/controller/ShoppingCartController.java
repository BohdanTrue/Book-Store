package mate.academy.bookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.cart.AddQuantityDto;
import mate.academy.bookstore.dto.cart.CartItemRequestDto;
import mate.academy.bookstore.dto.cart.ShoppingCartResponseDto;
import mate.academy.bookstore.model.User;
import mate.academy.bookstore.service.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(
            summary = "Add a book to shopping cart",
            description = "Add a book to the shopping cart.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ShoppingCartResponseDto addBookToShoppingCart(
            @Valid @RequestBody CartItemRequestDto requestDto,
            Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.addBookToShoppingCart(requestDto, user.getId());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(
            summary = "Get shopping cart",
            description = "Get shopping cart from database")
    @GetMapping
    public ShoppingCartResponseDto getShoppingCart(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.getShoppingCart(user.getId());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Update a book quantity",
            description = "Update a book quantity by cart item id")
    @PutMapping("/cart-items/{cartItemId}")
    public ShoppingCartResponseDto updateBookQuantity(
            @PathVariable Long cartItemId,
            @RequestBody @Valid AddQuantityDto requestDto,
            Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.updateQuantity(cartItemId, requestDto, user.getId());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(summary = "Remove a book",
            description = "Remove a book from the shopping cart")
    @DeleteMapping("/cart-items/{cartItemId}")
    public ShoppingCartResponseDto removeBookFromShoppingCart(
            @PathVariable Long cartItemId,
            Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return shoppingCartService.removeBookFromShoppingCart(cartItemId, user.getId());
    }
}
