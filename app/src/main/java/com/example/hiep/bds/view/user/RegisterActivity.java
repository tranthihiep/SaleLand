package com.example.hiep.bds.view.user;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.hiep.bds.R;
import com.example.hiep.bds.model.User;
import com.example.hiep.bds.utilts.ApiClient;
import com.example.hiep.bds.utilts.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.toolbarDangKi)
    Toolbar mToolbar;
    @BindView(R.id.edtTenDangKy)
    TextInputEditText mInputEditTextTen;
    @BindView(R.id.edtEmail)
    TextInputEditText mInputEditTextEmail;
    @BindView(R.id.edtSDT)
    TextInputEditText mInputEditTextSDT;
    @BindView(R.id.edtAddress)
    TextInputEditText mInputEditTextAddress;
    @BindView(R.id.edtPass)
    TextInputEditText mInputEditTextPass;
    @BindView(R.id.edtXacNhanPass)
    TextInputEditText mInputEditTextXNPass;
    @BindView(R.id.btnDangKy)
    Button btnDangKi;
    ApiInterface mAPIService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        setToolbar();
        mAPIService = ApiClient.getClient().create(ApiInterface.class);
        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((mInputEditTextTen.getText().toString().trim().length() == 0)
                        || (mInputEditTextEmail.getText().toString().trim().length()
                        == 0)
                        || (mInputEditTextSDT.getText().toString().trim().length()
                        == 0)
                        || (mInputEditTextAddress.getText().toString().trim().length() == 0)
                        || (mInputEditTextPass.getText().toString().trim().length() == 0)) {
                    Toast.makeText(RegisterActivity.this, "Vui lòng không để trống",
                            Toast.LENGTH_SHORT).show();
                } else if (isEmail(mInputEditTextEmail) == false) {
                    Toast.makeText(RegisterActivity.this, "Email không đúng định dạng",
                            Toast.LENGTH_SHORT).show();
                } else if (mInputEditTextPass.getText().toString().trim().length() <= 5) {
                    Toast.makeText(RegisterActivity.this, "Mật khẩu yêu cầu ít nhất 6 ký tự",
                            Toast.LENGTH_SHORT).show();
                } else if (!mInputEditTextPass.getText()
                        .toString()
                        .trim()
                        .equalsIgnoreCase(mInputEditTextXNPass.getText().toString().trim())) {
                    Toast.makeText(RegisterActivity.this, "Mật khẩu không khớp, vui lòng nhập lại",
                            Toast.LENGTH_SHORT).show();
                    mInputEditTextPass.setText("");
                    mInputEditTextXNPass.setText("");
                } else {
                    sendPost(new User(mInputEditTextTen.getText().toString(),
                            mInputEditTextEmail.getText().toString(),
                            mInputEditTextSDT.getText().toString(),
                            mInputEditTextAddress.getText().toString(),
                            mInputEditTextPass.getText().toString()));
                }
            }
        });
    }
    private void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Đăng ký");
        mToolbar.setNavigationIcon(R.drawable.ic_back_detail);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorWhile));

    }
    public void sendPost(User user) {
        mAPIService.register(user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                //howResponse(response.body().toString());
                //Log.i(TAG, "post submitted to API." + response.body().toString());
                Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT)
                        .show();
                mInputEditTextAddress.setText("");
                mInputEditTextEmail.setText("");
                mInputEditTextPass.setText("");
                mInputEditTextSDT.setText("");
                mInputEditTextTen.setText("");
                mInputEditTextXNPass.setText("");
                //                Intent intent = new Intent(RegisterActivity.this,LoginActivity
                // .class);
                //                startActivity(intent);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                //Log.e(TAG, "Unable to submit post to API.");
            }
        });
    }

    boolean isEmail(TextInputEditText inputEditTextEmail) {
        CharSequence email = inputEditTextEmail.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
}
