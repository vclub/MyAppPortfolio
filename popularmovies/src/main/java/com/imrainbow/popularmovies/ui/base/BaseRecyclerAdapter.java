package com.imrainbow.popularmovies.ui.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vclub on 15/7/24.
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected List<T> mItems;
    protected Context mContext;

    public BaseRecyclerAdapter(Context ctx) {
        mContext = ctx;
        mItems = new ArrayList<>();
    }

    public void setItems(List<T> newItems){
        if (newItems != null){
            mItems = newItems;
            notifyDataSetChanged();
        }
    }

    public void addItems(List<T> items){
        if (items != null){
            mItems.addAll(items);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }
}
