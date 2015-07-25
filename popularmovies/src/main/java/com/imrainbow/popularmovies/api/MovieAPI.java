package com.imrainbow.popularmovies.api;

import com.imrainbow.popularmovies.model.MovieInfo;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by vclub on 15/7/25.
 */
public interface MovieAPI {

    @GET("/movie")
    void getMoviesSortBy(@Query("sort_by") String sort_by,
                          @Query("api_key") String api_key,
                          Callback<MovieInfo> callback);
}
