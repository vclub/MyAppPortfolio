package com.imrainbow.popularmovies.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.imrainbow.popularmovies.Config;
import com.imrainbow.popularmovies.R;
import com.imrainbow.popularmovies.dao.GreenDaoHelper;
import com.imrainbow.popularmovies.dao.MovieEntity;
import com.imrainbow.popularmovies.model.MovieInfo;
import com.imrainbow.popularmovies.network.TheMovieDBApi;
import com.imrainbow.popularmovies.ui.adapter.MainPosterAdapter;
import com.imrainbow.popularmovies.ui.base.BaseActivity;
import com.imrainbow.popularmovies.ui.fragment.MovieDetailFragment;
import com.imrainbow.popularmovies.util.NetworkUtils;
import com.imrainbow.popularmovies.util.SharedPreferenceHelper;

import butterknife.Bind;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends BaseActivity implements MainPosterAdapter.OnItemClick {

    @Bind(R.id.rv_poster)
    RecyclerView rvPoster;

    @Bind(R.id.pb_loading)
    ProgressBar pbLoading;

    @Nullable
    @Bind(R.id.fl_movie_detail)
    FrameLayout flMovieDetail;

    private int default_spancount = 2;
    private MainPosterAdapter mAdapter;

    private SharedPreferenceHelper spfHelper;
    private MovieInfo currentMovieInfo;

    private MovieDetailFragment detailFragment;

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
            currentMovieInfo = savedInstanceState.getParcelable(Config.MOVIE_INFO_KEY);
            if (currentMovieInfo != null) {
                mAdapter.setItems(currentMovieInfo.getResults());
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(Config.MOVIE_INFO_KEY, currentMovieInfo);
        super.onSaveInstanceState(outState);
    }

    private void setupView() {
        if (flMovieDetail != null) {
            detailFragment = new MovieDetailFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_movie_detail, detailFragment).commit();
        }
        rvPoster.setLayoutManager(new GridLayoutManager(this, flMovieDetail != null ? 3 : default_spancount));
        mAdapter = new MainPosterAdapter(this);
        mAdapter.setOnItemClick(this);
        rvPoster.setAdapter(mAdapter);
    }

    private void loadMoiveData() {

        if (NetworkUtils.isNetworkAvailable(this)) {
            pbLoading.setVisibility(View.VISIBLE);
            TheMovieDBApi.getInstance()
                    .getTheMovieDBService()
                    .getMoviesSortBy(spfHelper.getValue(Config.SORT_VALUE_KEY),
                            Config.THE_MOVIE_DB_API_KEY,
                            new Callback<MovieInfo>() {
                                @Override
                                public void success(MovieInfo movieInfo, Response response) {
                                    pbLoading.setVisibility(View.GONE);
                                    if (movieInfo.getResults().size() > 0) {
                                        currentMovieInfo = movieInfo;
                                        mAdapter.setItems(movieInfo.getResults());

                                        if (flMovieDetail != null) {
                                            detailFragment.showMovie(movieInfo.getResults().get(0));
                                        }
                                    }
                                }

                                @Override
                                public void failure(RetrofitError error) {
                                    pbLoading.setVisibility(View.GONE);
                                    Log.e("test", "error", error);
                                }
                            });
        } else {
            Toast.makeText(this, getResources().getString(R.string.no_network_info), Toast.LENGTH_SHORT).show();
        }
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
            if (!spfHelper.getValue(Config.SORT_VALUE_KEY).equals(Config.SORT_BY_HIGHEST_RATED)) {
                spfHelper.setValue(Config.SORT_VALUE_KEY, Config.SORT_BY_HIGHEST_RATED);
                loadMoiveData();
                return true;
            }
        }
        if (id == R.id.action_most_popular) {
            if (!spfHelper.getValue(Config.SORT_VALUE_KEY).equals(Config.SORT_BY_MOST_POPULAR)) {
                spfHelper.setValue(Config.SORT_VALUE_KEY, Config.SORT_BY_MOST_POPULAR);
                loadMoiveData();
                return true;
            }
        }
        if (id == R.id.action_my_favorite) {
            if (!spfHelper.getValue(Config.SORT_VALUE_KEY).equals(Config.MY_FAVORITE)) {
                spfHelper.setValue(Config.SORT_VALUE_KEY, Config.MY_FAVORITE);
                loadMyFavorite();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadMyFavorite() {
        mAdapter.setItems(GreenDaoHelper.getInstance(this).getMovieEntityDao().loadAll());
    }

    @Override
    public void onItemClick(MovieEntity movie) {
        if (flMovieDetail != null) {
            detailFragment.showMovie(movie);
        } else {
            startActivity(new Intent(this, MovieDetailActivity.class)
                    .putExtra("movie", movie));
        }
    }
}
