package com.example.hiep.bds.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.example.hiep.bds.R;

public class PorposeActivity extends AppCompatActivity {

    Toolbar mToolbar;
    private RadioGroup radioGroup;
    private RadioButton radionSale, radioRent;
    private Button btnCOntinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        setContentView(R.layout.activity_purpose);
        init();
        setToolbar();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.radiosale:
                        // do operations specific to this selection
                        break;
                    case R.id.radioRent:
                        // do operations specific to this selection
                        break;
                }
            }
        });
        btnCOntinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PorposeActivity.this,PropertyTypeActivity.class);
                startActivity(intent);
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
        getSupportActionBar().setTitle("Bạn đăng tin");
        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorWhile));

    }

    private void init() {
        mToolbar = findViewById(R.id.idPurpose);
        radioGroup = findViewById(R.id.radioGroup);
        radionSale = findViewById(R.id.radiosale);
        radioRent = findViewById(R.id.radioRent);
        btnCOntinue = findViewById(R.id.btnTieptuc);

    }
}
