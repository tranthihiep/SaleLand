package com.example.hiep.bds.view.postAD;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.hiep.bds.R;
import com.example.hiep.bds.adapter.AmentiesAdapter;
import com.example.hiep.bds.model.Conveniences;
import com.example.hiep.bds.utilts.ApiClient;
import com.example.hiep.bds.utilts.ApiInterface;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AmentiesActivity extends AppCompatActivity {
    @BindView(R.id.toolbarAM)
    Toolbar mToolbar;
    @BindView(R.id.btnTTAM)
    Button mButton;
    @BindView(R.id.listview)
    ListView mListView;
    List<Conveniences> mConveniences;
    AmentiesAdapter mAmentiesAdapter;
    ArrayList<Integer> amen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amenties);
        ButterKnife.bind(this);
        setToolbar();
        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        haha();


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amen = new ArrayList<>();
                for (int i = 0 ; i< mConveniences.size();i++){
                    if (mConveniences.get(i).isChecked() == true){
                        amen.add(mConveniences.get(i).getId());
                    }
                }
                Intent intent = new Intent(AmentiesActivity.this,PropertyDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putIntegerArrayList("amen",amen);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void haha() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Conveniences>> call = apiService.getAmenties();
        call.enqueue(new Callback<List<Conveniences>>() {
            @Override
            public void onResponse(Call<List<Conveniences>> call, final Response<List<Conveniences>> response) {
                if (response.code() == 200) {

                    mConveniences = response.body();
                    for (int  i = 0 ; i< mConveniences.size();i++){
                        mConveniences.get(i).setAction(false);
                    }
                    mAmentiesAdapter = new AmentiesAdapter(getApplicationContext(), mConveniences);
                    mListView.setAdapter(mAmentiesAdapter);

                } else {
                }
            }

            @Override
            public void onFailure(Call<List<Conveniences>> call, Throwable t) {
            }
        });


    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Nội thất");
        mToolbar.setNavigationIcon(R.drawable.ic_back_detail);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mToolbar.setTitleTextColor(getResources().getColor(R.color.colorWhile));

    }


}

