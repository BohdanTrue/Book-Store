package mate.academy.bookstore.service;

import java.util.List;
import mate.academy.bookstore.dto.BookDto;
import mate.academy.bookstore.dto.CreateBookRequestDto;

public interface BookService {
    BookDto save(CreateBookRequestDto bookRequestDto);

    List<BookDto> getAll();

    BookDto getBookById(Long id);

    BookDto update(Long id, CreateBookRequestDto bookRequestDto);

    void deleteById(Long id);
}
