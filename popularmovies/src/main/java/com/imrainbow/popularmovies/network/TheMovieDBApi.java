package com.imrainbow.popularmovies.network;

import com.imrainbow.popularmovies.BuildConfig;
import com.imrainbow.popularmovies.Config;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Bin Li on 2015/9/13.
 */
public class TheMovieDBApi {

    private static TheMovieDBApi instance;
    private TheMovieDBService theMovieDBService;

    public static TheMovieDBApi getInstance() {
        if (instance == null) {
            instance = new TheMovieDBApi();
        }
        return instance;
    }

    private TheMovieDBApi() {
        RestAdapter restAdapter = buildRestAdapter();
        this.theMovieDBService = restAdapter.create(TheMovieDBService.class);
    }

    private RestAdapter buildRestAdapter() {
        RestAdapter.LogLevel level;

        if (BuildConfig.DEBUG) {
            level = RestAdapter.LogLevel.FULL;
        } else {
            level = RestAdapter.LogLevel.NONE;
        }

        return new RestAdapter.Builder()
                .setLogLevel(level)
                .setEndpoint(Config.THE_MOVIE_DB_BASE_URL)
                .setClient(new OkClient())
                .build();
    }

    public TheMovieDBService getTheMovieDBService() {
        return theMovieDBService;
    }
}
