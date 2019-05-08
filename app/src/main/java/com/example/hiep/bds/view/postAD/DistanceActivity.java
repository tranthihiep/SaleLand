package com.example.hiep.bds.view.postAD;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.hiep.bds.R;
import java.util.ArrayList;

public class DistanceActivity extends AppCompatActivity {
    @BindView(R.id.toolbarAD)
    Toolbar mToolbar;
    @BindView(R.id.btnTTAD)
    Button mButton;
    @BindView(R.id.edtBenXe)
    TextInputEditText mEditTextBenXe;
    @BindView(R.id.edtBien)
    TextInputEditText mEditTextBien;
    @BindView(R.id.edtXeBuyt)
    TextInputEditText mEditTextXeBuyt;
    @BindView(R.id.edtSanBay)
    TextInputEditText mEditTextSanBay;
    @BindView(R.id.edtNganHAng)
    TextInputEditText mEditTextNganHang;
    int Xb,BX,B,SB,NH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance);
        ButterKnife.bind(this);
        setToolbar();
        Bundle bundle = getIntent().getExtras();
        final ArrayList<Integer> ngoaithat = bundle.getIntegerArrayList("ngoaithat");
        final ArrayList<Integer> noithat = bundle.getIntegerArrayList("noithat");
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isEmpty(mEditTextBenXe)){
                    BX = 0;
                }else BX = Integer.parseInt(mEditTextBenXe.getText().toString());
                if (isEmpty(mEditTextXeBuyt)){
                    Xb = 0;
                }else Xb = Integer.parseInt(mEditTextXeBuyt.getText().toString());
                if (isEmpty(mEditTextSanBay)){
                    SB = 0;
                }else SB = Integer.parseInt(mEditTextSanBay.getText().toString());
                if (isEmpty(mEditTextBien)){
                    B = 0;
                }else B = Integer.parseInt(mEditTextBien.getText().toString());
                if (isEmpty(mEditTextNganHang)){
                    NH = 0;
                }else NH = Integer.parseInt(mEditTextNganHang.getText().toString());
                SharedPreferences.Editor editor = getSharedPreferences("DC", MODE_PRIVATE).edit();
                editor.putInt("XB", Xb);
                editor.putInt("BX", BX);
                editor.putInt("NH", NH);
                editor.putInt("SB", SB);
                editor.putInt("B", B);
                editor.apply();

                Intent intent = new Intent(DistanceActivity.this,DataPostActivity.class);
                Bundle bundle = new Bundle();
                bundle.putIntegerArrayList("ngth",ngoaithat);
                bundle.putIntegerArrayList("noth",noithat);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Vị trí");
        mToolbar.setNavigationIcon(R.drawable.ic_back_detail);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorWhile));
    }
    private boolean isEmpty(TextInputEditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
}
