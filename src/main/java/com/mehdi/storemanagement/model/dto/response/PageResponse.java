package com.mehdi.storemanagement.model.dto.response;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class PageResponse<T> implements Serializable {


    @Serial
    private static final long serialVersionUID = 7161934598739148744L;

    private List<T> content;
    private int currentPage;
    private long totalItems;
    private int totalPages;

    public PageResponse() {
    }

    public PageResponse(List<T> content, int currentPage, long totalItems, int totalPages) {
        this.content = content;
        this.currentPage = currentPage;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}

