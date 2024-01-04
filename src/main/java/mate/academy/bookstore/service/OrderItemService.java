package mate.academy.bookstore.service;

import java.util.List;
import mate.academy.bookstore.dto.order.OrderItemResponseDto;

public interface OrderItemService {
    List<OrderItemResponseDto> getAllById(Long orderId);

    OrderItemResponseDto getItemById(Long orderId, Long itemId);
}
