package com.example.hiep.bds.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.hiep.bds.model.Datum;
import com.example.hiep.bds.R;
import com.example.hiep.bds.utilts.GetChildFragment;
import com.example.hiep.bds.utilts.OnItemClick;
import com.example.hiep.bds.utilts.Unit;
import com.example.hiep.bds.view.detailData.DetailData;
import com.squareup.picasso.Picasso;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DiscoverHolder> {

private List<Datum> mMovies;
private int mRowLayout;
    public transient  Context mContext;
private GetChildFragment iGetChildFragment;
public  DataAdapter(List<Datum> movies, int rowLayout,
        Context context) {
        this.mMovies = movies;
        this.mRowLayout = rowLayout;
        this.mContext = context;
       // this.iGetChildFragment = getChildFragment;
        }
@Override
public DiscoverHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mRowLayout, parent, false);
final DiscoverHolder mViewHolder = new DiscoverHolder(view);
        return mViewHolder;
        }

    public void addData(List<Datum> data){
        for (Datum datum : data){
            mMovies.add(datum);
        }
        notifyDataSetChanged();
    }

    public class DiscoverHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {
    private ImageView mImageView;
    private TextView mTextViewTitle, mTextViewPrice, mTextViewLoaction, mTextViewFlood,
    mTextViewBed,mTextViewTime, mTextViewCatalogy,mTextViewS;
    private OnItemClick itemClickListener;

    public DiscoverHolder(View v) {
        super(v);
        mImageView = v.findViewById(R.id.img_item);
        mTextViewTitle = v.findViewById(R.id.txt_title);
        mTextViewPrice = v.findViewById(R.id.txt_price);
        mTextViewLoaction = v.findViewById(R.id.txt_loaction);
        mTextViewFlood = v.findViewById(R.id.txt_flood);
        mTextViewBed = v.findViewById(R.id.txt_bedroom);
        mTextViewTime = v.findViewById(R.id.txt_time);
        mTextViewCatalogy =v.findViewById(R.id.catalogy);
        mTextViewS = v.findViewById(R.id.txt_s);
        v.setOnClickListener(this);
    }

    public void setItemClickListener(OnItemClick itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), false);
        }
    }
    @Override
    public void onBindViewHolder(DiscoverHolder holder, final int position) {
         final Datum movie = mMovies.get(position);
        holder.mTextViewCatalogy.setText(movie.getPropertyType().getName());
        holder.mTextViewS.setText(movie.getArea()+ " m²");
        holder.mTextViewTitle.setText(movie.getTitle());
        holder.mTextViewFlood.setText(movie.getDetail().getFloor()+" Tầng");
        holder.mTextViewTime.setText("1 week ago");
        holder.mTextViewLoaction.setText(movie.getDistrict().getName());
        holder.mTextViewBed.setText(movie.getDetail().getBedRoom() + " Phòng ngủ");
        holder.mTextViewPrice.setText(Unit.formatPrice((long) movie.getPrice()).toString() +" VND/" + movie.getUnit());
        Picasso.get().load("http://project-property.herokuapp.com/uploads/images/"+movie.getImage()).into(holder.mImageView);

        holder.setItemClickListener(new OnItemClick() {
            @Override
            public void onClick(View v, int adapterPosition, boolean b) {

                Bundle bundle = new Bundle();
                bundle.putInt("id", movie.getId());
                Intent i = new Intent(mContext, DetailData.class);
                i.putExtras(bundle);
                mContext.startActivity(i);

                //iGetChildFragment.addChildFragment(movieDetailFragment, true, true);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }
}