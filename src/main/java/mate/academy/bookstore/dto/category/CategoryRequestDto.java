package mate.academy.bookstore.dto.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryRequestDto {
    @NotBlank(message = "can't be empty or null")
    @Size(min = 3, max = 20)
    private String name;

    @NotBlank(message = "can't be empty or null")
    @Size(min = 3, max = 50)
    private String description;
}
