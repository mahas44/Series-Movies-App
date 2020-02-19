package com.enes.moviesapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.enes.moviesapp.ui.MovieListItemClickListener;
import com.enes.moviesapp.R;
import com.enes.moviesapp.ui.view.VideoPopupActivity;
import com.enes.moviesapp.databinding.ListEachRowMovieArticleBinding;
import com.enes.moviesapp.model.movie.Movie;

public class MovieAdapter extends PagedListAdapter<Movie, MovieAdapter.ViewHolder> implements MovieListItemClickListener {

    private PagedList<Movie> moviePagedList;
    Context context;

    public MovieAdapter(Context context) {
        super(Movie.CALLBACK);
        this.context = context;
    }

    public void setMoviePagedList(PagedList<Movie> moviePagedList) {
        this.moviePagedList = moviePagedList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListEachRowMovieArticleBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.list_each_row_movie_article, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Movie movie = getItem(position);
        holder.bind(movie);
        holder.movieArticleBinding.setClickListener(this);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ListEachRowMovieArticleBinding movieArticleBinding;

        public ViewHolder(ListEachRowMovieArticleBinding movieArticleBinding) {
            super(movieArticleBinding.getRoot());
            this.movieArticleBinding = movieArticleBinding;
        }

        public void bind(Object object){
            movieArticleBinding.setVariable(com.enes.moviesapp.BR.movie, object);
            movieArticleBinding.executePendingBindings();
        }
    }

    public void movieClicked(Movie movie) {
        Intent intent = new Intent(context, VideoPopupActivity.class);
        intent.putExtra("trailer", movie.getTitle() + " trailer");
        context.startActivity(intent);
    }
}
