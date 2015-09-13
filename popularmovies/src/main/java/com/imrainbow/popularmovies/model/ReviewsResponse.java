package com.imrainbow.popularmovies.model;

import java.util.List;

/**
 * Created by Bin Li on 2015/9/13.
 */
public class ReviewsResponse {

    /**
     * id : 76341
     * page : 1
     * results : [{"id":"55660928c3a3687ad7001db1","author":"Phileas Fogg","content":"Fabulous action movie. Lots of interesting characters. They don't make many movies like this. The whole movie from start to finish was entertaining I'm looking forward to seeing it again. I definitely recommend seeing it.","url":"http://j.mp/1HLTNzT"},{"id":"55732a53925141456e000639","author":"Andres Gomez","content":"Good action movie with a decent script for the genre. The photography is really good too but, in the end, it is quite repeating itself from beginning to end and the stormy OST is exhausting.","url":"http://j.mp/1dUnvpG"},{"id":"55edd26792514106d600e380","author":"extoix","content":"Awesome movie!  WITNESS ME will stick with me forever!","url":"http://j.mp/1hQIOdj"}]
     * total_pages : 1
     * total_results : 3
     */

    private int id;
    private int page;
    private int total_pages;
    private int total_results;
    private List<ReviewEntity> results;

    public void setId(int id) {
        this.id = id;
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

    public void setResults(List<ReviewEntity> results) {
        this.results = results;
    }

    public int getId() {
        return id;
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

    public List<ReviewEntity> getResults() {
        return results;
    }

}
