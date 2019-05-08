package com.example.hiep.bds.view.rent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.hiep.bds.Presenter.DataPre;
import com.example.hiep.bds.Presenter.GetData;
import com.example.hiep.bds.R;
import com.example.hiep.bds.adapter.DataAdapter;
import com.example.hiep.bds.model.DataResponnse;
import com.example.hiep.bds.model.Datum;
import com.example.hiep.bds.utilts.ApiClient;
import com.example.hiep.bds.utilts.ApiInterface;
import com.example.hiep.bds.utilts.GetFragment;
import com.example.hiep.bds.view.postAD.PorposeActivity;
import com.example.hiep.bds.view.sale.GetDataView;
import com.example.hiep.bds.view.search.SearchActivity;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RentFragment  extends GetFragment implements GetDataView {

    public RentFragment() {
    }
    private FloatingActionButton fab,fabSearch;
    private RecyclerView mRecyclerMostPopular;
    private DataAdapter mDiscoverAdapter;
    private List<Datum> mPopulars;
    private GetData iGetDiscoverPre;
    private int page_current = 1;
    private boolean isLoading = true;
    private int postVisibleItems, visibleItemCount, totallItemCount, previous_total = 0;
    private int view_threshold = 10;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sale, container, false);
        init(rootView);
        final LinearLayoutManager layoutManager1 =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerMostPopular.setLayoutManager(layoutManager1);
        getDataMovieFromService();
        //        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        //        Call<DataResponnse> call = apiService.getMovieDetails(page_current);
        //        call.enqueue(new Callback<DataResponnse>() {
        //            @Override
        //            public void onResponse(Call<DataResponnse> call, Response<DataResponnse>
        // response) {
        //                if (response.code() == 200) {
        //                    page_sum = response.body().getTotal();
        //
        //                } else {
        //                    Toast.makeText(getActivity(), "Get Page total error", Toast
        // .LENGTH_SHORT).show();
        //                }
        //            }
        //
        //            @Override
        //            public void onFailure(Call<DataResponnse> call, Throwable t) {
        //                Toast.makeText(getActivity(), "Get Page total error", Toast
        // .LENGTH_SHORT).show();
        //            }
        //        });
        mRecyclerMostPopular.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visibleItemCount = layoutManager1.getChildCount();
                totallItemCount = layoutManager1.getItemCount();
                postVisibleItems = layoutManager1.findFirstVisibleItemPosition();
                if (dy >= 0) {
                    if (isLoading) {
                        if (totallItemCount > previous_total) {
                            isLoading = false;
                            previous_total = totallItemCount;
                        }
                    }
                    if (!isLoading && (totallItemCount - visibleItemCount) <= (postVisibleItems
                            + view_threshold)) {
                        page_current++;
                        perfromPagaination();
                        isLoading = true;
                    }
                }
            }
        });
        mRecyclerMostPopular.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                if (dy > 0 ||dy<0 && fab.isShown())
                {
                    fab.hide();
                }
                if (dy > 0 ||dy<0 && fabSearch.isShown())
                {
                    fabSearch.hide();
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
                if (newState == RecyclerView.SCROLL_STATE_IDLE)
                {
                    fab.show();
                    fabSearch.show();
                }

                super.onScrollStateChanged(recyclerView, newState);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),PorposeActivity.class);
                startActivity(intent);
            }
        });
        fabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

    private void perfromPagaination() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<DataResponnse> call = apiService.getMovieDetails(page_current);
        call.enqueue(new Callback<DataResponnse>() {
            @Override
            public void onResponse(Call<DataResponnse> call, Response<DataResponnse> response) {
                if (response.code() == 200) {
                    List<Datum> images = response.body().getData();
                    mPopulars =  new ArrayList<>();
                    for (Datum movie : images) {
                        switch (movie.getPurpose()) {
                            case "rent":
                                mPopulars.add(movie);
                                break;
                        }
                    }

                    mDiscoverAdapter.addData(mPopulars);
                } else {
                    Toast.makeText(getActivity(), "No more Data available", Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<DataResponnse> call, Throwable t) {
            }
        });
    }

    private void getDataMovieFromService() {

        iGetDiscoverPre.getData(1);
    }

    private void init(View view) {
        fab = view.findViewById(R.id.fapDangtin);
        fabSearch = view.findViewById(R.id.fapTimKiem);
        mRecyclerMostPopular = view.findViewById(R.id.recyclerSale);
        mPopulars = new ArrayList<>();
        iGetDiscoverPre = new DataPre(this);
    }

    @Override
    public void getListMovieSuccess(List<Datum> movies) {
        for (Datum movie : movies) {
            switch (movie.getPurpose()) {
                case "rent":
                    mPopulars.add(movie);
                    break;
            }
        }
        mDiscoverAdapter = new DataAdapter(mPopulars, R.layout.item_ads, getActivity());
        mRecyclerMostPopular.setAdapter(mDiscoverAdapter);
    }

    @Override
    public void getListMovieFailure() {

    }
}
