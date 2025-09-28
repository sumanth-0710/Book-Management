package com.graphql.poc1.book_management.repo;

import com.graphql.poc1.book_management.model.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {

    List<Book> findByBookIdGreaterThanOrderByBookIdAsc(Integer bookId, Pageable pageable);
    List<Book> findByAuthorAuthorIdAndBookIdGreaterThanOrderByBookIdAsc(
            Integer authorId,
            Integer afterId,
            Pageable pageable
    );
}
