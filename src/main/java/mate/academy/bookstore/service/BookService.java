package mate.academy.bookstore.service;

import mate.academy.bookstore.dto.BookDto;
import mate.academy.bookstore.dto.CreateBookRequestDto;
import mate.academy.bookstore.model.Book;

import java.util.List;

public interface BookService {
    BookDto save(CreateBookRequestDto book);

    List<BookDto> findAll();
}
