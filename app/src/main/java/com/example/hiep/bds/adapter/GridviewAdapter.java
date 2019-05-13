package com.example.hiep.bds.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.hiep.bds.R;
import com.example.hiep.bds.model.Datum;
import com.example.hiep.bds.model.Distance;
import com.example.hiep.bds.utilts.GetChildFragment;
import com.example.hiep.bds.utilts.OnItemClick;
import com.example.hiep.bds.utilts.Unit;
import com.example.hiep.bds.view.detailData.DetailData;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class GridviewAdapter extends RecyclerView.Adapter<GridviewAdapter.DiscoverHolder> {

    private List<Distance> mMovies;
    private int mRowLayout;
    public Context mContext;
    public GridviewAdapter(List<Distance> movies, int rowLayout, Context context) {
        this.mMovies = movies;
        this.mRowLayout = rowLayout;
        this.mContext = context;
    }

    @Override
    public GridviewAdapter.DiscoverHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mRowLayout, parent, false);
        final GridviewAdapter.DiscoverHolder mViewHolder = new GridviewAdapter.DiscoverHolder(view);
        return mViewHolder;
    }

    public class DiscoverHolder extends RecyclerView.ViewHolder {
        private TextView mTextViewTitle, mTextViewmet;

        public DiscoverHolder(View v) {
            super(v);
            mTextViewTitle = v.findViewById(R.id.distance);
            mTextViewmet = v.findViewById(R.id.met);
        }
    }

    @Override
    public void onBindViewHolder(GridviewAdapter.DiscoverHolder holder, final int position) {
        final Distance movie = mMovies.get(position);
        holder.mTextViewTitle.setText(movie.getName());
        holder.mTextViewmet.setText(movie.getPivot().getMeters() + " m");
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }
}