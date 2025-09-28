package com.graphql.poc1.book_management.controller;

import com.graphql.poc1.book_management.dto.BookConnection;
import com.graphql.poc1.book_management.model.Author;
import com.graphql.poc1.book_management.service.AuthorService;
import com.graphql.poc1.book_management.service.BookService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AuthorController {

    private final AuthorService authorService;
    private final BookService bookService;

    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @MutationMapping("addAuthor")
    public Author addAuthor(@Argument("author") Author authorInput) {
        return authorService.createAuthor(authorInput);
    }

    // Nested field mapping for Author -> books
    @SchemaMapping(typeName = "Author", field = "books")
    public BookConnection getBooks(Author author,
                                   @Argument int first,
                                   @Argument String after) {
        return bookService.getBooksByAuthor(author.getAuthorId(), first, after);
    }
}
