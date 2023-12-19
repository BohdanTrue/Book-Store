package mate.academy.bookstore.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Data;
import org.hibernate.validator.constraints.ISBN;

@Data
public class CreateBookRequestDto {
    @NotNull(message = "can't be null")
    @Size(min = 3, max = 30)
    private String title;
    @NotNull(message = "can't be null")
    @Size(min = 3, max = 30)
    private String author;
    @ISBN(message = "Invalid ISBN format")
    private String isbn;
    @Positive(message = "must be more than 0")
    @NotNull(message = "can't be null")
    private BigDecimal price;
    private String description;
    private String coverImage;
}
