package mate.academy.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
//@JsonIgnoreProperties(ignoreUnknown = false)
public class CreateBookRequestDto {
    private String title;
    private String author;
    private BigDecimal price;
    private String description;
    private String coverImage;
}
