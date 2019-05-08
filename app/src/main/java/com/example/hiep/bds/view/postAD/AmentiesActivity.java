package com.example.hiep.bds.view.postAD;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.hiep.bds.R;
import com.example.hiep.bds.adapter.AmentiesAdapter;
import com.example.hiep.bds.model.Conveniences;
import com.example.hiep.bds.model.modelLocation.Huyen;
import com.example.hiep.bds.utilts.ApiClient;
import com.example.hiep.bds.utilts.ApiInterface;
import com.example.hiep.bds.utilts.CustemSpinner;
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
    List<Conveniences> initItemList;
    AmentiesAdapter adapter;
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

                Conveniences model = initItemList.get(i);

                if (model.isChecked())
                    model.setAction(false);

                else
                    model.setAction(true);

                initItemList.set(i, model);

                //now update adapter
                adapter.updateRecords(initItemList);
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amen = new ArrayList<>();
                for (int i = 0 ; i<initItemList.size();i++){
                    if (initItemList.get(i).isChecked() == true){
                        amen.add(initItemList.get(i).getId());
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

