package mate.academy.bookstore.dto.order;

import jakarta.validation.constraints.NotBlank;

public record AddressRequestDto(
        @NotBlank(message = "Address cannot be null")
        String shippingAddress) {
}
