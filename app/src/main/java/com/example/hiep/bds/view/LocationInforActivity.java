package com.example.hiep.bds.view;

import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.hiep.bds.Presenter.DataPre;
import com.example.hiep.bds.Presenter.GetLocation;
import com.example.hiep.bds.Presenter.LocationPre;
import com.example.hiep.bds.R;
import com.example.hiep.bds.model.modelLocation.CityResponse;
import com.example.hiep.bds.model.modelLocation.Huyen;
import com.example.hiep.bds.model.modelLocation.LtsItem;
import com.example.hiep.bds.model.modelLocation.Phuong;
import com.example.hiep.bds.utilts.ApiClient;
import com.example.hiep.bds.utilts.ApiInterface;
import com.example.hiep.bds.utilts.CustemSpinner;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationInforActivity extends AppCompatActivity implements GetLocationView{
    Toolbar mToolbar;
    Spinner mSpinnerTinh, mSpinnerHuyen,mSpinnerXa;
    EditText mEditTextDiaChi;
    Button btnTiepTuc;
    GetLocation iGetLocationView;
    ArrayList<String> name;
    CustemSpinner mCustemSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        setContentView(R.layout.activity_location_info);
        init();
        setToolbar();
        getDataMovieFromService();

    }
    private void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Địa chỉ bất động sản");
        mToolbar.setNavigationIcon(R.drawable.ic_back_detail);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorWhile));

    }

    private void init() {
        mToolbar = findViewById(R.id.toolbarLoaction);
        btnTiepTuc = findViewById(R.id.btnLocation);
        mSpinnerTinh =  findViewById(R.id.spinnerTinh);
        mSpinnerHuyen = findViewById(R.id.spinnerHuyen);
        mSpinnerXa = findViewById(R.id.spinnerXa);
        mEditTextDiaChi = findViewById(R.id.edtDiaChi);
        iGetLocationView = new LocationPre(this);

    }
    private void getDataMovieFromService() {

        iGetLocationView.getLocation();
    }
    @Override
    public void getListLocationSucces(final List<LtsItem> locationResponses) {
        //Toast.makeText(this, "co du lieu", Toast.LENGTH_SHORT).show();
         name = new ArrayList<>();
        for (LtsItem movie : locationResponses) {
            name.add(movie.getTitle());
        }
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
//                android.R.layout.simple_spinner_item,name);
//        arrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        mCustemSpinner = new CustemSpinner(LocationInforActivity.this,
                android.R.layout.simple_spinner_item,
                name);
        mSpinnerTinh.setAdapter(mCustemSpinner);

        mSpinnerTinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ApiInterface apiService = ApiClient.getCity().create(ApiInterface.class);
                Call<List<Huyen>> call = apiService.getHuyen(locationResponses.get(i).getID());
                call.enqueue(new Callback<List<Huyen>>() {
                    @Override
                    public void onResponse(Call<List<Huyen>> call, final Response<List<Huyen>> response) {
                        if (response.code() == 200) {
                            ArrayList<String> huyen = new ArrayList<>();
                            for (Huyen movie : response.body()) {
                                huyen.add(movie.getTitle());
                            }
                            mCustemSpinner = new CustemSpinner(LocationInforActivity.this,
                                    android.R.layout.simple_spinner_item, huyen);
                            mSpinnerHuyen.setAdapter(mCustemSpinner);

                            mSpinnerHuyen.setOnItemSelectedListener(new AdapterView
                                    .OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView, View view,
                                        int i, long l) {

                                    ApiInterface apiService = ApiClient.getCity().create(ApiInterface.class);
                                    Call<List<Phuong>> call = apiService.getPhuong(response.body().get(i).getID());
                                    call.enqueue(new Callback<List<Phuong>>() {
                                        @Override
                                        public void onResponse(Call<List<Phuong>> call, Response<List<Phuong>> response) {
                                            if (response.code() == 200) {
                                                ArrayList<String> phuong = new ArrayList<>();
                                                for (Phuong movie : response.body()) {
                                                    phuong.add(movie.getTitle());
                                                }
                                                mCustemSpinner = new CustemSpinner(LocationInforActivity.this,
                                                        android.R.layout.simple_spinner_item, phuong);
                                                mSpinnerXa.setAdapter(mCustemSpinner);

                                            } else {
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<List<Phuong>> call, Throwable t) {
                                        }
                                    });

                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> adapterView) {

                                }
                            });


                        } else {
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Huyen>> call, Throwable t) {
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public void getListLocationFailure() {

    }

    @Override
    public Context getContext() {
        return null;
    }
}
