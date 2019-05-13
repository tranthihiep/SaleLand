package com.example.hiep.bds.view.postAD;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.hiep.bds.presenter.GetLocation;
import com.example.hiep.bds.R;
import com.example.hiep.bds.model.modelLocation.Huyen;
import com.example.hiep.bds.model.modelLocation.Tinh;
import com.example.hiep.bds.utilts.ApiClient;
import com.example.hiep.bds.utilts.ApiInterface;
import com.example.hiep.bds.utilts.CustemSpinner;
import com.google.android.gms.vision.barcode.Barcode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationInforActivity extends AppCompatActivity  {
    Toolbar mToolbar;
    Spinner mSpinnerTinh, mSpinnerHuyen;
    TextInputEditText mEditTextDiaChi;
    Button btnTiepTuc;
    GetLocation iGetLocationView;
    ArrayList<String> name;
    CustemSpinner mCustemSpinner;
    String tinhS, huyenS, dc;
    int idTinh, idHuyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_info);
        init();
        setToolbar();
        getData();
        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((mEditTextDiaChi.getText().toString().trim().length() == 0)) {
                    Toast.makeText(LocationInforActivity.this, "Vui lòng nhập địa chỉ",
                            Toast.LENGTH_SHORT).show();
                } else {
                    dc = mEditTextDiaChi.getText().toString();

                    SharedPreferences.Editor editor =
                            getSharedPreferences("LOCATION", MODE_PRIVATE).edit();
                    editor.putInt("tinh", idTinh);
                    editor.putInt("huyen", idHuyen);
                    editor.putString("diachi", dc + ", " + huyenS + ", " + tinhS + ", Việt Nam");
                    editor.putFloat("lat", (float) getLocationFromAddress(dc + ", " + huyenS + ", " + tinhS + ", Việt Nam").lat);
                    editor.putFloat("long", (float) getLocationFromAddress(
                            dc + ", " + huyenS + ", " + tinhS + ", Việt Nam").lng);
                    editor.apply();

                    Intent intent = new Intent(LocationInforActivity.this, ADInforActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    @SuppressLint("RestrictedApi")
    public Barcode.GeoPoint getLocationFromAddress(String strAddress) {

        Geocoder coder = new Geocoder(this);
        List<Address> address;
        Barcode.GeoPoint p1 = null;

        try {
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            p1 = new Barcode.GeoPoint((double) (location.getLatitude()),
                    (double) (location.getLongitude()));

            return p1;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p1;
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
        mSpinnerTinh = findViewById(R.id.spinnerTinh);
        mSpinnerHuyen = findViewById(R.id.spinnerHuyen);
        //mSpinnerXa = findViewById(R.id.spinnerXa);
        mEditTextDiaChi = findViewById(R.id.edtDiaChi);
    }

    private void getData(){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Tinh>> call = apiService.getCity();
        call.enqueue(new Callback<List<Tinh>>() {
            @Override
            public void onResponse(Call<List<Tinh>> call,
                    final Response<List<Tinh>> response) {
                if (response.code() == 200) {
                    ArrayList<String> tp = new ArrayList<>();
                    for (Tinh movie : response.body()) {
                        tp.add(movie.getName());
                    }
                    mCustemSpinner = new CustemSpinner(LocationInforActivity.this,
                            android.R.layout.simple_spinner_item, tp);
                    mSpinnerTinh.setAdapter(mCustemSpinner);

                    mSpinnerTinh.setOnItemSelectedListener(
                            new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> adapterView,
                                        View view, final int i, long l) {
                                        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                                        Call<List<Huyen>> call = apiService.getHuyen(response.body().get(i).getId());
                                        call.enqueue(new Callback<List<Huyen>>() {
                                            @Override
                                            public void onResponse(Call<List<Huyen>> call,
                                                    final Response<List<Huyen>> response1) {
                                                if (response1.code() == 200) {
                                                    ArrayList<String> huyen = new ArrayList<>();
                                                    for (Huyen movie : response1.body()) {
                                                        huyen.add(movie.getName());
                                                    }
                                                    mCustemSpinner = new CustemSpinner(LocationInforActivity.this,
                                                            android.R.layout.simple_spinner_item, huyen);
                                                    mSpinnerHuyen.setAdapter(mCustemSpinner);

                                                    mSpinnerHuyen.setOnItemSelectedListener(
                                                            new AdapterView.OnItemSelectedListener() {
                                                                @Override
                                                                public void onItemSelected(AdapterView<?> adapterView,
                                                                        View view, int j, long l) {

                                                                    idTinh =response.body().get(i).getId();
                                                                    huyenS = mSpinnerHuyen.getSelectedItem().toString();
                                                                    tinhS =mSpinnerTinh.getSelectedItem().toString();
                                                                    idHuyen = response1.body().get(j).getId();
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
            }

            @Override
            public void onFailure(Call<List<Tinh>> call, Throwable t) {
            }
        });


    }
}
