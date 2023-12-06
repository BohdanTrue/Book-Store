package mate.academy.bookstore.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.bookstore.model.Book;
import mate.academy.bookstore.service.BookService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/books")
public class BookControllerImpl implements BookController {
    private final BookService bookService;

    @PostMapping
    public Book save(@RequestBody Book book) {
        return bookService.save(book);
    }

    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }
}
