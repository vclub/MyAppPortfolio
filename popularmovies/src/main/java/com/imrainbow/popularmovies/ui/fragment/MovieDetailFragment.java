package com.imrainbow.popularmovies.ui.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.imrainbow.popularmovies.R;
import com.imrainbow.popularmovies.dao.Favorite;
import com.imrainbow.popularmovies.dao.GreenDaoHelper;
import com.imrainbow.popularmovies.model.MovieEntity;
import com.imrainbow.popularmovies.ui.base.BaseFragment;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Bin Li on 2015/9/13.
 */
public class MovieDetailFragment extends BaseFragment{



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
    protected int getLayoutRes() {
        return R.layout.fragment_movie_detail;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState ==null) {

            if (getActivity().getIntent().hasExtra("movie")) {
                mCurrentMovie = getActivity().getIntent().getParcelableExtra("movie");
                showMovie(mCurrentMovie);
            }
        }else {
            mCurrentMovie = savedInstanceState.getParcelable("movieis");
            if (mCurrentMovie != null)
                showMovie(mCurrentMovie);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("movieis", mCurrentMovie);
        super.onSaveInstanceState(outState);
    }

    public void showMovie(MovieEntity movie) {

        tvMovieTitle.setText(movie.getTitle());
        tvReleaseDate.setText(movie.getRelease_date());
        tvMovieVote.setText(movie.getVote_average() + "/10");
        tvPlotSynopsis.setText(movie.getOverview());

        ivMoviePoster.setImageURI(Uri.parse("http://image.tmdb.org/t/p/w185" + movie.getPoster_path()));

        mCurrentMovie = movie;
    }

    @OnClick(R.id.btn_my_favorite)
    void onBtnFavoriteClick(){
        if (mCurrentMovie != null){
            Favorite favorite = new Favorite();
            favorite.setId(mCurrentMovie.getId());
            favorite.setTitle(mCurrentMovie.getTitle());
            favorite.setRelease_date(mCurrentMovie.getRelease_date());
            favorite.setVote_average(mCurrentMovie.getVote_average());
            favorite.setPoster_path(mCurrentMovie.getPoster_path());
            favorite.setOverview(mCurrentMovie.getOverview());
            GreenDaoHelper.getInstance(getActivity().getApplicationContext()).getFavoriteDao().insertOrReplace(favorite);
        }
    }
}
