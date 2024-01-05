package mate.academy.bookstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.order.AddressRequestDto;
import mate.academy.bookstore.dto.order.OrderItemResponseDto;
import mate.academy.bookstore.dto.order.OrderResponseDto;
import mate.academy.bookstore.dto.order.OrderStatusRequestDto;
import mate.academy.bookstore.model.User;
import mate.academy.bookstore.service.OrderItemService;
import mate.academy.bookstore.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Orders management", description = "Endpoints for managing orders")
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(
            summary = "Place order",
            description = "Add a book to the shopping cart.")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrderResponseDto placeOrder(
            @RequestBody @Valid AddressRequestDto requestDto,
            Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return orderService.placeOrder(requestDto, user.getId());
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(
            summary = "Get orders",
            description = "Get user orders")
    @GetMapping
    public List<OrderResponseDto> getUserOrders(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return orderService.getUserOrders(user.getId());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
            summary = "Update order status",
            description = "Update order status")
    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping("/{id}")
    public OrderResponseDto updateOrderStatus(
            @PathVariable Long id,
            @RequestBody OrderStatusRequestDto requestDto) {
        return orderService.updateOrderStatus(id, requestDto);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(
            summary = "Get all order items by order id",
            description = "Get all order items by order id")
    @GetMapping("/{order-id}/items")
    public List<OrderItemResponseDto> getAllById(
            @PathVariable(name = "order-id") Long orderId) {
        return orderItemService.getAllById(orderId);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @Operation(
            summary = "Get order item by item id",
            description = "Get order item by item id")
    @GetMapping("/{order-id}/items/{item-id}")
    public OrderItemResponseDto getItemById(
            @PathVariable(name = "order-id") Long orderId,
            @PathVariable(name = "item-id") Long itemId) {
        return orderItemService.getItemById(itemId, orderId);
    }
}
