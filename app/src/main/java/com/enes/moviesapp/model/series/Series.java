package com.enes.moviesapp.model.series;

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

public class Series extends BaseObservable {
    @Expose
    @SerializedName("media_type")
    private String media_type;
    @Expose
    @SerializedName("popularity")
    private double popularity;
    @Expose
    @SerializedName("origin_country")
    private List<String> origin_country;
    @Expose
    @SerializedName("overview")
    private String overview;
    @Expose
    @SerializedName("backdrop_path")
    private String backdrop_path;
    @Expose
    @SerializedName("original_language")
    private String original_language;
    @Expose
    @SerializedName("genre_ids")
    private List<Integer> genre_ids;
    @Expose
    @SerializedName("poster_path")
    private String poster_path;
    @Expose
    @SerializedName("first_air_date")
    private String first_air_date;
    @Expose
    @SerializedName("vote_average")
    private double vote_average;
    @Expose
    @SerializedName("vote_count")
    private int vote_count;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("id")
    private int id;
    @Expose
    @SerializedName("original_name")
    private String original_name;
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
    public List<String> getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(List<String> origin_country) {
        this.origin_country = origin_country;
    }
    @Bindable
    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
    @Bindable
    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }
    @Bindable
    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }
    @Bindable
    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }
    @Bindable
    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }
    @Bindable
    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Bindable
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Bindable
    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public static final DiffUtil.ItemCallback<Series> CALLBACK = new DiffUtil.ItemCallback<Series>() {
        @Override
        public boolean areItemsTheSame(@NonNull Series oldItem, @NonNull Series newItem) {
            return oldItem.id == newItem.id;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Series oldItem, @NonNull Series newItem) {
            return true;
        }
    };

    @BindingAdapter("seriesPoster")
    public static void loadImage(ImageView view, String imageUrl){
        Glide.with(view.getContext())
                .load("https://image.tmdb.org/t/p/w500"+imageUrl)
                .into(view);
    }

}
