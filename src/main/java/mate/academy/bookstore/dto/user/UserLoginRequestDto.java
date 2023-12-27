package mate.academy.bookstore.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserLoginRequestDto {
    @Email
    @NotNull(message = "can't be null")
    private String email;

    @NotBlank(message = "can't be empty or null")
    @Size(min = 8, max = 30)
    private String password;
}
