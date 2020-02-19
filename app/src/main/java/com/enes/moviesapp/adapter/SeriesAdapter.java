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

import com.enes.moviesapp.databinding.ListEachRowSeriesArticleBinding;
import com.enes.moviesapp.model.series.Series;
import com.enes.moviesapp.R;
import com.enes.moviesapp.ui.SeriesListItemClickListener;
import com.enes.moviesapp.ui.view.VideoPopupActivity;

public class SeriesAdapter extends PagedListAdapter<Series,SeriesAdapter.ViewHolder> implements SeriesListItemClickListener {

    private Context context;
    private PagedList<Series> seriesPagedList;

    public SeriesAdapter(Context context) {
        super(Series.CALLBACK);
        this.context = context;
    }

    public void setSeriesPagedList(PagedList<Series> seriesPagedList) {
        this.seriesPagedList = seriesPagedList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ListEachRowSeriesArticleBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.list_each_row_series_article, parent,false);

        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Series series = getItem(position);

        holder.bind(series);
        holder.seriesArticleBinding.setClickListener(this);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ListEachRowSeriesArticleBinding seriesArticleBinding;

        public ViewHolder(ListEachRowSeriesArticleBinding seriesArticleBinding) {
            super(seriesArticleBinding.getRoot());
            this.seriesArticleBinding = seriesArticleBinding;
        }

        public void bind(Object object){
            seriesArticleBinding.setVariable(com.enes.moviesapp.BR.series, object);
            seriesArticleBinding.executePendingBindings();
        }
    }

    public void seriesClicked(Series series) {
        Intent intent = new Intent(context, VideoPopupActivity.class);
        intent.putExtra("trailer", series.getName() + " trailer");
        context.startActivity(intent);
    }
}
