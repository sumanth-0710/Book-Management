package com.graphql.poc1.book_management.service;

import com.graphql.poc1.book_management.model.Author;
import org.springframework.stereotype.Service;

@Service
public interface AuthorService {
    Author getAuthorById(Integer authorId);
    Author createAuthor(Author author);
}
