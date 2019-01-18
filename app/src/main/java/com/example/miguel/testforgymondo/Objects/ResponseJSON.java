package com.example.miguel.testforgymondo.Objects;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseJSON<E> {
    @SerializedName("count")

    private Integer count;
    @SerializedName("next")

    private String next;
    @SerializedName("previous")

    private String previous;
    @SerializedName("results")

    private List<E> results;

    public ResponseJSON(Integer count, String next, String previous, List<E> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<E> getResults() {
        return results;
    }

    public void setResults(List<E> results) {
        this.results = results;
    }
}
