package com.volunteeride.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by ayazlakdawala on 12/15/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PagedResponse<T extends Object> {

    private T[] content;
    private int totalElements;
    private int totalPages;
    private boolean last;
    private boolean first;
    private int size;
    private int number;
    private int numberOfElements;

    public T[] getContent() {
        return content;
    }

    public void setContent(T[] content) {
        this.content = content;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }
}
