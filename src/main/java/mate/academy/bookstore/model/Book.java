package mate.academy.bookstore.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    @ToString.Exclude
    @Column(unique = true)
    private String isbn;

    private BigDecimal price;

    private String description;

    private String coverImage;
}
