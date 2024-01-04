package mate.academy.bookstore.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.order.AddressRequestDto;
import mate.academy.bookstore.dto.order.OrderItemResponseDto;
import mate.academy.bookstore.dto.order.OrderResponseDto;
import mate.academy.bookstore.dto.order.OrderStatusRequestDto;
import mate.academy.bookstore.model.User;
import mate.academy.bookstore.service.OrderItemService;
import mate.academy.bookstore.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @PostMapping
    public OrderResponseDto placeOrder(
            @RequestBody AddressRequestDto requestDto,
            Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return orderService.placeOrder(requestDto, user.getId());
    }

    @GetMapping
    public List<OrderResponseDto> getUserOrders(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return orderService.getUserOrders(user.getId());
    }

    @PatchMapping("/{id}")
    public OrderResponseDto updateOrderStatus(
            @PathVariable Long id,
            @RequestBody OrderStatusRequestDto requestDto) {
        return orderService.updateOrderStatus(id, requestDto);
    }

    @GetMapping("/{order-id}/items")
    public List<OrderItemResponseDto> getAllById(
            @PathVariable(name = "order-id") Long orderId) {
        return orderItemService.getAllById(orderId);
    }

    @GetMapping("/{order-id}/items/{item-id}")
    public OrderItemResponseDto getItemById(
            @PathVariable(name = "order-id") Long orderId,
            @PathVariable(name = "item-id") Long itemId) {
        return orderItemService.getItemById(orderId, itemId);
    }
}
