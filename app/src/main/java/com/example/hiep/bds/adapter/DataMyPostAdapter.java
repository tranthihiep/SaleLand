package com.example.hiep.bds.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.hiep.bds.R;
import com.example.hiep.bds.model.DataMyPost;
import com.example.hiep.bds.model.Datum;
import com.example.hiep.bds.utilts.GetChildFragment;
import com.example.hiep.bds.utilts.OnItemClick;
import com.example.hiep.bds.utilts.Unit;
import com.example.hiep.bds.view.detailData.DetailData;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class DataMyPostAdapter extends RecyclerView.Adapter<DataMyPostAdapter.DiscoverHolder>{

    private List<DataMyPost> mMovies;
    private int mRowLayout;
    public Context mContext;
    private OnItemClick mListener;
    public DataMyPostAdapter(List<DataMyPost> movies, int rowLayout, Context context) {
        this.mMovies = movies;
        this.mRowLayout = rowLayout;
        this.mContext = context;
    }
    public void setOnItemClickListener(OnItemClick listener) {
        mListener = listener;
    }


    @Override
    public DataMyPostAdapter.DiscoverHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mRowLayout, parent, false);
        final DataMyPostAdapter.DiscoverHolder mViewHolder = new DataMyPostAdapter.
                DiscoverHolder(view,mListener);
        return mViewHolder;
    }

    public class DiscoverHolder extends RecyclerView.ViewHolder  {
        private ImageView mImageView,mImageViewDelete;
        private TextView mTextViewTitle, mTextViewPrice, mTextViewLoaction;
       // private OnItemClick itemClickListener;

        public DiscoverHolder(View v, final OnItemClick listener) {
            super(v);
            mImageView = v.findViewById(R.id.imgMypost);
            mTextViewTitle = v.findViewById(R.id.txtTitleMypost);
            mTextViewPrice = v.findViewById(R.id.txt_price_my_post);
            mTextViewLoaction = v.findViewById(R.id.txt_loaction_my_post);
            mImageViewDelete = v.findViewById(R.id.img_delete);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onClickItem(position);
                        }
                    }
                }
            });
            mImageViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onClickDelete(position);
                        }
                    }
                }
            });

        }

    }

    @Override
    public void onBindViewHolder(DataMyPostAdapter.DiscoverHolder holder,
            final int position) {
        final DataMyPost movie = mMovies.get(position);
        holder.mTextViewTitle.setText(movie.getTitle());
        holder.mTextViewLoaction.setText(movie.getAddress());
        holder.mTextViewPrice.setText(
                Unit.formatPrice((long) movie.getPrice()).toString() + " VND/" + movie.getUnit());
        Picasso.get()
                .load("http://project-property.herokuapp.com/uploads/images/" + movie.getImage())
                .into(holder.mImageView);

//        holder.setItemClickListener(new OnItemClick() {
//            @Override
//            public void onClick(View v, int adapterPosition, boolean b) {
//
//                Bundle bundle = new Bundle();
//                bundle.putInt("id", movie.getId());
//                Intent i = new Intent(mContext, DetailData.class);
//                i.putExtras(bundle);
//                mContext.startActivity(i);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

}
