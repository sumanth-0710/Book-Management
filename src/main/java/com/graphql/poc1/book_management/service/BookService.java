package com.graphql.poc1.book_management.service;

import com.graphql.poc1.book_management.dto.BookConnection;
import com.graphql.poc1.book_management.model.Book;

import java.util.List;

public interface BookService {

    //create book
    Book createBook(Book book, Integer authorId);

    //Get all books
    List<Book> getAllBooks();

    //Get book by id
    Book getBookById(Integer id);

    //Update book by id
    Book updateBookById(Integer id, Book book, Integer authorId);

    //Delete book by id
    String deleteBookById(Integer id);

    //Paginated fetch
    BookConnection getBooks(int first, String afterCursor);

    BookConnection getBooksByAuthor(Integer authorId, int first, String afterCursor);

}
