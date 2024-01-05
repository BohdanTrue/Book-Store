package mate.academy.bookstore.repository.shoppingcart;

import java.util.Optional;
import mate.academy.bookstore.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    @Query("SELECT sc FROM ShoppingCart sc JOIN FETCH sc.user u "
            + "JOIN FETCH sc.cartItems ci JOIN FETCH ci.book b WHERE u.id = :userId")
    Optional<ShoppingCart> getShoppingCartByUserId(Long userId);

    Optional<ShoppingCart> findByUserId(Long userId);
}
