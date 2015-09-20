package com.imrainbow.popularmovies.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.imrainbow.popularmovies.R;
import com.imrainbow.popularmovies.dao.MovieEntity;
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

        final MovieEntity info = mItems.get(position);

        holder.moviePoster.setImageURI(Uri.parse("http://image.tmdb.org/t/p/w185" + info.getPoster_path()));
        holder.moviePoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClick != null)
                    onItemClick.onItemClick(info);
            }
        });
    }

    public class MainPosterViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_movie_poster)
        SimpleDraweeView moviePoster;

        public MainPosterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick{
        void onItemClick(MovieEntity movie);
    }

}
