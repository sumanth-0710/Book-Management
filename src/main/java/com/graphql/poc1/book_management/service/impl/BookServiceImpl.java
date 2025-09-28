package com.graphql.poc1.book_management.service.impl;

import com.graphql.poc1.book_management.dto.BookConnection;
import com.graphql.poc1.book_management.dto.BookEdge;
import com.graphql.poc1.book_management.dto.PageInfo;
import com.graphql.poc1.book_management.exception.BookNotFoundException;
import com.graphql.poc1.book_management.model.Author;
import com.graphql.poc1.book_management.model.Book;
import com.graphql.poc1.book_management.repo.BookRepo;
import com.graphql.poc1.book_management.service.BookService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;
    private final AuthorServiceImpl authorService;

    public BookServiceImpl(BookRepo bookRepo, AuthorServiceImpl authorService) {
        this.bookRepo = bookRepo;
        this.authorService = authorService;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    @Override
    public Book getBookById(Integer bookId) {
        return bookRepo.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book not found with id: " + bookId));
    }

    @Override
    public Book createBook(Book book, Integer authorId) {
        Author author = authorService.getAuthorById(authorId);
        book.setAuthor(author);
        return bookRepo.save(book);
    }

    @Override
    public Book updateBookById(Integer bookId, Book book,Integer authorId) {
        Author author = authorService.getAuthorById(authorId);
        return bookRepo.findById(bookId)
                .map(existingBook -> {
                    existingBook.setTitle(book.getTitle());
                    existingBook.setAuthor(book.getAuthor());
                    existingBook.setPublishedYear(book.getPublishedYear());
                    existingBook.setAuthor(author);
                    return bookRepo.save(existingBook);
                }).orElseThrow(() -> new BookNotFoundException("Book not found with id: " + bookId));
    }

    @Override
    public String deleteBookById(Integer bookId) {
        if (bookRepo.existsById(bookId)) {
            bookRepo.deleteById(bookId);
            return "Book deleted with id: " + bookId;
        }
        return "Book not found with id: " + bookId;
    }

    @Override
    public BookConnection getBooks(int first, String afterCursor) {
        Integer afterId = (afterCursor != null) ? Integer.parseInt(afterCursor) : 0;

        List<Book> books = bookRepo.findByBookIdGreaterThanOrderByBookIdAsc(afterId, PageRequest.of(0, first + 1));

        boolean hasNextPage = books.size() > first;
        List<Book> limitedBooks = hasNextPage ? books.subList(0, first) : books;

        String endCursor = limitedBooks.isEmpty() ? null
                : String.valueOf(limitedBooks.get(limitedBooks.size() - 1).getBookId());

        List<BookEdge> edges = limitedBooks.stream()
                .map(book -> new BookEdge(book, String.valueOf(book.getBookId())))
                .toList();

        PageInfo pageInfo = new PageInfo(endCursor, hasNextPage);

        return new BookConnection(edges, pageInfo);
    }

    @Override
    public BookConnection getBooksByAuthor(Integer authorId, int first, String afterCursor) {
        Integer afterId = (afterCursor != null) ? Integer.parseInt(afterCursor) : 0;

        List<Book> books = bookRepo.findByAuthorAuthorIdAndBookIdGreaterThanOrderByBookIdAsc(
                authorId, afterId, PageRequest.of(0, first + 1)
        );

        boolean hasNextPage = books.size() > first;
        List<Book> limitedBooks = hasNextPage ? books.subList(0, first) : books;

        String endCursor = limitedBooks.isEmpty() ? null
                : String.valueOf(limitedBooks.get(limitedBooks.size() - 1).getBookId());

        List<BookEdge> edges = limitedBooks.stream()
                .map(book -> new BookEdge(book, String.valueOf(book.getBookId())))
                .toList();

        return new BookConnection(edges, new PageInfo(endCursor, hasNextPage));
    }


}
