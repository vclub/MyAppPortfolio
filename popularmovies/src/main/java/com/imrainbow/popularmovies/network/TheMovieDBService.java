package com.imrainbow.popularmovies.network;

import com.imrainbow.popularmovies.model.MovieInfo;
import com.imrainbow.popularmovies.model.ReviewsResponse;
import com.imrainbow.popularmovies.model.VideosResonse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by vclub on 15/7/25.
 */
public interface TheMovieDBService {

    @GET("/discover/movie")
    void getMoviesSortBy(@Query("sort_by") String sort_by,
                          @Query("api_key") String api_key,
                          Callback<MovieInfo> callback);

    @GET("/movie/{id}/videos")
    void getMovieTrailers(@Path("id") long movieId,
                          @Query("api_key") String api_key,
                          Callback<VideosResonse> callback);

    @GET("/movie/{id}/reviews")
    void getMovieReviews(@Path("id") long movieId,
                          @Query("api_key") String api_key,
                          Callback<ReviewsResponse> callback);
}
