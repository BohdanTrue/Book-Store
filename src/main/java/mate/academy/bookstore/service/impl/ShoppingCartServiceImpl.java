package mate.academy.bookstore.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.cart.AddQuantityDto;
import mate.academy.bookstore.dto.cart.CartItemRequestDto;
import mate.academy.bookstore.dto.cart.ShoppingCartResponseDto;
import mate.academy.bookstore.exception.EntityNotFoundException;
import mate.academy.bookstore.mapper.ShoppingCartMapper;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.model.CartItem;
import mate.academy.bookstore.model.ShoppingCart;
import mate.academy.bookstore.model.User;
import mate.academy.bookstore.repository.book.BookRepository;
import mate.academy.bookstore.repository.cartitem.CartItemRepository;
import mate.academy.bookstore.repository.shoppingcart.ShoppingCartRepository;
import mate.academy.bookstore.repository.user.UserRepository;
import mate.academy.bookstore.service.ShoppingCartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private static final String CANNOT_FIND_BOOK_BY_ID = "Can't find book by id: ";
    private static final String CANNOT_FIND_USER_BY_ID = "Can't find user by id: ";
    private static final String CANNOT_FIND_SHOPPING_CART_BY_USER_ID
            = "Can't find shopping cart by user id: ";
    private static final String CANNOT_FIND_CART_ITEM_BY_ID
            = "Can't find cart item by cart item by id: ";
    private final ShoppingCartRepository shoppingCartRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final ShoppingCartMapper shoppingCartMapper;

    @Override
    @Transactional
    public ShoppingCartResponseDto addBookToShoppingCart(
            CartItemRequestDto requestDto,
            Long userId) {
        Book book = bookRepository.findById(requestDto.getBookId())
                .orElseThrow(() -> new EntityNotFoundException(
                        CANNOT_FIND_BOOK_BY_ID + requestDto.getBookId()));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        CANNOT_FIND_USER_BY_ID + userId));
        ShoppingCart shoppingCartFromDb = shoppingCartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    ShoppingCart shoppingCart = new ShoppingCart();
                    shoppingCart.setUser(user);
                    shoppingCartRepository.save(shoppingCart);
                    return shoppingCart;
                });

        CartItem cartItem = new CartItem();
        cartItem.setQuantity(requestDto.getQuantity());
        cartItem.setBook(book);
        cartItem.setShoppingCart(shoppingCartFromDb);
        cartItemRepository.save(cartItem);

        return shoppingCartMapper.toDto(shoppingCartFromDb);
    }

    @Override
    public ShoppingCartResponseDto getShoppingCart(Long userId) {
        ShoppingCart shoppingCart = shoppingCartRepository.getShoppingCartByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        CANNOT_FIND_SHOPPING_CART_BY_USER_ID + userId));

        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    @Transactional
    public ShoppingCartResponseDto updateQuantity(
            Long cartItemId,
            AddQuantityDto requestDto,
            Long userId) {
        ShoppingCart shoppingCart = shoppingCartRepository.getShoppingCartByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        CANNOT_FIND_SHOPPING_CART_BY_USER_ID + userId));

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException(
                        CANNOT_FIND_CART_ITEM_BY_ID + cartItemId));

        cartItem.setQuantity(requestDto.getQuantity());
        cartItemRepository.save(cartItem);
        return shoppingCartMapper.toDto(shoppingCart);
    }

    @Override
    public ShoppingCartResponseDto removeBookFromShoppingCart(Long cartItemId, Long userId) {

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new EntityNotFoundException(
                        CANNOT_FIND_CART_ITEM_BY_ID + cartItemId));

        cartItemRepository.delete(cartItem);

        ShoppingCart shoppingCart = shoppingCartRepository.getShoppingCartByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        CANNOT_FIND_SHOPPING_CART_BY_USER_ID + userId));

        return shoppingCartMapper.toDto(shoppingCart);
    }
}
