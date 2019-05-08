package com.example.hiep.bds.view.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.hiep.bds.R;
import com.example.hiep.bds.model.Login;
import com.example.hiep.bds.model.User;
import com.example.hiep.bds.utilts.ApiClient;
import com.example.hiep.bds.utilts.ApiInterface;
import com.example.hiep.bds.view.home.HomeActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.toolbarLogin)
    Toolbar mToolbar;

    @BindView(R.id.edtEmailLogin)
    TextInputEditText mInputEditTextEmail;

    @BindView(R.id.edtPassLogin)
    TextInputEditText mInputEditTextPass;

    @BindView(R.id.btnDangNhap)
    Button mButtonDN;

    @BindView(R.id.btnDangKy)
    Button mButtonDK;

    ApiInterface mAPIService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setToolbar();
        mAPIService = ApiClient.getClient().create(ApiInterface.class);
        mButtonDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(mInputEditTextEmail) || isEmpty(mInputEditTextPass)){
                    Toast.makeText(LoginActivity.this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {

                loginPost(new User(mInputEditTextEmail.getText().toString(),
                        mInputEditTextPass.getText().toString()));
            }}
        });
        mButtonDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intesnt = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intesnt);
            }
        });


    }
    private boolean isEmpty( TextInputEditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }
    private void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Đăng nhập");
        mToolbar.setNavigationIcon(R.drawable.ic_back_detail);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorWhile));
    }

    public void loginPost(User user) {
        mAPIService.login(user).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if (response.code() == 200) {
                    SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
                    SharedPreferences.Editor Ed = sp.edit();
                    Ed.putString("token", response.body().getAccessToken());
                    Ed.commit();
                    mInputEditTextEmail.setText("");
                    mInputEditTextPass.setText("");
                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                                startActivity(intent);
                }else {
                    Toast.makeText(LoginActivity.this, "Email hoặc mật khẩu không đúng",
                            Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                //Log.e(TAG, "Unable to submit post to API.");
                Toast.makeText(LoginActivity.this, "Email hoặc mật khẩu không đúng",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
