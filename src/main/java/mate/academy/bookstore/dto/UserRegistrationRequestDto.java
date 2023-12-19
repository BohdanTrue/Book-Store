package mate.academy.bookstore.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import mate.academy.bookstore.validation.FieldMatch;

@Data
@FieldMatch
public class UserRegistrationRequestDto {
    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String repeatPassword;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private String shippingAddress;
}
