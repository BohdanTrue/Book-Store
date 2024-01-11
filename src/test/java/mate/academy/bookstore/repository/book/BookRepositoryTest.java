package mate.academy.bookstore.repository.book;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.bookstore.model.Book;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;
    private Book book;
    @BeforeEach
    void setUp() {
        book = new Book()
                .setId(1L)
                .setAuthor("Robert Martin")
                .setTitle("Clean Code")
                .setIsbn("9780307474278")
                .setPrice(new BigDecimal(1111))
                .setDescription("description");
    }

    @Test
    @DisplayName("""
            Find all books with valid category id
            """)
    @Sql(scripts = {
            "classpath:database/books/add-books.sql",
            "classpath:database/categories/add-categories.sql",
            "classpath:database/books_categories/add-books_categories.sql"
    }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = {
            "classpath:database/books_categories/delete-books_categories.sql",
            "classpath:database/categories/delete-categories.sql",
            "classpath:database/books/delete-books.sql"
    }, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void findAllBooks_WithValidCategoryId_ReturnOk() {
        List<Book> expected = List.of(book);
        List<Book> actual = bookRepository.findAllByCategoryId(1L);

        assertNotNull(actual);
        assertEquals(1, actual.size());
        EqualsBuilder.reflectionEquals(expected, actual);
    }
}
