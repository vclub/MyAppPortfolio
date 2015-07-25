package com.imrainbow.popularmovies.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.imrainbow.popularmovies.Config;
import com.imrainbow.popularmovies.R;
import com.imrainbow.popularmovies.api.MovieAPI;
import com.imrainbow.popularmovies.model.MovieInfo;
import com.imrainbow.popularmovies.ui.adapter.MainPosterAdapter;
import com.imrainbow.popularmovies.ui.base.BaseActivity;
import com.imrainbow.popularmovies.util.RestAdapterUtils;

import butterknife.Bind;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends BaseActivity {

    @Bind(R.id.rv_poster)
    RecyclerView rvPoster;

    private String currentSortBy = Config.SORT_BY_MOST_POPULAR;
    MainPosterAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupView();

        loadMoiveData();
    }

    private void setupView() {
        rvPoster.setLayoutManager(new GridLayoutManager(this, 2));
        if (mAdapter == null){
            mAdapter = new MainPosterAdapter(this);
        }
        rvPoster.setAdapter(mAdapter);
    }

    private void loadMoiveData(){
        MovieAPI movieAPI = RestAdapterUtils.getRestAPI(MovieAPI.class);

        movieAPI.getMoviesSortBy(currentSortBy, Config.THE_MOVIE_DB_API_KEY, new Callback<MovieInfo>() {
            @Override
            public void success(MovieInfo movieInfo, Response response) {
                if (movieInfo.getResults().size() > 0) {
                    mAdapter.setItems(movieInfo.getResults());
                }
            }

            @Override
            public void failure(RetrofitError error) {
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
            currentSortBy = Config.SORT_BY_HIGHEST_RATED;
            loadMoiveData();
            return true;
        }
        if (id == R.id.action_most_popular) {
            currentSortBy = Config.SORT_BY_MOST_POPULAR;
            loadMoiveData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
