package mate.academy.bookstore.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.dto.BookDto;
import mate.academy.bookstore.dto.BookSearchParametersDto;
import mate.academy.bookstore.dto.CreateBookRequestDto;
import mate.academy.bookstore.exception.EntityNotFoundException;
import mate.academy.bookstore.mapper.BookMapper;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.repository.book.BookRepository;
import mate.academy.bookstore.repository.book.BookSpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private static final String CANNOT_GET_BOOK_BY_ID_EXCEPTION = "Can't get book by id: ";
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder bookSpecificationBuilder;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> getAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(CANNOT_GET_BOOK_BY_ID_EXCEPTION + id)
        );
        return bookMapper.toDto(book);
    }

    @Override
    public BookDto update(Long id, CreateBookRequestDto bookRequestDto) {
        Optional<Book> bookById = bookRepository.findById(id);
        if (bookById.isPresent()) {
            Book updatedBook = new Book();
            updatedBook.setId(id);
            updatedBook.setAuthor(bookRequestDto.getAuthor());
            updatedBook.setTitle(bookRequestDto.getTitle());
            updatedBook.setPrice(bookRequestDto.getPrice());
            updatedBook.setIsbn(bookRequestDto.getIsbn());
            updatedBook.setDescription(bookRequestDto.getDescription());
            updatedBook.setCoverImage(bookRequestDto.getCoverImage());
            return bookMapper.toDto(bookRepository.save(updatedBook));
        }
        return bookMapper.toDto(bookRepository.save(bookMapper.toModel(bookRequestDto)));
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public List<BookDto> searchBooks(BookSearchParametersDto searchParameters) {
        Specification<Book> bookSpecification = bookSpecificationBuilder.build(searchParameters);
        return bookRepository.findAll(bookSpecification).stream()
                .map(bookMapper::toDto)
                .toList();
    }
}
