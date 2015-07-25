package com.imrainbow.popularmovies.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.imrainbow.popularmovies.R;
import com.imrainbow.popularmovies.model.MovieEntity;
import com.imrainbow.popularmovies.ui.base.BaseRecyclerAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vclub on 15/7/24.
 */
public class MainPosterAdapter extends BaseRecyclerAdapter<MovieEntity> {


    public MainPosterAdapter(Context ctx) {
        super(ctx);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        return new MainPosterViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_main_poster, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        MainPosterViewHolder holder = (MainPosterViewHolder) viewHolder;

        MovieEntity info = mItems.get(position);


        info.getPoster_path();
        Glide.with(mContext)
                .load("http://image.tmdb.org/t/p/w185" + info.getPoster_path())
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(holder.moviePoster);
    }

    public class MainPosterViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_movie_poster)
        ImageView moviePoster;

        public MainPosterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
