package com.enes.moviesapp.ui.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.enes.moviesapp.AppController;
import com.enes.moviesapp.R;
import com.enes.moviesapp.adapter.MovieAdapter;
import com.enes.moviesapp.databinding.FragmentMovieBinding;
import com.enes.moviesapp.model.movie.Movie;
import com.enes.moviesapp.ui.viewmodel.MovieViewModel;
import com.enes.moviesapp.ui.viewmodel.MovieViewModelFactory;

import javax.inject.Inject;

public class MovieFragment extends Fragment {

    private RecyclerView my_recycler_view;
    private MovieAdapter adapter;
    private PagedList<Movie> movies;
    private MovieViewModel movieViewModel;
    private FragmentMovieBinding binding;

    @Inject
    public MovieViewModelFactory movieViewModelFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_movie,container,false);

        my_recycler_view= binding.myRecyclerView;
        my_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        my_recycler_view.setHasFixedSize(true);

        // adapter
        adapter = new MovieAdapter(getContext());
        my_recycler_view.setAdapter(adapter);
        adapter.setMoviePagedList(movies);

        AppController.create(getContext()).getMovieComponent().inject(this);

        // View Model
        movieViewModel = ViewModelProviders.of(this, movieViewModelFactory).get(MovieViewModel.class);
        getMovies();

        return binding.getRoot();
    }

    private void getMovies() {

        movieViewModel.getMoviesPagedList().observe(getViewLifecycleOwner(), new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(PagedList<Movie> movieFromLiveData) {
                movies = movieFromLiveData;
                adapter.submitList(movies);

            }
        });
    }

}
