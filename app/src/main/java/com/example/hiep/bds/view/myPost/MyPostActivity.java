package com.example.hiep.bds.view.myPost;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.hiep.bds.presenter.DataMyPostPre;
import com.example.hiep.bds.presenter.GetDataMyPost;
import com.example.hiep.bds.R;
import com.example.hiep.bds.adapter.DataMyPostAdapter;
import com.example.hiep.bds.model.DataMyPost;
import com.example.hiep.bds.model.Delete;
import com.example.hiep.bds.utilts.ApiClient;
import com.example.hiep.bds.utilts.ApiInterface;
import com.example.hiep.bds.utilts.OnItemClick;
import com.example.hiep.bds.view.home.HomeActivity;
import com.example.hiep.bds.view.postAD.PorposeActivity;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPostActivity extends AppCompatActivity  implements GetDataMyPostView {

    private RecyclerView mRecyclerView;
    private DataMyPostAdapter mDataMyPostAdapter;
    private GetDataMyPost iGetDataMyPost;
    private TextView txtShow;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);

        init();
        final LinearLayoutManager layoutManager1 =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager1);
        getDataFromServer();
        setToolBar();
    }

    private void getDataFromServer() {
        iGetDataMyPost.getDataMyPost("Bearer " + HomeActivity.TOKEN);
    }

    private void init() {
        mToolbar = findViewById(R.id.toolBarMyPost);
        mRecyclerView = findViewById(R.id.recyclerMyPost);
        iGetDataMyPost = new DataMyPostPre(this);
        txtShow = findViewById(R.id.txtShow);
    }
    private void setToolBar(){
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Bài viết của tôi");
        mToolbar.setNavigationIcon(R.drawable.ic_back_detail);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorWhile));
    }
    @Override
    public void getDataMyPostSuccess(final List<DataMyPost> movies) {
        if (movies.size()==0){
            txtShow.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
            txtShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), PorposeActivity.class);
                    startActivity(intent);
                }
            });
        } else {
            txtShow.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            mDataMyPostAdapter = new DataMyPostAdapter(movies, R.layout.item_my_post, getContext());
            mRecyclerView.setAdapter(mDataMyPostAdapter);
            mDataMyPostAdapter.setOnItemClickListener(new OnItemClick() {
                @Override
                public void onClick(View v, int adapterPosition, boolean b) {

                }

                @Override
                public void onClickItem(int pos) {

                }

                @Override
                public void onClickDelete(final int pos) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(MyPostActivity.this);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn có chắc muốn xóa tin không? ");
                    builder.setIcon(R.drawable.ic_delete);

                    // Set up the buttons
                    builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                            Call<Delete> call = apiService.delete("Bearer "+ HomeActivity.TOKEN,movies.get(pos).getId());
                            call.enqueue(new Callback<Delete>() {
                                @Override
                                public void onResponse(Call<Delete> call, Response<Delete> response) {
                                    if (response.code() == 200) {
                                        Toast.makeText(MyPostActivity.this, ""+response.body().getSuccess(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                                @Override
                                public void onFailure(Call<Delete> call, Throwable t) {
                                }
                            });
                            removeItem(movies,pos);
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    builder.show();
                }

                @Override
                public void onClickEdit(int pos) {
                    Intent intent = new Intent(MyPostActivity.this, EditActivity.class);
                    intent.putExtra("Key_2", movies.get(pos).getId());
                    startActivity(intent);
                }
            });


        }


    }

    @Override
    public void getDataMyPostFailure() {

    }

    @Override
    public Context getContext() {
        return null;
    }
    public void removeItem(List<DataMyPost> movies,int position) {
        movies.remove(position);
        mDataMyPostAdapter.notifyItemRemoved(position);
    }

}
