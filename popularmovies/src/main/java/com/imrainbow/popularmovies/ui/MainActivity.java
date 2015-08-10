package com.imrainbow.popularmovies.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.imrainbow.popularmovies.Config;
import com.imrainbow.popularmovies.R;
import com.imrainbow.popularmovies.api.MovieAPI;
import com.imrainbow.popularmovies.model.MovieInfo;
import com.imrainbow.popularmovies.ui.adapter.MainPosterAdapter;
import com.imrainbow.popularmovies.ui.base.BaseActivity;
import com.imrainbow.popularmovies.util.RestAdapterUtils;
import com.imrainbow.popularmovies.util.SharedPreferenceHelper;

import butterknife.Bind;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends BaseActivity {

    @Bind(R.id.rv_poster)
    RecyclerView rvPoster;

    @Bind(R.id.pb_loading)
    ProgressBar pbLoading;

    private MainPosterAdapter mAdapter;

    private SharedPreferenceHelper spfHelper;
    private MovieInfo currentMovieInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spfHelper = SharedPreferenceHelper.getInstance(this);
        if (TextUtils.isEmpty(spfHelper.getValue(Config.SORT_VALUE_KEY))) {
            spfHelper.setValue(Config.SORT_VALUE_KEY, Config.SORT_BY_MOST_POPULAR);
        }

        setupView();

        if (savedInstanceState == null) {
            loadMoiveData();
        } else {
            currentMovieInfo = savedInstanceState.getParcelable("movie_info");
            if (currentMovieInfo != null)
                mAdapter.setItems(currentMovieInfo.getResults());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("movie_info", currentMovieInfo);
        super.onSaveInstanceState(outState);
    }

    private void setupView() {
        rvPoster.setLayoutManager(new GridLayoutManager(this, 2));
        mAdapter = new MainPosterAdapter(this);
        rvPoster.setAdapter(mAdapter);
    }

    private void loadMoiveData() {
        pbLoading.setVisibility(View.VISIBLE);
        MovieAPI movieAPI = RestAdapterUtils.getRestAPI(MovieAPI.class);
        movieAPI.getMoviesSortBy(spfHelper.getValue(Config.SORT_VALUE_KEY), Config.THE_MOVIE_DB_API_KEY, new Callback<MovieInfo>() {
            @Override
            public void success(MovieInfo movieInfo, Response response) {
                pbLoading.setVisibility(View.GONE);
                if (movieInfo.getResults().size() > 0) {
                    currentMovieInfo = movieInfo;
                    mAdapter.setItems(movieInfo.getResults());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                pbLoading.setVisibility(View.GONE);
                Log.e("test", "error", error);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_highest_rated) {
            spfHelper.setValue(Config.SORT_VALUE_KEY, Config.SORT_BY_HIGHEST_RATED);
            loadMoiveData();
            return true;
        }
        if (id == R.id.action_most_popular) {
            spfHelper.setValue(Config.SORT_VALUE_KEY, Config.SORT_BY_MOST_POPULAR);
            loadMoiveData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
