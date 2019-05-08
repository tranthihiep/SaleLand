package com.example.hiep.bds.view.postAD;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.hiep.bds.R;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ADInforActivity extends AppCompatActivity {
    Toolbar mToolbar;
    ImageView imgad;
    TextInputEditText mEditTextTitleAD, mEditTextGiaAD, mEditTextSAD, mEditTextMotaAD,
            mEditTextNhaVS, mEditTextTang, mEditTextPhong, mEditTextTam, mEditTextBanCong;
    CheckBox mCheckBox;
    Button mButtonTTAD;
    Spinner mSpinner;
    TextView txtUnit;
    int NT, NVS, P, BC, T;
    int yes = 0;
    private final int REQUEST_CODE_CAMERA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_info);
        initPermission();
        init();
        setToolbar();
        imgad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }
        });
        mButtonTTAD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(mEditTextNhaVS)) {
                    NVS = 0;
                } else {
                    NVS = Integer.parseInt(mEditTextNhaVS.getText().toString());
                }

                if (isEmpty(mEditTextTam)) {
                    NT = 0;
                } else {
                    NT = Integer.parseInt(mEditTextTam.getText().toString());
                }
                if (isEmpty(mEditTextTang)) {
                    T = 0;
                } else {
                    T = Integer.parseInt(mEditTextTang.getText().toString());
                }
                if (isEmpty(mEditTextBanCong)) {
                    BC = 0;
                } else {
                    BC = Integer.parseInt(mEditTextBanCong.getText().toString());
                }
                if (isEmpty(mEditTextPhong)) {
                    P = 0;
                } else {
                    P = Integer.parseInt(mEditTextPhong.getText().toString());
                }
                if (mCheckBox.isChecked()) {
                    yes = 1;
                }
                if (isEmpty(mEditTextTitleAD)) {
                    Toast.makeText(ADInforActivity.this, "Vui lòng không để trống chủ đề !",
                            Toast.LENGTH_SHORT).show();
                } else if (isEmpty(mEditTextGiaAD)) {
                    Toast.makeText(ADInforActivity.this, "Vui lòng không để trống giá !",
                            Toast.LENGTH_SHORT).show();
                } else if (isEmpty(mEditTextSAD)) {
                    Toast.makeText(ADInforActivity.this, "Vui lòng không để trống diện tích  !",
                            Toast.LENGTH_SHORT).show();
                } else if (isEmpty(mEditTextMotaAD)) {
                    Toast.makeText(ADInforActivity.this, "Vui lòng không để trống mô tả !",
                            Toast.LENGTH_SHORT).show();
                } else if (mEditTextMotaAD.length() < 50) {
                    Toast.makeText(ADInforActivity.this, "Yêu cầu mô tả trên 50 ký tự",
                            Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor editor =
                            getSharedPreferences("AD", MODE_PRIVATE).edit();
                    editor.putString("title", mEditTextTitleAD.getText().toString());
                    editor.putFloat("gia", Float.parseFloat(mEditTextGiaAD.getText().toString()));
                    editor.putString("unit", mSpinner.getSelectedItem().toString());
                    editor.putFloat("dientich",
                            Float.parseFloat(mEditTextSAD.getText().toString()));
                    editor.putString("mota", mEditTextMotaAD.getText().toString());
                    editor.putInt("T", T);
                    editor.putInt("P", P);
                    editor.putInt("NVS", NVS);
                    editor.putInt("NT", NT);
                    editor.putInt("BC", BC);
                    editor.putInt("yes", yes);
                    editor.apply();
                    Intent intent = new Intent(ADInforActivity.this, AmentiesActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                //Permisson don't granted
                if (shouldShowRequestPermissionRationale(
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    Toast.makeText(ADInforActivity.this, "Permission isn't granted ",
                            Toast.LENGTH_SHORT).show();
                }
                // Permisson don't granted and dont show dialog again.
                else {
                    Toast.makeText(ADInforActivity.this,
                            "Permisson don't granted and dont show dialog again ",
                            Toast.LENGTH_SHORT).show();
                }
                //Register permission
                requestPermissions(new String[] { Manifest.permission.READ_EXTERNAL_STORAGE }, 1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(ADInforActivity.this, "Permision Write File is Granted",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ADInforActivity.this, "Permision Write File is Denied",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Thông tin bất động sản");
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
        txtUnit = findViewById(R.id.txtUnit);
        mSpinner = findViewById(R.id.spinnerThue);
        mToolbar = findViewById(R.id.toolbarAD);
        imgad = findViewById(R.id.imgad);
        mEditTextTitleAD = findViewById(R.id.edtTitleAD);
        mEditTextGiaAD = findViewById(R.id.edtGiaAD);
        mEditTextSAD = findViewById(R.id.edtSAD);
        mEditTextMotaAD = findViewById(R.id.edtMotaAD);
        mButtonTTAD = findViewById(R.id.btnTTAD);
        mEditTextBanCong = findViewById(R.id.edtBanCong);
        mEditTextNhaVS = findViewById(R.id.edtNhaVeSinh);
        mEditTextTam = findViewById(R.id.edtNhaTam);
        mEditTextPhong = findViewById(R.id.edtSoPhong);
        mEditTextTang = findViewById(R.id.edtSoTang);
        mCheckBox = findViewById(R.id.yes);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (PorposeActivity.HIHI == true) {
            txtUnit.setText("Đơn vị theo giá");
            String[] testArray = getResources().getStringArray(R.array.ban);
            ArrayAdapter<String> spinnerArrayAdapter =
                    new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, testArray);
            mSpinner.setAdapter(spinnerArrayAdapter);
        } else {
            txtUnit.setText("Giá thuê theo");
            String[] testArray = getResources().getStringArray(R.array.thue);
            ArrayAdapter<String> spinnerArrayAdapter =
                    new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, testArray);
            mSpinner.setAdapter(spinnerArrayAdapter);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap =
                        MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                imgad.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private boolean isEmpty(TextInputEditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
}
