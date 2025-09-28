package com.graphql.poc1.book_management.service.impl;

import com.graphql.poc1.book_management.exception.AuthorNotFoundException;
import com.graphql.poc1.book_management.model.Author;
import com.graphql.poc1.book_management.repo.AuthorRepo;
import com.graphql.poc1.book_management.service.AuthorService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepo authorRepo;

    public AuthorServiceImpl(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public Author getAuthorById(Integer authorId) {
      return authorRepo.findById(authorId).orElseThrow(() ->new AuthorNotFoundException("Author not found with id: " + authorId));
    }

    @Override
    public Author createAuthor(Author author) {
        return authorRepo.save(author);
    }
}
