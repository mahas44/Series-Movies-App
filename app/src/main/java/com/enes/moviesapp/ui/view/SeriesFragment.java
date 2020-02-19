package com.enes.moviesapp.ui.view;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.enes.moviesapp.App;
import com.enes.moviesapp.adapter.SeriesAdapter;
import com.enes.moviesapp.databinding.FragmentSeriesBinding;
import com.enes.moviesapp.model.series.Series;
import com.enes.moviesapp.R;
import com.enes.moviesapp.ui.viewmodel.SeriesViewModel;
import com.enes.moviesapp.ui.viewmodel.SeriesViewModelFactory;

import javax.inject.Inject;

public class SeriesFragment extends Fragment {

    private RecyclerView my_recycler_view;
    private SeriesAdapter adapter;
    private PagedList<Series> series;
    private SeriesViewModel seriesViewModel;
    private FragmentSeriesBinding binding;

    @Inject
    public SeriesViewModelFactory seriesViewModelFactory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_series,container,false);

        my_recycler_view= binding.myRecyclerView;
        my_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        my_recycler_view.setHasFixedSize(true);


        // adapter
        adapter = new SeriesAdapter(getContext());
        my_recycler_view.setAdapter(adapter);
        adapter.setSeriesPagedList(series);

        App.getApp().getSeriesComponent().inject(this);

        // View Model
        seriesViewModel = ViewModelProviders.of(this,seriesViewModelFactory).get(SeriesViewModel.class);
        getSeries();


        return binding.getRoot();
    }

    private void getSeries() {

        seriesViewModel.getSeriesPagedList().observe(getViewLifecycleOwner(), new Observer<PagedList<Series>>() {
            @Override
            public void onChanged(PagedList<Series> seriesFromLiveData) {
                series = seriesFromLiveData;
                adapter.submitList(series);
            }
        });
    }

}
