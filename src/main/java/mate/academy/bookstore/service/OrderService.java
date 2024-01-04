package mate.academy.bookstore.service;

import java.util.List;
import mate.academy.bookstore.dto.order.AddressRequestDto;
import mate.academy.bookstore.dto.order.OrderResponseDto;
import mate.academy.bookstore.dto.order.OrderStatusRequestDto;

public interface OrderService {
    OrderResponseDto placeOrder(AddressRequestDto requestDto, Long userId);

    List<OrderResponseDto> getUserOrders(Long userId);

    OrderResponseDto updateOrderStatus(Long orderId, OrderStatusRequestDto requestDto);
}
