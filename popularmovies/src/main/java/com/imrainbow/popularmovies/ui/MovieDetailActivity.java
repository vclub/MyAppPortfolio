package com.imrainbow.popularmovies.ui;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.imrainbow.popularmovies.R;
import com.imrainbow.popularmovies.model.MovieEntity;
import com.imrainbow.popularmovies.ui.base.BackBaseActivity;

import butterknife.Bind;

/**
 * Created by Bin Li on 2015/8/9.
 */
public class MovieDetailActivity extends BackBaseActivity {

    @Bind(R.id.tv_movie_title)
    TextView tvMovieTitle;
    @Bind(R.id.iv_movie_poster)
    SimpleDraweeView ivMoviePoster;
    @Bind(R.id.tv_release_date)
    TextView tvReleaseDate;
    @Bind(R.id.tv_movie_vote)
    TextView tvMovieVote;
    @Bind(R.id.tv_plot_synopsis)
    TextView tvPlotSynopsis;

    private MovieEntity mCurrentMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        if (getIntent().hasExtra("movie")) {
            mCurrentMovie = getIntent().getParcelableExtra("movie");
            initView();
        }
    }

    private void initView() {
        tvMovieTitle.setText(mCurrentMovie.getTitle());
        tvReleaseDate.setText(mCurrentMovie.getRelease_date());
        tvMovieVote.setText(mCurrentMovie.getVote_average() + "/10");
        tvPlotSynopsis.setText(mCurrentMovie.getOverview());
        ivMoviePoster.setImageURI(Uri.parse("http://image.tmdb.org/t/p/w185" + mCurrentMovie.getPoster_path()));


    }
}
