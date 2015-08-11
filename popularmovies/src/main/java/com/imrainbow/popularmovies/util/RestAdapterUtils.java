package com.imrainbow.popularmovies.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.imrainbow.popularmovies.BuildConfig;
import com.imrainbow.popularmovies.Config;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by vclub on 15/7/25.
 */
public class RestAdapterUtils {

    private static final String TAG = RestAdapterUtils.class.getSimpleName();

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

    public static <T> T getRestAPI(Class<T> service, final Context ctx) {

        RestAdapter.LogLevel level;

        if (BuildConfig.DEBUG) {
            level = RestAdapter.LogLevel.FULL;
        } else {
            level = RestAdapter.LogLevel.NONE;
        }

        OkHttpClient okHttpClient = new OkHttpClient();
        try{
            int cacheSize = 10 * 1024 * 1024;
            Cache cache = new Cache(ctx.getCacheDir(), cacheSize);
            okHttpClient.setCache(cache);
        }catch (Exception ex){
            Log.e(TAG, "cache error", ex);
        }


        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(level)
                .setEndpoint(Config.THE_MOVIE_DB_BASE_URL)
                .setClient(new OkClient(okHttpClient))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        if (NetworkUtils.isNetworkAvailable(ctx)) {
                            int maxAge = 600; // read from cache for 10 minute
                            request.addHeader("Cache-Control", "public, max-age=" + maxAge);
                        } else {
                            int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                            request.addHeader("Cache-Control",
                                    "public, only-if-cached, max-stale=" + maxStale);

                            Toast.makeText(ctx, "Please check your network!", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .build();

        return restAdapter.create(service);
    }
}
