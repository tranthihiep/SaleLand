package com.example.hiep.bds.view.postAD;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.hiep.bds.R;

public class PropertyTypeActivity extends AppCompatActivity {
    Toolbar mToolbar;
    private RadioGroup radioGroup;
    private RadioButton radioBietThu, radioNhaTro, radioDat,radioChungCu,radioVanPhong,
    radioNhaO, radioTrangTrai, radioNhakho;
    private Button btnCOntinue;
    int type = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        setContentView(R.layout.activity_property_type);
        init();
        setToolbar();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radioChungCu:
                        type = 1;
                        break;
                    case R.id.radioNhaO:
                        type = 2;
                        break;
                    case R.id.radioDat:
                        type = 3;
                        break;
                    case R.id.radioVanPhong:
                        type = 4;
                        break;
                    case R.id.radioBietThu:
                        type = 5;
                        break;
                }
            }
        });

        btnCOntinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioGroup.getCheckedRadioButtonId() != -1)
                {
                    SharedPreferences.Editor editor = getSharedPreferences("TYPE", MODE_PRIVATE).edit();
                    editor.putInt("loaiBDS", type);
                    editor.apply();
                    Intent intent = new Intent(PropertyTypeActivity.this,LocationInforActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(PropertyTypeActivity.this, "Vui lòng chọn", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationIcon(R.drawable.ic_back_detail);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setTitle("Chọn danh mục đăng tin");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorWhile));

    }

    private void init() {
        mToolbar = findViewById(R.id.toolbarType);
        radioGroup = findViewById(R.id.groupType);
        radioBietThu = findViewById(R.id.radioBietThu);
        radioNhaO = findViewById(R.id.radioNhaO);
        radioDat = findViewById(R.id.radioDat);
        radioChungCu = findViewById(R.id.radioChungCu);
        radioVanPhong = findViewById(R.id.radioVanPhong);
        btnCOntinue = findViewById(R.id.btnType);

    }
}
