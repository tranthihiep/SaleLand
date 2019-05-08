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
import android.widget.Filter;
import android.widget.Filterable;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DiscoverHolder>
        implements Filterable {

    private List<Datum> mMovies;
    private List<Datum> mMoviesFilter;
    private int mRowLayout;
    public transient Context mContext;
    private ValueFilter valueFilter;
    private GetChildFragment iGetChildFragment;

    public DataAdapter(List<Datum> movies, int rowLayout, Context context) {
        this.mMovies = movies;
        this.mRowLayout = rowLayout;
        this.mContext = context;
        this.mMoviesFilter = movies;
        // getFilter();
        // this.iGetChildFragment = getChildFragment;
    }

    @Override
    public DiscoverHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mRowLayout, parent, false);
        final DiscoverHolder mViewHolder = new DiscoverHolder(view);
        return mViewHolder;
    }

    public void addData(List<Datum> data) {
        for (Datum datum : data) {
            mMovies.add(datum);
        }
        notifyDataSetChanged();
    }
    public void dataForCity(List<Datum> data, int id) {
        for (int i =0 ; i<data.size();i++) {
            if (data.get(i).getDistrict().getCityId() == id){
                mMovies.add((Datum) data);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null){
            valueFilter = new DataAdapter.ValueFilter();
        }return valueFilter;

    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0) {
                ArrayList<Datum> filterList = new ArrayList<Datum>();
                for (int i = 0; i < mMoviesFilter.size(); i++) {
                    if ((mMoviesFilter.get(i).getTitle().toUpperCase()).contains(
                            constraint.toString().toUpperCase())) {
                        Datum movies = new Datum();
                        movies.setId(mMoviesFilter.get(i).getId());
                        movies.setTitle(mMoviesFilter.get(i).getTitle());
                        movies.setAddress(mMoviesFilter.get(i).getAddress());
                        movies.setArea(mMoviesFilter.get(i).getArea());
                        movies.setCreatedAt(mMoviesFilter.get(i).getCreatedAt());
                        movies.setDescription(mMoviesFilter.get(i).getDescription());
                        movies.setDetail(mMoviesFilter.get(i).getDetail());
                        movies.setImage(mMoviesFilter.get(i).getImage());
                        movies.setDistances(mMoviesFilter.get(i).getDistances());
                        movies.setUser(mMoviesFilter.get(i).getUser());
                        movies.setPrice(mMoviesFilter.get(i).getPrice());
                        movies.setDistrict(mMoviesFilter.get(i).getDistrict());
                        movies.setDetail(mMoviesFilter.get(i).getDetail());
                        movies.setPropertyType(mMoviesFilter.get(i).getPropertyType());
                        movies.setPurpose(mMoviesFilter.get(i).getPurpose());
                        movies.setViews(mMoviesFilter.get(i).getViews());
                        movies.setStatus(mMoviesFilter.get(i).getStatus());
                        movies.setNegotiable(mMoviesFilter.get(i).getNegotiable());
                        movies.setArea(mMoviesFilter.get(i).getArea());
                        movies.setTypeId(mMoviesFilter.get(i).getTypeId());
                        movies.setUserId(mMoviesFilter.get(i).getUserId());
                        movies.setSlug(mMoviesFilter.get(i).getSlug());
                        movies.setImages(mMoviesFilter.get(i).getImages());
                        movies.setLatitude(mMoviesFilter.get(i).getLatitude());
                        movies.setLongitude(mMoviesFilter.get(i).getLongitude());
                        movies.setPropertyTypeId(mMoviesFilter.get(i).getPropertyTypeId());
                        movies.setEndDate(mMoviesFilter.get(i).getEndDate());
                        movies.setStartDate(mMoviesFilter.get(i).getStartDate());
                        movies.setDistrictId(mMoviesFilter.get(i).getDistrictId());
                        movies.setUpdatedAt(mMoviesFilter.get(i).getUpdatedAt());
                        movies.setType(mMoviesFilter.get(i).getType());
                        filterList.add(movies);
                    }else if ((mMoviesFilter.get(i).getDistrict().getCity().getName()).equalsIgnoreCase(
                            constraint.toString())){
                        Datum movies = new Datum();
                        movies.setId(mMoviesFilter.get(i).getId());
                        movies.setTitle(mMoviesFilter.get(i).getTitle());
                        movies.setAddress(mMoviesFilter.get(i).getAddress());
                        movies.setArea(mMoviesFilter.get(i).getArea());
                        movies.setCreatedAt(mMoviesFilter.get(i).getCreatedAt());
                        movies.setDescription(mMoviesFilter.get(i).getDescription());
                        movies.setDetail(mMoviesFilter.get(i).getDetail());
                        movies.setImage(mMoviesFilter.get(i).getImage());
                        movies.setDistances(mMoviesFilter.get(i).getDistances());
                        movies.setUser(mMoviesFilter.get(i).getUser());
                        movies.setPrice(mMoviesFilter.get(i).getPrice());
                        movies.setDistrict(mMoviesFilter.get(i).getDistrict());
                        movies.setDetail(mMoviesFilter.get(i).getDetail());
                        movies.setPropertyType(mMoviesFilter.get(i).getPropertyType());
                        movies.setPurpose(mMoviesFilter.get(i).getPurpose());
                        movies.setViews(mMoviesFilter.get(i).getViews());
                        movies.setStatus(mMoviesFilter.get(i).getStatus());
                        movies.setNegotiable(mMoviesFilter.get(i).getNegotiable());
                        movies.setArea(mMoviesFilter.get(i).getArea());
                        movies.setTypeId(mMoviesFilter.get(i).getTypeId());
                        movies.setUserId(mMoviesFilter.get(i).getUserId());
                        movies.setSlug(mMoviesFilter.get(i).getSlug());
                        movies.setImages(mMoviesFilter.get(i).getImages());
                        movies.setLatitude(mMoviesFilter.get(i).getLatitude());
                        movies.setLongitude(mMoviesFilter.get(i).getLongitude());
                        movies.setPropertyTypeId(mMoviesFilter.get(i).getPropertyTypeId());
                        movies.setEndDate(mMoviesFilter.get(i).getEndDate());
                        movies.setStartDate(mMoviesFilter.get(i).getStartDate());
                        movies.setDistrictId(mMoviesFilter.get(i).getDistrictId());
                        movies.setUpdatedAt(mMoviesFilter.get(i).getUpdatedAt());
                        movies.setType(mMoviesFilter.get(i).getType());
                        filterList.add(movies);

                    }else if ((mMoviesFilter.get(i).getDistrict().getName()).equalsIgnoreCase(
                            constraint.toString())){
                        Datum movies = new Datum();
                        movies.setId(mMoviesFilter.get(i).getId());
                        movies.setTitle(mMoviesFilter.get(i).getTitle());
                        movies.setAddress(mMoviesFilter.get(i).getAddress());
                        movies.setArea(mMoviesFilter.get(i).getArea());
                        movies.setCreatedAt(mMoviesFilter.get(i).getCreatedAt());
                        movies.setDescription(mMoviesFilter.get(i).getDescription());
                        movies.setDetail(mMoviesFilter.get(i).getDetail());
                        movies.setImage(mMoviesFilter.get(i).getImage());
                        movies.setDistances(mMoviesFilter.get(i).getDistances());
                        movies.setUser(mMoviesFilter.get(i).getUser());
                        movies.setPrice(mMoviesFilter.get(i).getPrice());
                        movies.setDistrict(mMoviesFilter.get(i).getDistrict());
                        movies.setDetail(mMoviesFilter.get(i).getDetail());
                        movies.setPropertyType(mMoviesFilter.get(i).getPropertyType());
                        movies.setPurpose(mMoviesFilter.get(i).getPurpose());
                        movies.setViews(mMoviesFilter.get(i).getViews());
                        movies.setStatus(mMoviesFilter.get(i).getStatus());
                        movies.setNegotiable(mMoviesFilter.get(i).getNegotiable());
                        movies.setArea(mMoviesFilter.get(i).getArea());
                        movies.setTypeId(mMoviesFilter.get(i).getTypeId());
                        movies.setUserId(mMoviesFilter.get(i).getUserId());
                        movies.setSlug(mMoviesFilter.get(i).getSlug());
                        movies.setImages(mMoviesFilter.get(i).getImages());
                        movies.setLatitude(mMoviesFilter.get(i).getLatitude());
                        movies.setLongitude(mMoviesFilter.get(i).getLongitude());
                        movies.setPropertyTypeId(mMoviesFilter.get(i).getPropertyTypeId());
                        movies.setEndDate(mMoviesFilter.get(i).getEndDate());
                        movies.setStartDate(mMoviesFilter.get(i).getStartDate());
                        movies.setDistrictId(mMoviesFilter.get(i).getDistrictId());
                        movies.setUpdatedAt(mMoviesFilter.get(i).getUpdatedAt());
                        movies.setType(mMoviesFilter.get(i).getType());
                        filterList.add(movies);

                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mMoviesFilter.size();
                results.values = mMoviesFilter;
            }
            return results;
        }

        //Invoked in the UI thread to publish the filtering results in the user interface.
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mMovies = (ArrayList<Datum>) results.values;
            notifyDataSetChanged();
        }
    }

    public class DiscoverHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageView;
        private TextView mTextViewTitle, mTextViewPrice, mTextViewLoaction, mTextViewFlood,
                mTextViewBed, mTextViewTime, mTextViewCatalogy, mTextViewS;
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
            mTextViewCatalogy = v.findViewById(R.id.catalogy);
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
        holder.mTextViewS.setText(movie.getArea() + " m²");
        holder.mTextViewTitle.setText(movie.getTitle());
        holder.mTextViewFlood.setText(movie.getDetail().getFloor() + " Tầng");
        holder.mTextViewTime.setText( getDaysDifference(movie.getStartDate().toString())+" ngày trước");
        holder.mTextViewLoaction.setText(movie.getDistrict().getName()+"/ "+
                movie.getDistrict().getCity().getName());
        holder.mTextViewBed.setText(movie.getDetail().getBedRoom() + " Phòng ngủ");
        holder.mTextViewPrice.setText(
                Unit.formatPrice((long) movie.getPrice()).toString() + " VND/" + movie.getUnit());
        Picasso.get()
                .load("http://project-property.herokuapp.com/uploads/images/" + movie.getImage())
                .into(holder.mImageView);

        holder.setItemClickListener(new OnItemClick() {
            @Override
            public void onClick(View v, int adapterPosition, boolean b) {

                Bundle bundle = new Bundle();
                bundle.putInt("id", movie.getId());
                Intent i = new Intent(mContext, DetailData.class);
                i.putExtras(bundle);
                mContext.startActivity(i);
            }

            @Override
            public void onClickItem(int pos) {

            }

            @Override
            public void onClickDelete(int pos) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }
    public int getDaysDifference( String startDate)
    {
        Calendar myCalendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = format.parse(startDate);
            if(date==null)
                return 0;
            return (int)( TimeUnit.DAYS.convert(myCalendar.getTime().getTime() - date.getTime(), TimeUnit.MILLISECONDS));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  0 ;

    }
}