package com.imrainbow.popularmovies.model;

/**
 * Created by Bin Li on 2015/9/13.
 */
public class ReviewEntity {
    /**
     * id : 55660928c3a3687ad7001db1
     * author : Phileas Fogg
     * content : Fabulous action movie. Lots of interesting characters. They don't make many movies like this. The whole movie from start to finish was entertaining I'm looking forward to seeing it again. I definitely recommend seeing it.
     * url : http://j.mp/1HLTNzT
     */

    private String id;
    private String author;
    private String content;
    private String url;

    public void setId(String id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }
}
