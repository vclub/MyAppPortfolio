package com.imrainbow.popularmovies.model;

import java.util.List;

/**
 * Created by vclub on 15/7/24.
 */
public class MovieInfo {

    private List<MovieEntity> results;
    private int page;
    private int total_pages;
    private int total_results;

    public void setResults(List<MovieEntity> results) {
        this.results = results;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public List<MovieEntity> getResults() {
        return results;
    }

    public int getPage() {
        return page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

}
