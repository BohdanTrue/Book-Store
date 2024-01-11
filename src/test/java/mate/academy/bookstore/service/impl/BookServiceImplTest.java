package mate.academy.bookstore.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;
import mate.academy.bookstore.dto.book.BookResponseDto;
import mate.academy.bookstore.mapper.BookMapper;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.repository.book.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    @Mock
    private BookRepository bookRepository;
    @Mock
    private BookMapper bookMapper;
    @InjectMocks
    private BookServiceImpl bookService;
    private Book book;
    private BookResponseDto bookResponseDto;

    @BeforeEach
    void setUp() {
        book = new Book()
                .setId(1L)
                .setAuthor("Robert Martin")
                .setTitle("Clean Code");

        bookResponseDto = new BookResponseDto()
                .setId(book.getId())
                .setAuthor(book.getAuthor())
                .setTitle(book.getTitle());
    }

    @DisplayName("Get all books with pageable")
    @Test
    void getAll_withPageable_ReturnOk() {
        List<Book> books = List.of(book);

        Pageable pageable = PageRequest.of(0, 5);
        Page<Book> page = new PageImpl<>(books, pageable, books.size());

        when(bookRepository.findAll(pageable)).thenReturn(page);
        when(bookMapper.toDto(book)).thenReturn(bookResponseDto);

        List<BookResponseDto> expected = List.of(bookResponseDto);
        List<BookResponseDto> actual = bookService.getAll(pageable);

        assertNotNull(actual);
        assertEquals(1, actual.size());
        assertEquals(expected, actual);
    }
}
