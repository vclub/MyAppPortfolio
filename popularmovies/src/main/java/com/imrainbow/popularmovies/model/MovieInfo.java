package com.imrainbow.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.imrainbow.popularmovies.dao.MovieEntity;

import java.util.List;

/**
 * Created by vclub on 15/7/24.
 */
public class MovieInfo implements Parcelable {

    private List<MovieEntity> results;
    private int page;
    private int total_pages;
    private int total_results;

    public void setResults(List<MovieEntity> results) {
        this.results = results;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public List<MovieEntity> getResults() {
        return results;
    }

    public int getPage() {
        return page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(results);
        dest.writeInt(this.page);
        dest.writeInt(this.total_pages);
        dest.writeInt(this.total_results);
    }

    public MovieInfo() {
    }

    protected MovieInfo(Parcel in) {
        this.results = in.createTypedArrayList(MovieEntity.CREATOR);
        this.page = in.readInt();
        this.total_pages = in.readInt();
        this.total_results = in.readInt();
    }

    public static final Parcelable.Creator<MovieInfo> CREATOR = new Parcelable.Creator<MovieInfo>() {
        public MovieInfo createFromParcel(Parcel source) {
            return new MovieInfo(source);
        }

        public MovieInfo[] newArray(int size) {
            return new MovieInfo[size];
        }
    };
}
