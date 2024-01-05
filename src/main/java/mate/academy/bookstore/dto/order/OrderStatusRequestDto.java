package mate.academy.bookstore.dto.order;

import lombok.Data;
import mate.academy.bookstore.model.Order;

@Data
public class OrderStatusRequestDto {
    private Order.Status status;
}
