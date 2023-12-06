package mate.academy.bookstore.controller;

import mate.academy.bookstore.model.Book;

import java.util.List;

public interface BookController {
    Book save(Book book);

    List<Book> findAll();
}
