package mate.academy.bookstore.dto.order;

import jakarta.validation.constraints.NotNull;

public record AddressRequestDto(
        @NotNull(message = "Address cannot be null")
        String shippingAddress) {
}
