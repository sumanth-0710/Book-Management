package com.graphql.poc1.book_management.controller;

import com.graphql.poc1.book_management.dto.BookConnection;
import com.graphql.poc1.book_management.model.Author;
import com.graphql.poc1.book_management.model.Book;
import com.graphql.poc1.book_management.model.BookInput;
import com.graphql.poc1.book_management.service.AuthorService;
import com.graphql.poc1.book_management.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @QueryMapping("getAllBooks")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @QueryMapping("getBookById")
    public Book getBookById(@Argument("bookId") Integer bookId) {
        return bookService.getBookById(bookId);
    }

    @QueryMapping("getAllBooksByPages")
    public BookConnection getAllBooks(@Argument int first, @Argument String after) {
        return bookService.getBooks(first, after);
    }

    @MutationMapping("addBook")
    public Book addBook(@Argument("book") BookInput bookInput) {
        Book book = new Book();
        book.setTitle(bookInput.getTitle());
        book.setPublishedYear(bookInput.getPublishedYear());
        return bookService.createBook(book, bookInput.getAuthorId());
    }


    @MutationMapping("updateBook")
    public Book updateBook(@Argument("bookId") Integer bookId, @Argument("book") BookInput bookInput) {
        Book book = new Book();
        book.setTitle(bookInput.getTitle());
        book.setPublishedYear(bookInput.getPublishedYear());
        return bookService.updateBookById(bookId, book, bookInput.getAuthorId());
    }

    @MutationMapping("deleteBook")
    public String deleteBook(@Argument("bookId") Integer bookId) {
        return bookService.deleteBookById(bookId);
    }

    @SchemaMapping(typeName = "Book", field = "author")
    public Author getAuthor(Book book) {
        return authorService.getAuthorById(book.getAuthor().getAuthorId());
    }
}
