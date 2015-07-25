package com.imrainbow.popularmovies.util;

import com.imrainbow.popularmovies.BuildConfig;
import com.imrainbow.popularmovies.Config;

import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by vclub on 15/7/25.
 */
public class RestAdapterUtils {

    public static <T> T getRestAPI(Class<T> service) {

        RestAdapter.LogLevel level;

        if (BuildConfig.DEBUG){
            level = RestAdapter.LogLevel.FULL;
        }else {
            level = RestAdapter.LogLevel.NONE;
        }

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(level)
                .setEndpoint(Config.THE_MOVIE_DB_BASE_URL)
                .setClient(new OkClient())
                .build();

        return restAdapter.create(service);
    }
}
