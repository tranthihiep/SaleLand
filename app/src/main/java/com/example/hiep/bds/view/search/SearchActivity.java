package com.example.hiep.bds.view.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.hiep.bds.presenter.DataPre;
import com.example.hiep.bds.presenter.GetData;
import com.example.hiep.bds.R;
import com.example.hiep.bds.adapter.DataAdapter;
import com.example.hiep.bds.model.DataResponnse;
import com.example.hiep.bds.model.Datum;
import com.example.hiep.bds.model.modelLocation.Huyen;
import com.example.hiep.bds.model.modelLocation.Tinh;
import com.example.hiep.bds.utilts.ApiClient;
import com.example.hiep.bds.utilts.ApiInterface;
import com.example.hiep.bds.utilts.CustemSpinner;
import com.example.hiep.bds.view.sale.GetDataView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity implements GetDataView{
    @BindView(R.id.toolBarSearch)
    Toolbar mToolbar;
    @BindView(R.id.edtSearch)
    EditText mEditText;
    @BindView(R.id.btnCancel)
    TextView mCancel;
    @BindView(R.id.recyclerSearch)
    RecyclerView mRecyclerMostPopular;
    @BindView(R.id.spinnerHuyenSearch)
    Spinner mSpinnerHuyen;
    @BindView(R.id.spinnerTinhSearch)
    Spinner mSpinnerTinh;
    private DataAdapter mDiscoverAdapter;
    private GetData iGetDiscoverPre;
    List<Datum> dd;
    CustemSpinner mCustemSpinner;
    private int page_current = 1;
    private boolean isLoading = true;
    private int postVisibleItems, visibleItemCount, totallItemCount, previous_total = 0;
    private int view_threshold = 10;
    ArrayList<String> name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);
        setToolbar();
        init();
        getDataMovieFromService();
        final LinearLayoutManager layoutManager1 =
                new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,
                        false);
        mRecyclerMostPopular.setLayoutManager(layoutManager1);
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
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                mDiscoverAdapter.getFilter().filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void getDataMovieFromService() {
        iGetDiscoverPre.getData(1);
    }

    private void init() {
        iGetDiscoverPre = new DataPre(this);
    }

    private void perfromPagaination() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<DataResponnse> call = apiService.getMovieDetails(page_current);
        call.enqueue(new Callback<DataResponnse>() {
            @Override
            public void onResponse(Call<DataResponnse> call, Response<DataResponnse> response) {
                if (response.code() == 200) {
                    List<Datum> images = response.body().getData();
                    mDiscoverAdapter.addData(images);
                } else {
                    Toast.makeText(getApplicationContext(), "No more Data available",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DataResponnse> call, Throwable t) {
            }
        });
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        mToolbar.setNavigationIcon(R.drawable.ic_backk);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorWhile));
    }

    @Override
    public void getListMovieSuccess(final List<Datum> movies) {

       dd = movies;
        mDiscoverAdapter = new DataAdapter(movies, R.layout.item_ads, getApplicationContext());
        mRecyclerMostPopular.setAdapter(mDiscoverAdapter);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Tinh>> call = apiService.getCity();
        call.enqueue(new Callback<List<Tinh>>() {
            @Override
            public void onResponse(Call<List<Tinh>> call,
                    final Response<List<Tinh>> response) {
                if (response.code() == 200) {
                    ArrayList<String> tp = new ArrayList<>();
                    tp.add("Chọn Tỉnh/TP");
                    for (Tinh movie : response.body()) {
                        tp.add(movie.getName());
                    }
                    mCustemSpinner = new CustemSpinner(SearchActivity.this,
                            android.R.layout.simple_spinner_item, tp);
                    mSpinnerTinh.setAdapter(mCustemSpinner);

                    mSpinnerTinh.setOnItemSelectedListener(
                            new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView,
                                        View view, int i, long l) {
                                    if (mSpinnerTinh.getSelectedItemPosition()!=0){
                                        mDiscoverAdapter.getFilter().filter(mSpinnerTinh.getSelectedItem().toString());
                                    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                                    Call<List<Huyen>> call = apiService.getHuyen(response.body().get(i-1).getId());
                                    call.enqueue(new Callback<List<Huyen>>() {
                                        @Override
                                        public void onResponse(Call<List<Huyen>> call,
                                                final Response<List<Huyen>> response) {
                                            if (response.code() == 200) {
                                                ArrayList<String> huyen = new ArrayList<>();
                                                huyen.add("Chọn quận/huyện");
                                                for (Huyen movie : response.body()) {
                                                    huyen.add(movie.getName());
                                                }
                                                mCustemSpinner = new CustemSpinner(SearchActivity.this,
                                                        android.R.layout.simple_spinner_item, huyen);
                                                mSpinnerHuyen.setAdapter(mCustemSpinner);

                                                mSpinnerHuyen.setOnItemSelectedListener(
                                                        new AdapterView.OnItemSelectedListener() {
                                                            @Override
                                                            public void onItemSelected(AdapterView<?> adapterView,
                                                                    View view, int i, long l) {

                                                                if (mSpinnerHuyen.getSelectedItemPosition()!=0) {
                                                                    mDiscoverAdapter.getFilter().filter(mSpinnerHuyen.getSelectedItem().toString());
                                                                }

                                                            }

                                                            @Override
                                                            public void onNothingSelected(AdapterView<?> adapterView) {

                                                            }
                                                        });
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<List<Huyen>> call, Throwable t) {
                                        }
                                    });

                                }}

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
                            });

            }
            }

            @Override
            public void onFailure(Call<List<Tinh>> call, Throwable t) {
            }
        });



    }

    @Override
    public void getListMovieFailure() {

    }

    @Override
    public Context getContext() {
        return null;
    }


}
