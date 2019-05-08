package com.example.hiep.bds.view.postAD;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.hiep.bds.R;

public class PorposeActivity extends AppCompatActivity {

    Toolbar mToolbar;
    public static boolean HIHI = false;
    private RadioGroup radioGroup;
    private RadioButton radionSale, radioRent;
    String select;
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
                        HIHI = true;
                        select= "sale";
                        break;
                    case R.id.radioRent:
                        HIHI = false;
                        select = "rent";
                        break;
                }
            }
        });

        btnCOntinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (radioGroup.getCheckedRadioButtonId() != -1)
                {
                    SharedPreferences.Editor editor = getSharedPreferences("PORPOSE", MODE_PRIVATE).edit();
                    editor.putString("porpose", select);
                    editor.apply();
                    Intent intent = new Intent(PorposeActivity.this,PropertyTypeActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(PorposeActivity.this, "Vui lòng chọn", Toast.LENGTH_SHORT).show();
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
