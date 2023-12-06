package mate.academy.bookstore.mapper;

import mate.academy.bookstore.dto.BookDto;
import mate.academy.bookstore.dto.CreateBookRequestDto;
import mate.academy.bookstore.model.Book;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class BookMapperImpl implements BookMapper {
    @Override
    public BookDto toDto(Book book) {
        BookDto dto = new BookDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        System.out.println("MAPPER (toDTO): bookTitle = " + book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setPrice(book.getPrice());
        dto.setDescription(book.getDescription());
        dto.setCoverImage(book.getCoverImage());

        return dto;
    }

    @Override
    public Book toModel(CreateBookRequestDto requestDto) {
        Book book = new Book();
        book.setTitle(requestDto.getTitle());
        book.setPrice(requestDto.getPrice());
        book.setAuthor(requestDto.getAuthor());
        book.setCoverImage(requestDto.getCoverImage());
        book.setDescription(requestDto.getDescription());

        return book;
    }
}
