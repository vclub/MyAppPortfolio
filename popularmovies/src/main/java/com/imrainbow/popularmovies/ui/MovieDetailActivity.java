package com.imrainbow.popularmovies.ui;

import android.os.Bundle;

import com.imrainbow.popularmovies.R;
import com.imrainbow.popularmovies.dao.MovieEntity;
import com.imrainbow.popularmovies.ui.base.BackBaseActivity;
import com.imrainbow.popularmovies.ui.fragment.MovieDetailFragment;

/**
 * Created by Bin Li on 2015/8/9.
 */
public class MovieDetailActivity extends BackBaseActivity {


    private MovieEntity mCurrentMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        if (getIntent().hasExtra("movie")) {
            mCurrentMovie = getIntent().getParcelableExtra("movie");

            getSupportFragmentManager().beginTransaction().replace(R.id.container,
                    MovieDetailFragment.newInstance(mCurrentMovie)).commit();
        }
    }


}
