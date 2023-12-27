package mate.academy.bookstore.service;

import java.util.List;
import mate.academy.bookstore.dto.book.BookDtoWithoutCategoryIds;
import mate.academy.bookstore.dto.book.BookRequestDto;
import mate.academy.bookstore.dto.book.BookResponseDto;
import mate.academy.bookstore.dto.book.BookSearchParametersDto;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookResponseDto save(BookRequestDto bookRequestDto);

    List<BookResponseDto> getAll(Pageable pageable);

    BookResponseDto getBookById(Long id);

    BookResponseDto update(Long id, BookRequestDto bookRequestDto);

    void deleteById(Long id);

    List<BookResponseDto> search(BookSearchParametersDto searchParameters);

    List<BookDtoWithoutCategoryIds> getBooksByCategoryId(Long id);
}
