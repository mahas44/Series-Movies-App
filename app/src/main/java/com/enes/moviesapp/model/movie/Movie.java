package com.enes.moviesapp.model.movie;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie extends BaseObservable {
    @Expose
    @SerializedName("media_type")
    private String media_type;
    @Expose
    @SerializedName("popularity")
    private double popularity;
    @Expose
    @SerializedName("poster_path")
    private String poster_path;
    @Expose
    @SerializedName("overview")
    private String overview;
    @Expose
    @SerializedName("adult")
    private boolean adult;
    @Expose
    @SerializedName("backdrop_path")
    private String backdrop_path;
    @Expose
    @SerializedName("genre_ids")
    private List<Integer> genre_ids;
    @Expose
    @SerializedName("original_title")
    private String original_title;
    @Expose
    @SerializedName("original_language")
    private String original_language;
    @Expose
    @SerializedName("release_date")
    private String release_date;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("vote_average")
    private double vote_average;
    @Expose
    @SerializedName("vote_count")
    private int vote_count;
    @Expose
    @SerializedName("video")
    private boolean video;
    @Expose
    @SerializedName("id")
    private int id;

    @Bindable
    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    @Bindable
    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    @Bindable
    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    @Bindable
    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @Bindable
    public boolean getAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    @Bindable
    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }


    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    @Bindable
    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    @Bindable
    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    @Bindable
    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Bindable
    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    @Bindable
    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    @Bindable
    public boolean getVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public static final DiffUtil.ItemCallback<Movie> CALLBACK = new DiffUtil.ItemCallback<Movie>() {
        @Override
        public boolean areItemsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Movie oldItem, @NonNull Movie newItem) {
            return true;
        }
    };

    @BindingAdapter("moviePoster")
    public static void loadImage(ImageView view, String imageUrl){
        Glide.with(view.getContext())
                .load("https://image.tmdb.org/t/p/w500"+imageUrl)
                .into(view);
    }
}
