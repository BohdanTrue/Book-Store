package mate.academy.bookstore.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import mate.academy.bookstore.validation.FieldMatch;

@Data
@FieldMatch
public class UserRegistrationRequestDto {
    @Email
    @NotNull(message = "can't be null")
    private String email;

    @NotBlank(message = "can't be empty")
    @NotNull(message = "can't be null")
    @Size(min = 8, max = 30)
    private String password;

    @NotBlank(message = "can't be empty")
    @NotNull(message = "can't be null")
    @Size(min = 8, max = 30)
    private String repeatPassword;

    @NotBlank(message = "can't be empty")
    @NotNull(message = "can't be null")
    @Size(min = 3, max = 30)
    private String firstName;

    @NotBlank(message = "can't be empty")
    @NotNull(message = "can't be null")
    @Size(min = 3, max = 30)
    private String lastName;

    private String shippingAddress;
}
