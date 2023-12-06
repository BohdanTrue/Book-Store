package mate.academy.bookstore.mapper;

import mate.academy.bookstore.dto.BookDto;
import mate.academy.bookstore.dto.CreateBookRequestDto;
import mate.academy.bookstore.model.Book;

public interface BookMapper {
    BookDto toDto(Book book);
    Book toModel(CreateBookRequestDto requestDto);
}
