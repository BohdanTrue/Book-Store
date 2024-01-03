package mate.academy.bookstore.dto.cart;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddQuantityDto {
    @NotNull
    private int quantity;
}
