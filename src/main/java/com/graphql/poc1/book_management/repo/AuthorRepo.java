package com.graphql.poc1.book_management.repo;

import com.graphql.poc1.book_management.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Integer> {

}
