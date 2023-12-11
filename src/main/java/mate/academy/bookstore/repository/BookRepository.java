package mate.academy.bookstore.repository;

import java.util.List;
import java.util.Optional;
import mate.academy.bookstore.model.Book;

public interface BookRepository {
    Book save(Book book);

    List<Book> getAll();

    Optional<Book> getBookById(Long id);
}
