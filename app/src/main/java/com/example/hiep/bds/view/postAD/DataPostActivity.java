package com.example.hiep.bds.view.postAD;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.hiep.bds.R;
import com.example.hiep.bds.model.Dis;
import com.example.hiep.bds.model.Distance;
import com.example.hiep.bds.model.Pivot;
import com.example.hiep.bds.model.postModel.DataPost;
import com.example.hiep.bds.utilts.ApiClient;
import com.example.hiep.bds.utilts.ApiInterface;
import com.example.hiep.bds.utilts.Unit;
import com.example.hiep.bds.view.home.HomeActivity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataPostActivity extends AppCompatActivity  {
    @BindView(R.id.toolbarADP)
    Toolbar mToolbar;
    @BindView(R.id.spinnerPost)
    Spinner mSpinner;
    @BindView(R.id.txtInfor)
    TextView mTextViewInfor;
    @BindView(R.id.txtDonGia)
    TextView mTextViewDonGia;
    @BindView(R.id.txtNgayBatDau)
    TextView mTextViewNgayBatDau;
    @BindView(R.id.txtNgayKetThuc)
    TextView mTextViewNgayKetThuc;
    @BindView(R.id.txtSoNgay)
    TextView mTextViewSoNgay;
    @BindView(R.id.txtPhiDang)
    TextView mTextViewPhiDang;
    @BindView(R.id.txtVAT)
    TextView mTextViewVAT;
    @BindView(R.id.txtThanhTien)
    TextView mTextViewThanhTien;
    @BindView(R.id.btnDangTin)
    Button btnDangTin;
    @BindView(R.id.test)
    TextView test;
    Date dateStart;
    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_data_post);

        ButterKnife.bind(this);
        setToolbar();
        setLoaiTin();
        SharedPreferences s1 = getSharedPreferences("PORPOSE", MODE_PRIVATE);
        SharedPreferences s2 = getSharedPreferences("TYPE", MODE_PRIVATE);
        SharedPreferences s3 = getSharedPreferences("LOCATION", MODE_PRIVATE);
        SharedPreferences s4 = getSharedPreferences("AD", MODE_PRIVATE);
        SharedPreferences s5 = getSharedPreferences("DC", MODE_PRIVATE);


        final String porpose = s1.getString("porpose", "");

         final int type = s2.getInt("type",0);

         final String diachi = s3.getString("diachi","") ;
         final int tinh = s3.getInt("tinh",0);
        final int huyen = s3.getInt("huyen",0);
         final Float lat = s3.getFloat("lat",0);
        final Float  longti = s3.getFloat("long",0);


        final String title = s4.getString("title","");
        final Float gia = s4.getFloat("gia",0);
        final String unit = s4.getString("unit","");
        final Float dientich= s4.getFloat("dientich",0);
        final String  mota =  s4.getString("mota","");
        final int  nhatam= s4.getInt("NT",0);
        final int  floor=s4.getInt("T",0);
        final int  bancong=s4.getInt("BC",0);
        final int phongngu=s4.getInt("P",0);
        final int toalet=s4.getInt("NVS",0);
        final int yes = s4.getInt("yes",0);

        final int XB=s5.getInt("XB",0);
        final int BX=s4.getInt("BX",0);
        final int NH=s4.getInt("NH",0);
        final int SB=s4.getInt("SB",0);
        final int B=s4.getInt("B",0);

        Bundle bundle = getIntent().getExtras();
        final ArrayList<Integer> ngoaithat = bundle.getIntegerArrayList("ngth");
        final ArrayList<Integer> noithat = bundle.getIntegerArrayList("noth");
        final ArrayList<Dis> distance = new ArrayList<>();
        distance.add(new Dis(1,XB));
        distance.add(new Dis(2,BX));
        distance.add(new Dis(3,NH));
        distance.add(new Dis(4,SB));
        distance.add(new Dis(5,B));

        ngoaithat.addAll(noithat);
        final List<String> image = new ArrayList<>();
        image.add("dsdsdfds");
        mTextViewNgayBatDau.setText(ngay());

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                mTextViewNgayKetThuc.setText(df.format(myCalendar.getTime()));
                mTextViewSoNgay.setText("Số ngày: " + getDaysDifference(dateStart, myCalendar.getTime()));

                if (mSpinner.getSelectedItemPosition() == 0) {
                    mTextViewPhiDang.setText("Phí đăng tin: " + Unit.formatPrice(
                            (long) (getDaysDifference(dateStart, myCalendar.getTime()) * 2000)) + " vnd");
                    mTextViewVAT.setText("Phí VAT (10%): " + Unit.formatPrice(
                            (long) (getDaysDifference(dateStart, myCalendar.getTime()) * 2000) * 10
                                    / 100) + " vnd");
                    mTextViewThanhTien.setText("THÀNH TIỀN: " + Unit.formatPrice(
                            (long) (getDaysDifference(dateStart, myCalendar.getTime()) * 2000) + ((
                                    getDaysDifference(dateStart, myCalendar.getTime())
                                            * 2000) * 10 / 100)) + " vnd");
                } else if (mSpinner.getSelectedItemPosition() == 1) {
                    mTextViewPhiDang.setText("Phí đăng tin: " + Unit.formatPrice(
                            (long) (getDaysDifference(dateStart, myCalendar.getTime()) * 20000)) + " vnd");
                    mTextViewVAT.setText("Phí VAT (10%): " + Unit.formatPrice(
                            (long) (getDaysDifference(dateStart, myCalendar.getTime()) * 20000) * 10
                                    / 100) + " vnd");
                    mTextViewThanhTien.setText("THÀNH TIỀN: " + Unit.formatPrice(
                            (long) (getDaysDifference(dateStart, myCalendar.getTime()) * 20000) + ((
                                    getDaysDifference(dateStart, myCalendar.getTime())
                                            * 2000) * 10 / 100)) + " vnd");
                } else {
                    mTextViewPhiDang.setText("Phí đăng tin: " + Unit.formatPrice(
                            (long) (getDaysDifference(dateStart, myCalendar.getTime()) * 50000)) + " vnd");
                    mTextViewVAT.setText("Phí VAT (10%): " + Unit.formatPrice(
                            (long) (getDaysDifference(dateStart, myCalendar.getTime()) * 50000) * 10
                                    / 100) + " vnd");
                    mTextViewThanhTien.setText("THÀNH TIỀN: " + Unit.formatPrice(
                            (long) (getDaysDifference(dateStart, myCalendar.getTime()) * 50000) + ((
                                    getDaysDifference(dateStart, myCalendar.getTime())
                                            * 50000) * 10 / 100)) + " vnd");
                }
            }
        };

        mTextViewNgayKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(DataPostActivity.this, date, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        btnDangTin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getDaysDifference(dateStart, myCalendar.getTime()) < 7){
                    Toast.makeText(DataPostActivity.this, "Yêu cầu trên 7 ngày", Toast.LENGTH_SHORT).show();
                }else {

//                    sendPost(new DataPost(title, porpose,gia,dientich,mota,huyen,tinh,
//                            mSpinner.getSelectedItemPosition()+1,diachi,lat,longti,type,floor,
//                            phongngu,
//                            nhatam,bancong,toalet,ngoaithat,null,mTextViewNgayBatDau.getText().toString(),
//                            mTextViewNgayKetThuc.getText().toString(),
//                            unit,yes
//                            ));
                    ApiInterface mAPIService = ApiClient.getClient().create(ApiInterface.class);
                    mAPIService.postData("Bearer "+ HomeActivity.TOKEN, new DataPost(title, porpose,gia,dientich,mota,huyen,tinh,
                            mSpinner.getSelectedItemPosition()+1,diachi,lat,longti,type,floor,
                            phongngu,
                            nhatam,bancong,toalet,ngoaithat,distance,mTextViewNgayBatDau.getText().toString(),
                            mTextViewNgayKetThuc.getText().toString(),
                            unit,yes,image
                    )).enqueue(new Callback<DataPost>() {
                        @Override
                        public void onResponse(Call<DataPost> call, Response<DataPost> response) {
                            Toast.makeText(DataPostActivity.this, "Tạo bài biết thành công. Bài viết sẽ được sớm cập nhật, vui lòng check email.", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<DataPost> call, Throwable t) {
                            //Log.e(TAG, "Unable to submit post to API.");
                        }
                    });


                }
            }
        });
    }

    public int getDaysDifference(Date startDate, Date endDate)
    {
        if(startDate==null||endDate==null)
            return 0;
        return (int)(1+TimeUnit.DAYS.convert(endDate.getTime() - startDate.getTime(), TimeUnit.MILLISECONDS));

    }
    private void setLoaiTin() {
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0 ){
                    mTextViewInfor.setText("Tin thường : Là loại tin đăng bằng chữ màu đen, khung màu trắng, không băng rôn");
                    mTextViewDonGia.setText("Đơn giá : 5,000 đồng / Ngày");

                    mTextViewPhiDang.setText("Phí đăng tin: "+ Unit.formatPrice(
                            (long) (getDaysDifference(dateStart,myCalendar.getTime())*5000))+ " vnd");
                    mTextViewVAT.setText("Phí VAT (10%): "+ Unit.formatPrice(
                            (long) (getDaysDifference(dateStart,myCalendar.getTime())*5000) * 10/100) + " vnd");
                    mTextViewThanhTien.setText("THÀNH TIỀN: " +Unit.formatPrice(
                            (long) (getDaysDifference(dateStart,myCalendar.getTime())*5000) +
                                    ((getDaysDifference(dateStart,myCalendar.getTime())*5000) * 10/100))+ " vnd");

                }else if (i == 1){
                    mTextViewInfor.setText("Tin cao cấp : Là loại tin đăng bằng chữ màu xanh, khung màu vàng, có 1 băng rôn");
                    mTextViewDonGia.setText("Đơn giá : 20,000 đồng / Ngày");
                    mTextViewPhiDang.setText("Phí đăng tin: "+ Unit.formatPrice(
                            (long) (getDaysDifference(dateStart,myCalendar.getTime())*20000))+ " vnd");
                    mTextViewVAT.setText("Phí VAT (10%): "+ Unit.formatPrice(
                            (long) (getDaysDifference(dateStart,myCalendar.getTime())*20000) * 10/100) + " vnd");
                    mTextViewThanhTien.setText("THÀNH TIỀN: " +Unit.formatPrice(
                            (long) (getDaysDifference(dateStart,myCalendar.getTime())*20000) +
                                    ((getDaysDifference(dateStart,myCalendar.getTime())*2000) * 10/100))+ " vnd");
                }else {
                    mTextViewInfor.setText("Tin Vip : Là loại tin đăng bằng chữ màu đỏ, khung màu cam, có 2 băng rôn");
                    mTextViewDonGia.setText("Đơn giá : 50,000 đồng / Ngày");
                    mTextViewPhiDang.setText("Phí đăng tin: "+ Unit.formatPrice(
                            (long) (getDaysDifference(dateStart,myCalendar.getTime())*50000))+ " vnd");
                    mTextViewVAT.setText("Phí VAT (10%): "+ Unit.formatPrice(
                            (long) (getDaysDifference(dateStart,myCalendar.getTime())*50000) * 10/100) + " vnd");
                    mTextViewThanhTien.setText("THÀNH TIỀN: " +Unit.formatPrice(
                            (long) (getDaysDifference(dateStart,myCalendar.getTime())*50000) +
                                    ((getDaysDifference(dateStart,myCalendar.getTime())*50000) * 10/100))+ " vnd");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Lịch đăng tin");
        mToolbar.setNavigationIcon(R.drawable.ic_back_detail);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorWhile));
    }
    public void sendPost(DataPost user) {
        ApiInterface mAPIService = ApiClient.getClient().create(ApiInterface.class);
        mAPIService.postData("Bearer "+ HomeActivity.TOKEN, user).enqueue(new Callback<DataPost>() {
            @Override
            public void onResponse(Call<DataPost> call, Response<DataPost> response) {
                Toast.makeText(getApplicationContext(), "Success " + response.body().getId(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<DataPost> call, Throwable t) {
                //Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }
}
