package mate.academy.bookstore.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.order.OrderItemResponseDto;
import mate.academy.bookstore.exception.EntityNotFoundException;
import mate.academy.bookstore.mapper.OrderItemMapper;
import mate.academy.bookstore.model.OrderItem;
import mate.academy.bookstore.repository.orderitem.OrderItemRepository;
import mate.academy.bookstore.service.OrderItemService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private static final String CANNOT_FIND_ORDER_ITEMS_BY_ID
            = "Can't find all order items by id: ";
    private static final String CANNOT_FIND_ORDER_ITEM_BY_ITEM_ID
            = "Can't find order item by item id: ";
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItemResponseDto> getAllById(Long orderId) {
        List<OrderItem> orderItems = orderItemRepository
                .findAllByOrderId(orderId)
                .orElseThrow(() -> new EntityNotFoundException(
                        CANNOT_FIND_ORDER_ITEMS_BY_ID + orderId));

        return orderItems.stream()
                .map(orderItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemResponseDto getItemById(Long orderId, Long itemId) {
        OrderItem orderItem = orderItemRepository.findOrderItemByOrderIdAndItemId(orderId, itemId)
                .orElseThrow(() -> new EntityNotFoundException(
                        CANNOT_FIND_ORDER_ITEM_BY_ITEM_ID + itemId));

        return orderItemMapper.toDto(orderItem);
    }
}
