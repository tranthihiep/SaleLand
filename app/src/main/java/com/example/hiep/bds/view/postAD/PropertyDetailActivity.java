package com.example.hiep.bds.view.postAD;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
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

public class PropertyDetailActivity extends AppCompatActivity {
    @BindView(R.id.toolbarPD)
    Toolbar mToolbar;
    @BindView(R.id.btnTTPD)
    Button mButtonTTPD;
    @BindView(R.id.listview)
    ListView mListView;
    List<Conveniences> initItemList;
    AmentiesAdapter adapter;
    ArrayList<Integer> ngt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_detail);
        ButterKnife.bind(this);
        setToolbar();
        haha();
        Bundle bundle = getIntent().getExtras();
        final ArrayList<Integer> noithat = bundle.getIntegerArrayList("amen");
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Conveniences model = initItemList.get(i);

                if (model.isChecked())
                    model.setAction(false);

                else
                    model.setAction(true);

                initItemList.set(i, model);

                //now update mAmentiesAdapter
                adapter.updateRecords(initItemList);
            }
        });
        mButtonTTPD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ngt = new ArrayList<>();
                for (int i = 0 ; i<initItemList.size();i++){
                    if (initItemList.get(i).isChecked() == true){
                        ngt.add(initItemList.get(i).getId());
                    }
                }
                Intent intent = new Intent(PropertyDetailActivity.this,DistanceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putIntegerArrayList("ngoaithat",ngt);
                bundle.putIntegerArrayList("noithat",noithat);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void haha() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Conveniences>> call = apiService.getExterior();
        call.enqueue(new Callback<List<Conveniences>>() {
            @Override
            public void onResponse(Call<List<Conveniences>> call, final Response<List<Conveniences>> response) {
                if (response.code() == 200) {

                    initItemList = response.body();
                    for (int  i = 0 ; i< initItemList.size();i++){
                        initItemList.get(i).setAction(false);
                    }
                    adapter = new AmentiesAdapter(getApplicationContext(),
                            initItemList);
                    mListView.setAdapter(adapter);

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
        getSupportActionBar().setTitle("Ngoại thất");
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

