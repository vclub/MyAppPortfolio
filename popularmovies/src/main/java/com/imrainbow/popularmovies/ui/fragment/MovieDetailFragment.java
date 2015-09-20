package com.imrainbow.popularmovies.ui.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.imrainbow.popularmovies.Config;
import com.imrainbow.popularmovies.R;
import com.imrainbow.popularmovies.dao.GreenDaoHelper;
import com.imrainbow.popularmovies.dao.MovieEntity;
import com.imrainbow.popularmovies.model.ReviewEntity;
import com.imrainbow.popularmovies.model.ReviewsResponse;
import com.imrainbow.popularmovies.model.VideoEntity;
import com.imrainbow.popularmovies.model.VideosResonse;
import com.imrainbow.popularmovies.network.TheMovieDBApi;
import com.imrainbow.popularmovies.ui.base.BaseFragment;
import com.imrainbow.popularmovies.util.DensityUtils;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Bin Li on 2015/9/13.
 */
public class MovieDetailFragment extends BaseFragment {


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

    @Bind(R.id.trailers_layout)
    LinearLayout trailerLayout;

    @Bind(R.id.reviews_layout)
    LinearLayout reviewLayout;

    private MovieEntity mCurrentMovie;

    public static MovieDetailFragment newInstance(MovieEntity movie) {

        Bundle args = new Bundle();
        args.putParcelable("movie", movie);
        MovieDetailFragment fragment = new MovieDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_movie_detail;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState == null) {

            if (getActivity().getIntent().hasExtra("movie")) {
                mCurrentMovie = getActivity().getIntent().getParcelableExtra("movie");
                showMovie(mCurrentMovie);

            }
        } else {
            mCurrentMovie = savedInstanceState.getParcelable("movieis");
            if (mCurrentMovie != null) {
                showMovie(mCurrentMovie);
            }
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

        loadVideos(mCurrentMovie.getId());
        loadReviews(mCurrentMovie.getId());
    }

    private void loadVideos(long movieId) {
        TheMovieDBApi.getInstance().getTheMovieDBService().getMovieTrailers(movieId,
                Config.THE_MOVIE_DB_API_KEY,
                new Callback<VideosResonse>() {
                    @Override
                    public void success(VideosResonse videosResonse, Response response) {
                        if (videosResonse.getResults().size() > 0) {

                            for (VideoEntity video : videosResonse.getResults()) {
                                TextView trailer = new TextView(getActivity());
                                trailer.setCompoundDrawablesWithIntrinsicBounds(
                                        getResources().getDrawable(android.R.drawable.ic_media_play),
                                        null, null, null);
                                trailer.setGravity(Gravity.CENTER_VERTICAL);
                                trailer.setText(video.getName());
                                final String key = video.getKey();
                                trailer.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent view = new Intent();
                                        view.setAction(Intent.ACTION_VIEW);
                                        view.setData(Uri.parse("https://youtu.be/" + key));
                                        startActivity(view);
                                    }
                                });
                                trailerLayout.addView(trailer,
                                        new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                DensityUtils.dp2px(getActivity(), 38)));
                                Log.e("test", video.toString());
                            }

                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
    }

    private void loadReviews(long movieId) {
        TheMovieDBApi.getInstance().getTheMovieDBService().getMovieReviews(movieId,
                Config.THE_MOVIE_DB_API_KEY,
                new Callback<ReviewsResponse>() {
                    @Override
                    public void success(ReviewsResponse reviewsResponse, Response response) {
                        if (reviewsResponse.getResults().size() > 0) {
                            for (ReviewEntity reviewEntity : reviewsResponse.getResults()) {
                                TextView reviewAuthor = new TextView(getActivity());
                                reviewAuthor.setText(reviewEntity.getAuthor());
                                reviewAuthor.setCompoundDrawablesWithIntrinsicBounds(
                                        getResources().getDrawable(android.R.drawable.sym_def_app_icon),
                                        null, null, null);
                                reviewAuthor.setGravity(Gravity.CENTER_VERTICAL);
                                reviewLayout.addView(reviewAuthor,
                                        new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                ViewGroup.LayoutParams.WRAP_CONTENT));


                                TextView review = new TextView(getActivity());
                                review.setText(reviewEntity.getContent());
                                reviewLayout.addView(review,
                                        new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                                ViewGroup.LayoutParams.WRAP_CONTENT));
                            }
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
    }

    @OnClick(R.id.btn_my_favorite)
    void onBtnFavoriteClick() {
        if (mCurrentMovie != null) {
            MovieEntity favorite = new MovieEntity();
            favorite.setId(mCurrentMovie.getId());
            favorite.setTitle(mCurrentMovie.getTitle());
            favorite.setRelease_date(mCurrentMovie.getRelease_date());
            favorite.setVote_average(mCurrentMovie.getVote_average());
            favorite.setPoster_path(mCurrentMovie.getPoster_path());
            favorite.setOverview(mCurrentMovie.getOverview());
            GreenDaoHelper.getInstance(getActivity().getApplicationContext())
                    .getMovieEntityDao()
                    .insertOrReplace(favorite);
        }
    }
}
