package com.imrainbow.popularmovies.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;

import com.imrainbow.popularmovies.R;
import com.imrainbow.popularmovies.dao.MovieEntity;
import com.imrainbow.popularmovies.ui.base.BackBaseActivity;
import com.imrainbow.popularmovies.ui.fragment.MovieDetailFragment;

/**
 * Created by Bin Li on 2015/8/9.
 */
public class MovieDetailActivity extends BackBaseActivity implements MovieDetailFragment.MovieUrlCallback{


    private MovieEntity mCurrentMovie;
    private ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        if (getIntent().hasExtra("movie")) {
            mCurrentMovie = getIntent().getParcelableExtra("movie");

            getSupportFragmentManager().beginTransaction().replace(R.id.container,
                    MovieDetailFragment.newInstance(mCurrentMovie, this)).commit();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_movie_detail, menu);

        MenuItem item = menu.findItem(R.id.menu_item_share);

        mShareActionProvider = new ShareActionProvider(this);
        MenuItemCompat.setActionProvider(item, mShareActionProvider);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void updateMovieTrailerUrl(String videoUrl) {
        if (mShareActionProvider != null){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, videoUrl);
            mShareActionProvider.setShareIntent(intent);
        }
    }
}
