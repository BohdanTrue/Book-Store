package mate.academy.bookstore.repository.book;

import static org.junit.Assert.assertEquals;

import java.util.List;
import mate.academy.bookstore.model.Book;
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
        List<Book> actual = bookRepository.findAllByCategoryId(1L);

        assertEquals(1, actual.size());
        assertEquals("Robert Martin", actual.get(0).getAuthor());
    }
}
