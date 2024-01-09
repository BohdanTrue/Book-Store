package mate.academy.bookstore.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;
import mate.academy.bookstore.dto.book.BookResponseDto;
import mate.academy.bookstore.mapper.BookMapper;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.repository.book.BookRepository;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @Test
    void getAll_withPageAble_ReturnOk() {
        Book book = new Book()
                .setId(1L)
                .setAuthor("Robert Martin")
                .setTitle("Clean Code");

        List<Book> books = List.of(book);

        BookResponseDto bookResponseDto = new BookResponseDto()
                .setId(book.getId())
                .setAuthor(book.getAuthor())
                .setTitle(book.getTitle());

        Pageable pageable = PageRequest.of(0, 5);
        Page<Book> page = new PageImpl<>(books, pageable, books.size());

        when(bookRepository.findAll(pageable)).thenReturn(page);
        when(bookMapper.toDto(book)).thenReturn(bookResponseDto);

        List<BookResponseDto> expected = List.of(bookResponseDto);
        List<BookResponseDto> actual = bookService.getAll(pageable);

        assertNotNull(actual);
        assertEquals(1, actual.size());
        EqualsBuilder.reflectionEquals(expected, actual);
    }
}
