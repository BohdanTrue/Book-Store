package mate.academy.bookstore.service;

import java.util.List;
import mate.academy.bookstore.dto.order.OrderItemResponseDto;
import org.springframework.data.domain.Pageable;

public interface OrderItemService {
    List<OrderItemResponseDto> getOrderItems(Long orderId, Pageable pageable);

    OrderItemResponseDto getItemById(Long id, Long orderId);
}
