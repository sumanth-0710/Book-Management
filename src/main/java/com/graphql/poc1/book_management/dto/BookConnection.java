package com.graphql.poc1.book_management.dto;

import java.util.List;

public class BookConnection {
    private List<BookEdge> edges;
    private PageInfo pageInfo;

    public BookConnection(List<BookEdge> edges, PageInfo pageInfo) {
        this.edges = edges;
        this.pageInfo = pageInfo;
    }

    public List<BookEdge> getEdges() { return edges; }
    public PageInfo getPageInfo() { return pageInfo; }
}
