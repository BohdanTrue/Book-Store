package mate.academy.bookstore.repository.order;

import java.util.List;
import java.util.Optional;
import mate.academy.bookstore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o "
            + "JOIN FETCH o.orderItems oi "
            + "JOIN FETCH o.user u "
            + "JOIN FETCH u.roles r "
            + "WHERE u.id = :userId")
    Optional<List<Order>> findOrdersByUserId(Long userId);

    @Query("SELECT o FROM Order o "
            + "JOIN FETCH o.orderItems oi "
            + "JOIN FETCH o.user u "
            + "JOIN FETCH u.roles r "
            + "WHERE o.id = :orderId")
    Order findOrderById(Long orderId);
}
