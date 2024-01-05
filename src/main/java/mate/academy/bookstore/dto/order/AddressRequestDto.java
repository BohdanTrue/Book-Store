package mate.academy.bookstore.dto.order;

import jakarta.validation.constraints.NotBlank;

public record AddressRequestDto(
        @NotBlank(message = "Address cannot be blank")
        String shippingAddress) {
}
