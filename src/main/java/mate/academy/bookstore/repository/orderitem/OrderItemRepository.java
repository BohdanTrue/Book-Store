package mate.academy.bookstore.repository.orderitem;

import java.util.List;
import java.util.Optional;
import mate.academy.bookstore.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    Optional<List<OrderItem>> findAllByOrderId(Long id);

    @Query("SELECT oi FROM OrderItem oi "
            + "JOIN FETCH oi.order o "
            + "JOIN FETCH oi.book b "
            + "WHERE oi.id = :itemId AND o.id = :orderId")
    Optional<OrderItem> findOrderItemByOrderIdAndItemId(Long orderId, Long itemId);
}
