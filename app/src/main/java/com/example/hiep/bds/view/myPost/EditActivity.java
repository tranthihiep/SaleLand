package com.example.hiep.bds.view.myPost;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.hiep.bds.presenter.DataDetailPre;
import com.example.hiep.bds.presenter.GetDataDetail;
import com.example.hiep.bds.R;
import com.example.hiep.bds.adapter.AmentiesAdapter;
import com.example.hiep.bds.model.Conveniences;
import com.example.hiep.bds.model.DataDetail;
import com.example.hiep.bds.model.modelLocation.Huyen;
import com.example.hiep.bds.model.modelLocation.Tinh;
import com.example.hiep.bds.model.postModel.DataPost;
import com.example.hiep.bds.utilts.ApiClient;
import com.example.hiep.bds.utilts.ApiInterface;
import com.example.hiep.bds.utilts.CustemSpinner;
import com.example.hiep.bds.utilts.ExpandableHeightListView;
import com.example.hiep.bds.utilts.Unit;
import com.example.hiep.bds.view.detailData.GetDataDetailView;
import com.example.hiep.bds.view.home.HomeActivity;
import com.google.android.gms.vision.barcode.Barcode;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends AppCompatActivity implements GetDataDetailView {
    @BindView(R.id.toolbarEdit)
    Toolbar mToolbar;
    @BindView(R.id.imgEdit)
    ImageView mImageEdit;
    @BindView(R.id.edtTitleEdit)
    TextInputEditText mEditTextTitle;
    @BindView(R.id.edtMotaEdit)
    TextInputEditText mEditTextDescrip;
    @BindView(R.id.spinnerLoaiEdit)
    Spinner mSpinnerSpecies;
    @BindView(R.id.spinnerHinhThucEdit)
    Spinner mSpinnerPurpose;
    @BindView(R.id.edtGiaEdit)
    TextInputEditText mEditTextPrice;
    @BindView(R.id.yesEdit)
    CheckBox mCheckBoxYes;
    @BindView(R.id.spinnerTinhTheoEdit)
    Spinner mSpinnerUnit;
    @BindView(R.id.edtSEdit)
    TextInputEditText mEditTextArea;
    @BindView(R.id.edtSoTangEdit)
    TextInputEditText mEditTextFloor;
    @BindView(R.id.edtSoPhongEdit)
    TextInputEditText mEditTextBedroom;
    @BindView(R.id.edtNhaTamEdit)
    TextInputEditText mEditTextBath;
    @BindView(R.id.edtNhaVeSinhEdit)
    TextInputEditText mEditTextTolet;
    @BindView(R.id.edtBanCongEdit)
    TextInputEditText mEditTextBalacy;
    @BindView(R.id.listviewNoiThatEdit)
    ExpandableHeightListView mListViewConvenient;
    @BindView(R.id.spinnerTinhEdit)
    Spinner mSpinnerCity;
    @BindView(R.id.spinnerHuyenEdit)
    Spinner mSpinnerDistrict;
    @BindView(R.id.edtDiaChiEdit)
    TextInputEditText mEditTextAddress;
    @BindView(R.id.edtXeBuytEdit)
    TextInputEditText mEditTextBus;
    @BindView(R.id.edtBenXeEdit)
    TextInputEditText mEditTextTrain;
    @BindView(R.id.edtSanBayEdit)
    TextInputEditText mEditTextAir;
    @BindView(R.id.edtBienEdit)
    TextInputEditText mEditTextSea;
    @BindView(R.id.edtNganHAngEdit)
    TextInputEditText mEditTextBanl;
    @BindView(R.id.spinerLoaiTinEdit)
    Spinner mSpinnerTypeNew;
    @BindView(R.id.txtNgayBatDauEdit)
    TextView mTextViewStartDay;
    @BindView(R.id.txtNgayKetThucEdit)
    TextView mTextViewEndDay;
    @BindView(R.id.txtSoNgayEdit)
    TextView mTextViewDay;
    @BindView(R.id.txtThanhTienEdit)
    TextView mTextViewTotal;
    @BindView(R.id.txtVATEdit)
    TextView mTextViewVAT;
    @BindView(R.id.txtPhiDangEdit)
    TextView mTextViewFree;
    @BindView(R.id.btnCapNhat)
    Button mButtonEdit;
    private GetDataDetail iGetDataDetail;
    CustemSpinner mCustemSpinner;
    private Date dateStart;
    final Calendar myCalendar = Calendar.getInstance();
    List<Conveniences> mConveniences;
    AmentiesAdapter mAmentiesAdapter;
    int idTinh, idHuyen;
    String huyenS,tinhS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);
        setToolBar();
        Intent intent = getIntent();
        final int id = intent.getIntExtra("Key_2", 0);
        iGetDataDetail = new DataDetailPre(this);
        iGetDataDetail.getDetailMovie(id);
        setSpinnerTinhHuyen();
        mTextViewStartDay.setText(ngay());
        setLoaiTin();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }


        };

        mTextViewEndDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(EditActivity.this, date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        getAmentions();
        //getExterior();
        setAmention();
        //setAExter();


        mButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int BX,Xb,SB,NH,B,yes;
                ArrayList<Integer> amen = new ArrayList<>();
                for (int i = 0 ; i< mConveniences.size();i++){
                    if (mConveniences.get(i).isChecked() == true){
                        amen.add(mConveniences.get(i).getId());
                    }
                }
                if (isEmpty(mEditTextTrain)){
                    BX = 0;
                }else BX = Integer.parseInt(mEditTextTrain.getText().toString());
                if (isEmpty(mEditTextBus)){
                    Xb = 0;
                }else Xb = Integer.parseInt(mEditTextBus.getText().toString());
                if (isEmpty(mEditTextAir)){
                    SB = 0;
                }else SB = Integer.parseInt(mEditTextAir.getText().toString());
                if (isEmpty(mEditTextSea)){
                    B = 0;
                }else B = Integer.parseInt(mEditTextSea.getText().toString());
                if (isEmpty(mEditTextBanl)){
                    NH = 0;
                }else NH = Integer.parseInt(mEditTextBanl.getText().toString());
                final HashMap<Integer, Integer> phoneNumbers= new HashMap<>();
                phoneNumbers.put(1, Xb);
                phoneNumbers.put(2,BX);
                phoneNumbers.put(3,NH);
                phoneNumbers.put(4,SB);
                phoneNumbers.put(5,B);
                if (mCheckBoxYes.isChecked()){
                    yes =1;
                }else yes = 0;

                Toast.makeText(EditActivity.this, "hahadhsds",
                        Toast.LENGTH_SHORT).show();
//                txttesst.setText(""+
//                                mEditTextTitle.getText().toString()+ mSpinnerPurpose.getSelectedItem().toString()+
//                        Float.parseFloat(mEditTextPrice.getText().toString())+Float.parseFloat(mEditTextArea.getText().toString())+
//                        mEditTextDescrip.getText().toString()+idHuyen + idTinh+(mSpinnerTypeNew.getSelectedItemPosition()+1)+
//                        mEditTextAddress.getText().toString() +
//                        (mSpinnerSpecies.getSelectedItemPosition()+1)+
//                        Integer.parseInt(mEditTextFloor.getText().toString())+
//                        Integer.parseInt(mEditTextBedroom.getText().toString())+
//                        Integer.parseInt(mEditTextBath.getText().toString())+
//                        Integer.parseInt(mEditTextBalacy.getText().toString())+
//                        Integer.parseInt(mEditTextTolet.getText().toString()) +
//                        mTextViewStartDay.getText().toString()+
//                        mTextViewEndDay.getText().toString()+
//                        mSpinnerUnit.getSelectedItem().toString()+
//                        yes +
//                        id);
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<DataPost> call = apiService.edit("Bearer "+ HomeActivity.TOKEN,
                        new DataPost(mEditTextTitle.getText().toString(),
                                mSpinnerPurpose.getSelectedItem().toString(),
                                Float.parseFloat(mEditTextPrice.getText().toString()),Float.parseFloat(
                                mEditTextArea.getText().toString()),
                                mEditTextDescrip.getText().toString(),idHuyen,idTinh,
                                mSpinnerTypeNew.getSelectedItemPosition()+1,
                                mEditTextAddress.getText().toString(), (float)getLocationFromAddress(
                                mEditTextAddress.getText().toString() + ", " + huyenS + ", " + tinhS + ", Việt Nam").lat,
                                (float)getLocationFromAddress(
                                        mEditTextAddress.getText().toString() + ", " + huyenS + ", " + tinhS + ", Việt Nam").lng,
                                mSpinnerSpecies.getSelectedItemPosition()+1,
                                Integer.parseInt(mEditTextFloor.getText().toString()),
                                Integer.parseInt(mEditTextBedroom.getText().toString()),
                                Integer.parseInt(mEditTextBath.getText().toString()),
                                Integer.parseInt(mEditTextBalacy.getText().toString()),
                                Integer.parseInt(mEditTextTolet.getText().toString()),
                                amen,phoneNumbers, mTextViewStartDay.getText().toString(),
                                mTextViewEndDay.getText().toString(),
                                mSpinnerUnit.getSelectedItem().toString(),yes),
                                id);
                call.enqueue(new Callback<DataPost>() {
                    @Override
                    public void onResponse(Call<DataPost> call, Response<DataPost> response) {
                        if (response.code() == 200) {
                            Toast.makeText(EditActivity.this, "Success",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<DataPost> call, Throwable t) {
                    }
                });
            }
        });
    }
    private boolean isEmpty(TextInputEditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
    private void setToolBar(){
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Cập nhật");
        mToolbar.setNavigationIcon(R.drawable.ic_back_detail);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorWhile));
    }
    private void setSpinnerTinhHuyen(){
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
                    mCustemSpinner = new CustemSpinner(EditActivity.this,
                            android.R.layout.simple_spinner_item, tp);
                    mSpinnerCity.setAdapter(mCustemSpinner);

                    mSpinnerCity.setOnItemSelectedListener(
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
                                                mCustemSpinner = new CustemSpinner(EditActivity.this,
                                                        android.R.layout.simple_spinner_item, huyen);
                                                mSpinnerDistrict.setAdapter(mCustemSpinner);

                                                mSpinnerDistrict.setOnItemSelectedListener(
                                                        new AdapterView.OnItemSelectedListener() {
                                                            @Override
                                                            public void onItemSelected(AdapterView<?> adapterView,
                                                                    View view, int j, long l) {
                                                                idTinh =response.body().get(i).getId();
                                                                idHuyen = response1.body().get(j).getId();
                                                                huyenS = mSpinnerDistrict.getSelectedItem().toString();
                                                                tinhS = mSpinnerCity.getSelectedItem().toString();
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


    @Override
    public void getDataDetailSuccess(final DataDetail movieDetails) {
        if (movieDetails.getImage() == null){
            mImageEdit.setImageResource(R.drawable.ic_user);
        }else  Picasso.get()
                .load("http://project-property.herokuapp.com/uploads/images/" + movieDetails.getImage())
                .into(mImageEdit);
        mEditTextTitle.setText(movieDetails.getTitle());
        mEditTextDescrip.setText(movieDetails.getDescription());
        if (movieDetails.getPurpose().equalsIgnoreCase("sale")){
            mSpinnerPurpose.setSelection(0);
            String[] testArray = getResources().getStringArray(R.array.ban);
            ArrayAdapter<String> spinnerArrayAdapter =
                    new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, testArray);
            mSpinnerUnit.setAdapter(spinnerArrayAdapter);
        }else {
            mSpinnerPurpose.setSelection(1);
            String[] testArray = getResources().getStringArray(R.array.thue);
            ArrayAdapter<String> spinnerArrayAdapter =
                    new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, testArray);
            mSpinnerUnit.setAdapter(spinnerArrayAdapter);
        }
        mSpinnerSpecies.setSelection(movieDetails.getPropertyType().getId()-1);
        mEditTextPrice.setText(movieDetails.getPrice()+"");
        if (movieDetails.getNegotiable()==1){
           mCheckBoxYes.setChecked(true);
        }else mCheckBoxYes.setChecked(false);

        for (int i=0;i< mSpinnerUnit.getCount();i++){
            if (mSpinnerUnit.getItemAtPosition(i).toString().equalsIgnoreCase(movieDetails.getUnit().toString())){
                mSpinnerUnit.setSelection(i);
            }
        }
        mEditTextArea.setText(""+movieDetails.getArea());
        mEditTextBedroom.setText(""+movieDetails.getDetail().getBedRoom());
        mEditTextFloor.setText(""+movieDetails.getDetail().getFloor());
        mEditTextBath.setText(""+movieDetails.getDetail().getBath());
        mEditTextTolet.setText(""+movieDetails.getDetail().getToilet());
        mEditTextBalacy.setText(""+movieDetails.getDetail().getBalcony());

        for (int i=0;i< mSpinnerCity.getCount();i++){
            if (mSpinnerCity.getItemAtPosition(i).toString().equalsIgnoreCase(movieDetails.getDistrict().getCity().getName())){
                mSpinnerCity.setSelection(i);
            }

            for (int j=0;j< mSpinnerDistrict.getCount();j++){
                if (mSpinnerDistrict.getItemAtPosition(j).toString().equalsIgnoreCase(movieDetails.getDistrict().getName())){
                    mSpinnerDistrict.setSelection(j);
                }
            }
        }
        mEditTextAddress.setText(movieDetails.getAddress());
        if (movieDetails.getDistances().size() == 0){
            mEditTextBus.setText(String.valueOf(0));
        }else mEditTextBus.setText(""+ movieDetails.getDistances().get(0).getPivot().getMeters());
        if (movieDetails.getDistances().size() == 0){
            mEditTextTrain.setText(String.valueOf(0));
        }else mEditTextTrain.setText(movieDetails.getDistances().get(1).getPivot().getMeters()+"");
        if (movieDetails.getDistances().size() == 0){
            mEditTextAir.setText(String.valueOf(0));
        }else mEditTextAir.setText(movieDetails.getDistances().get(2).getPivot().getMeters()+"");
        if (movieDetails.getDistances().size() == 0){
            mEditTextBanl.setText(String.valueOf(0));
        }else mEditTextBanl.setText(movieDetails.getDistances().get(3).getPivot().getMeters()+"");
        if (movieDetails.getDistances().size() == 0){
            mEditTextSea.setText(String.valueOf(0));
        }else mEditTextSea.setText(movieDetails.getDistances().get(4).getPivot().getMeters()+"");



        mSpinnerTypeNew.setSelection(movieDetails.getTypeId()-1);
        mTextViewStartDay.setText(movieDetails.getStartDate());
        mTextViewEndDay.setText(movieDetails.getEndDate());
        mSpinnerTypeNew.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0 ){
                    mTextViewFree.setText("Phí đăng tin: "+ Unit.formatPrice(
                            (long) (getDaysDifference(movieDetails.getStartDate(),movieDetails.getEndDate())*5000))+ " vnd");
                    mTextViewVAT.setText("Phí VAT (10%): "+ Unit.formatPrice(
                            (long) (getDaysDifference(movieDetails.getStartDate(),movieDetails.getEndDate())*5000) * 10/100) + " vnd");
                    mTextViewTotal.setText("THÀNH TIỀN: " +Unit.formatPrice(
                            (long) (getDaysDifference(movieDetails.getStartDate(),movieDetails.getEndDate())*5000) +
                                    ((getDaysDifference(movieDetails.getStartDate(),movieDetails.getEndDate())*5000) * 10/100))+ " vnd");

                }else if (i == 1){
                    mTextViewFree.setText("Phí đăng tin: "+ Unit.formatPrice(
                            (long) (getDaysDifference(movieDetails.getStartDate(),movieDetails.getEndDate())*20000))+ " vnd");
                    mTextViewVAT.setText("Phí VAT (10%): "+ Unit.formatPrice(
                            (long) (getDaysDifference(movieDetails.getStartDate(),movieDetails.getEndDate())*20000) * 10/100) + " vnd");
                    mTextViewTotal.setText("THÀNH TIỀN: " +Unit.formatPrice(
                            (long) (getDaysDifference(movieDetails.getStartDate(),movieDetails.getEndDate())*20000) +
                                    ((getDaysDifference(movieDetails.getStartDate(),movieDetails.getEndDate())*2000) * 10/100))+ " vnd");
                }else {
                    mTextViewFree.setText("Phí đăng tin: "+ Unit.formatPrice(
                            (long) (getDaysDifference(movieDetails.getStartDate(),movieDetails.getEndDate())*50000))+ " vnd");
                    mTextViewVAT.setText("Phí VAT (10%): "+ Unit.formatPrice(
                            (long) (getDaysDifference(movieDetails.getStartDate(),movieDetails.getEndDate())*50000) * 10/100) + " vnd");
                    mTextViewTotal.setText("THÀNH TIỀN: " +Unit.formatPrice(
                            (long) (getDaysDifference(movieDetails.getStartDate(),movieDetails.getEndDate())*50000) +
                                    ((getDaysDifference(movieDetails.getStartDate(),movieDetails.getEndDate())*50000) * 10/100))+ " vnd");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        if (movieDetails.getConveniences().size()!=0) {
            for (int i = 0; i < movieDetails.getConveniences().size(); i++) {
                for (int j = 0; j < mConveniences.size(); j++) {
                    if (movieDetails.getConveniences().get(i).getId() == mConveniences.get(j).getId()) {
                        mConveniences.get(j).setAction(true);
                    }
                }
            }
            mAmentiesAdapter.updateRecords(mConveniences);
        }
        int songay = getDaysDifference(movieDetails.getStartDate(),movieDetails.getEndDate());

        mTextViewDay.setText("Số ngày: "+ songay);
        if (mSpinnerTypeNew.getSelectedItemPosition() == 0) {
            mTextViewFree.setText("Phí đăng tin : " + Unit.formatPrice((long) (songay * 5000)));
            mTextViewVAT.setText("Phí VAT (10%): "+ Unit.formatPrice((long) (songay * 5000 *10/100)));
            mTextViewTotal.setText("THÀNH TIỀN: " + Unit.formatPrice(
                    (long) ((songay * 5000) + (songay * 5000 *10/100))) + " vnd");
        } else if (mSpinnerTypeNew.getSelectedItemPosition() == 1) {
            mTextViewFree.setText("Phí đăng tin : " + Unit.formatPrice((long) (songay * 20000)));
            mTextViewVAT.setText("Phí VAT (10%): "+ Unit.formatPrice((long) (songay * 20000 *10/100)));
            mTextViewTotal.setText("THÀNH TIỀN: " + Unit.formatPrice(
                    (long) ((songay * 20000) + (songay * 20000 *10/100))) + " vnd");
        } else {
            mTextViewFree.setText("Phí đăng tin : " + Unit.formatPrice((long) (songay * 50000)));
            mTextViewVAT.setText("Phí VAT (10%): "+ Unit.formatPrice((long) (songay * 50000 *10/100)));
            mTextViewTotal.setText("THÀNH TIỀN: " + Unit.formatPrice(
                    (long) ((songay * 50000) + (songay * 50000 *10/100))) + " vnd");
        }


    }

    @Override
    public void getDataDetailFailure() {

    }

    @Override
    public Context getContext() {
        return null;
    }

    private void setLoaiTin() {
        mSpinnerTypeNew.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0 ){
                    mTextViewFree.setText("Phí đăng tin: "+ Unit.formatPrice(
                            (long) (getDaysDifferenceDate(dateStart,myCalendar.getTime())*5000))+ " vnd");
                    mTextViewVAT.setText("Phí VAT (10%): "+ Unit.formatPrice(
                            (long) (getDaysDifferenceDate(dateStart,myCalendar.getTime())*5000) * 10/100) + " vnd");
                    mTextViewTotal.setText("THÀNH TIỀN: " +Unit.formatPrice(
                            (long) (getDaysDifferenceDate(dateStart,myCalendar.getTime())*5000) +
                                    ((getDaysDifferenceDate(dateStart,myCalendar.getTime())*5000) * 10/100))+ " vnd");

                }else if (i == 1){
                    mTextViewFree.setText("Phí đăng tin: "+ Unit.formatPrice(
                            (long) (getDaysDifferenceDate(dateStart,myCalendar.getTime())*20000))+ " vnd");
                    mTextViewVAT.setText("Phí VAT (10%): "+ Unit.formatPrice(
                            (long) (getDaysDifferenceDate(dateStart,myCalendar.getTime())*20000) * 10/100) + " vnd");
                    mTextViewTotal.setText("THÀNH TIỀN: " +Unit.formatPrice(
                            (long) (getDaysDifferenceDate(dateStart,myCalendar.getTime())*20000) +
                                    ((getDaysDifferenceDate(dateStart,myCalendar.getTime())*2000) * 10/100))+ " vnd");
                }else {
                    mTextViewFree.setText("Phí đăng tin: "+ Unit.formatPrice(
                            (long) (getDaysDifferenceDate(dateStart,myCalendar.getTime())*50000))+ " vnd");
                    mTextViewVAT.setText("Phí VAT (10%): "+ Unit.formatPrice(
                            (long) (getDaysDifferenceDate(dateStart,myCalendar.getTime())*50000) * 10/100) + " vnd");
                    mTextViewTotal.setText("THÀNH TIỀN: " +Unit.formatPrice(
                            (long) (getDaysDifferenceDate(dateStart,myCalendar.getTime())*50000) +
                                    ((getDaysDifferenceDate(dateStart,myCalendar.getTime())*50000) * 10/100))+ " vnd");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public int getDaysDifference(String startDate, String endDate)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = format.parse(startDate);
            Date date2 = format.parse(endDate);
            if(startDate==null||endDate==null)
                return 0;
            return (int)(1+ TimeUnit.DAYS.convert(date2.getTime() - date1.getTime(),
                    TimeUnit.MILLISECONDS));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;



    }
    private String ngay(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(tomorrow);
        dateStart = tomorrow;
        return  formattedDate;
    }
    public int getDaysDifferenceDate(Date startDate, Date endDate)
    {
        if(startDate==null||endDate==null)
            return 0;
        return (int)(1+TimeUnit.DAYS.convert(endDate.getTime() - startDate.getTime(), TimeUnit.MILLISECONDS));

    }

    private void getAmentions() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Conveniences>> call = apiService.getConvenience();
        call.enqueue(new Callback<List<Conveniences>>() {
            @Override
            public void onResponse(Call<List<Conveniences>> call, final Response<List<Conveniences>> response) {
                if (response.code() == 200) {

                    mConveniences = response.body();
                    for (int  i = 0 ; i< mConveniences.size();i++){
                        mConveniences.get(i).setAction(false);
                    }
                    mAmentiesAdapter = new AmentiesAdapter(getApplicationContext(), mConveniences);
                    mListViewConvenient.setAdapter(mAmentiesAdapter);

                } else {
                }
            }

            @Override
            public void onFailure(Call<List<Conveniences>> call, Throwable t) {
            }
        });


    }
    private void setAmention(){
        mListViewConvenient.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        mListViewConvenient.setExpanded(true);
        mListViewConvenient.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Conveniences model = mConveniences.get(i);

                if (model.isChecked())
                    model.setAction(false);

                else
                    model.setAction(true);

                mConveniences.set(i, model);

                //now update mAmentiesAdapter
                mAmentiesAdapter.updateRecords(mConveniences);
            }
        });
    }

    private void updateLabel() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        mTextViewEndDay.setText(df.format(myCalendar.getTime()));
        mTextViewDay.setText("Số ngày: " + getDaysDifferenceDate(dateStart, myCalendar.getTime()));

        if (mSpinnerTypeNew.getSelectedItemPosition() == 0) {
            mTextViewFree.setText("Phí đăng tin: " + Unit.formatPrice(
                    (long) (getDaysDifferenceDate(dateStart, myCalendar.getTime()) * 5000)) + " vnd");
            mTextViewVAT.setText("Phí VAT (10%): " + Unit.formatPrice(
                    (long) (getDaysDifferenceDate(dateStart, myCalendar.getTime()) * 5000) * 10
                            / 100) + " vnd");
            mTextViewTotal.setText("THÀNH TIỀN: " + Unit.formatPrice(
                    (long) (getDaysDifferenceDate(dateStart, myCalendar.getTime()) * 5000) + ((
                            getDaysDifferenceDate(dateStart, myCalendar.getTime())
                                    * 5000) * 10 / 100)) + " vnd");
        } else if (mSpinnerTypeNew.getSelectedItemPosition() == 1) {
            mTextViewFree.setText("Phí đăng tin: " + Unit.formatPrice(
                    (long) (getDaysDifferenceDate(dateStart, myCalendar.getTime()) * 20000)) + " vnd");
            mTextViewVAT.setText("Phí VAT (10%): " + Unit.formatPrice(
                    (long) (getDaysDifferenceDate(dateStart, myCalendar.getTime()) * 20000) * 10
                            / 100) + " vnd");
            mTextViewTotal.setText("THÀNH TIỀN: " + Unit.formatPrice(
                    (long) (getDaysDifferenceDate(dateStart, myCalendar.getTime()) * 20000) + ((
                            getDaysDifferenceDate(dateStart, myCalendar.getTime())
                                    * 2000) * 10 / 100)) + " vnd");
        } else {
            mTextViewFree.setText("Phí đăng tin: " + Unit.formatPrice(
                    (long) (getDaysDifferenceDate(dateStart, myCalendar.getTime()) * 50000)) + " vnd");
            mTextViewVAT.setText("Phí VAT (10%): " + Unit.formatPrice(
                    (long) (getDaysDifferenceDate(dateStart, myCalendar.getTime()) * 50000) * 10
                            / 100) + " vnd");
            mTextViewTotal.setText("THÀNH TIỀN: " + Unit.formatPrice(
                    (long) (getDaysDifferenceDate(dateStart, myCalendar.getTime()) * 50000) + ((
                            getDaysDifferenceDate(dateStart, myCalendar.getTime())
                                    * 50000) * 10 / 100)) + " vnd");
        }
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
}
