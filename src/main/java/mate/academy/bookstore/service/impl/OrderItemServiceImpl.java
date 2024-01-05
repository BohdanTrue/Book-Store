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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private static final String CANNOT_FIND_ORDER_ITEM_BY_ITEM_ID
            = "Can't find order item by item id: ";
    private final OrderItemRepository orderItemRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItemResponseDto> getOrderItems(Long orderId, Pageable pageable) {
        List<OrderItem> orderItems = orderItemRepository.findAllByOrderId(orderId, pageable);

        return orderItems.stream()
                .map(orderItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OrderItemResponseDto getItemById(Long id, Long orderId) {
        OrderItem orderItem = orderItemRepository.findByIdAndOrderId(id, orderId)
                .orElseThrow(() -> new EntityNotFoundException(
                        CANNOT_FIND_ORDER_ITEM_BY_ITEM_ID + id));

        return orderItemMapper.toDto(orderItem);
    }
}
