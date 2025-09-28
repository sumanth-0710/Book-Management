package com.graphql.poc1.book_management.dto;

import com.graphql.poc1.book_management.model.Book;

public class BookEdge {
    private Book node;
    private String cursor;

    public BookEdge(Book node, String cursor) {
        this.node = node;
        this.cursor = cursor;
    }

    public Book getNode() { return node; }
    public String getCursor() { return cursor; }
}
