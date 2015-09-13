package com.imrainbow.popularmovies.model;

import java.util.List;

/**
 * Created by Bin Li on 2015/9/13.
 */
public class VideosResonse {

    /**
     * id : 76341
     * results : [{"id":"559198cac3a3685710000b58","iso_639_1":"en","key":"FRDdRto_3SA","name":"Trailers From Hell","site":"YouTube","size":1080,"type":"Featurette"},{"id":"551afc679251417fd70002b1","iso_639_1":"en","key":"jnsgdqppAYA","name":"Trailer 2","site":"YouTube","size":720,"type":"Trailer"},{"id":"548ce4e292514122ed002d99","iso_639_1":"en","key":"YWNWi-ZWL3c","name":"Official Trailer #1","site":"YouTube","size":1080,"type":"Trailer"}]
     */

    private int id;
    private List<VideoEntity> results;

    public void setId(int id) {
        this.id = id;
    }

    public void setResults(List<VideoEntity> results) {
        this.results = results;
    }

    public int getId() {
        return id;
    }

    public List<VideoEntity> getResults() {
        return results;
    }


}
