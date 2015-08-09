package com.imrainbow.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vclub on 15/7/25.
 */
public class MovieEntity implements Parcelable {
    /**
     * backdrop_path : /dkMD5qlogeRMiEixC4YNPUvax2T.jpg
     * vote_average : 7
     * adult : false
     * id : 135397
     * title : Jurassic World
     * original_language : en
     * overview : Twenty-two years after the events of Jurassic Park, Isla Nublar now features a fully functioning dinosaur theme park, Jurassic World, as originally envisioned by John Hammond.
     * genre_ids : [28,12,878,53]
     * original_title : Jurassic World
     * release_date : 2015-06-12
     * vote_count : 1354
     * poster_path : /uXZYawqUsChGSj54wcuBtEdUJbh.jpg
     * video : false
     * popularity : 82.059414
     */
    private String backdrop_path;
    private float vote_average;
    private boolean adult;
    private long id;
    private String title;
    private String original_language;
    private String overview;
    private List<Integer> genre_ids;
    private String original_title;
    private String release_date;
    private int vote_count;
    private String poster_path;
    private boolean video;
    private double popularity;

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public float getVote_average() {
        return vote_average;
    }

    public boolean isAdult() {
        return adult;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getOverview() {
        return overview;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getVote_count() {
        return vote_count;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public boolean isVideo() {
        return video;
    }

    public double getPopularity() {
        return popularity;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.backdrop_path);
        dest.writeFloat(this.vote_average);
        dest.writeByte(adult ? (byte) 1 : (byte) 0);
        dest.writeLong(this.id);
        dest.writeString(this.title);
        dest.writeString(this.original_language);
        dest.writeString(this.overview);
        dest.writeList(this.genre_ids);
        dest.writeString(this.original_title);
        dest.writeString(this.release_date);
        dest.writeInt(this.vote_count);
        dest.writeString(this.poster_path);
        dest.writeByte(video ? (byte) 1 : (byte) 0);
        dest.writeDouble(this.popularity);
    }

    public MovieEntity() {
    }

    protected MovieEntity(Parcel in) {
        this.backdrop_path = in.readString();
        this.vote_average = in.readFloat();
        this.adult = in.readByte() != 0;
        this.id = in.readLong();
        this.title = in.readString();
        this.original_language = in.readString();
        this.overview = in.readString();
        this.genre_ids = new ArrayList<Integer>();
        in.readList(this.genre_ids, List.class.getClassLoader());
        this.original_title = in.readString();
        this.release_date = in.readString();
        this.vote_count = in.readInt();
        this.poster_path = in.readString();
        this.video = in.readByte() != 0;
        this.popularity = in.readDouble();
    }

    public static final Parcelable.Creator<MovieEntity> CREATOR = new Parcelable.Creator<MovieEntity>() {
        public MovieEntity createFromParcel(Parcel source) {
            return new MovieEntity(source);
        }

        public MovieEntity[] newArray(int size) {
            return new MovieEntity[size];
        }
    };
}
