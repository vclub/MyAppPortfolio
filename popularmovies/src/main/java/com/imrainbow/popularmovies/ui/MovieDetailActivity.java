package com.imrainbow.popularmovies.ui;

import android.content.Intent;
import android.net.Uri;
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
public class MovieDetailActivity extends BackBaseActivity {


    private MovieEntity mCurrentMovie;
    private ShareActionProvider mShareActionProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        if (getIntent().hasExtra("movie")) {
            mCurrentMovie = getIntent().getParcelableExtra("movie");

            getSupportFragmentManager().beginTransaction().replace(R.id.container,
                    MovieDetailFragment.newInstance(mCurrentMovie)).commit();
        }

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setData(Uri.parse("http://youtu.be/v/dddd"));
        setShareIntent(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_detail, menu);

        MenuItem item = menu.findItem(R.id.menu_item_share);


        mShareActionProvider = (ShareActionProvider)MenuItemCompat.getActionProvider(item);

        return true;
    }

    private void setShareIntent(Intent shareIntent){
        if (mShareActionProvider != null){
            mShareActionProvider.setShareIntent(shareIntent);
        }
    }
}
