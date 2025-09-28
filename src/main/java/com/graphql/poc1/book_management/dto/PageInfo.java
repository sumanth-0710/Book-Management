package com.graphql.poc1.book_management.dto;

public class PageInfo {
    private String endCursor;
    private boolean hasNextPage;

    public PageInfo(String endCursor, boolean hasNextPage) {
        this.endCursor = endCursor;
        this.hasNextPage = hasNextPage;
    }

    public String getEndCursor() { return endCursor; }
    public boolean isHasNextPage() { return hasNextPage; }
}