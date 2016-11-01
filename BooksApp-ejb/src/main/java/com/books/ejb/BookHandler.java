package com.books.ejb;

import com.books.ejb.model.Book;

import javax.ejb.Local;
import java.util.Collection;

@Local
public interface BookHandler {

    Collection<Book> getAll();

    Book addBook(Book book);

    Book getBook(Long id);

    void deleteBook(Long id);

    Book update(Book book);
}
