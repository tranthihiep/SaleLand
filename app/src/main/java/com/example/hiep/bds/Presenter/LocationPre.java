package com.example.hiep.bds.Presenter;

import com.example.hiep.bds.model.modelLocation.LtsItem;
import com.example.hiep.bds.utilts.ApiClient;
import com.example.hiep.bds.utilts.ApiInterface;
import com.example.hiep.bds.utilts.GetView;
import com.example.hiep.bds.view.postAD.GetLocationView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationPre extends GetView<GetLocationView> implements GetLocation {
    public LocationPre(GetLocationView getDiscoverView) {
        super(getDiscoverView);
    }

    @Override
    public void getLocation() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<LtsItem>> call = apiService.getCity();
        call.enqueue(new Callback<List<LtsItem>>() {
            @Override
            public void onResponse(Call<List<LtsItem>> call, Response<List<LtsItem>> response) {
                if (response.code() == 200) {
                    presenter.getListLocationSucces(response.body());
                } else {
                    presenter.getListLocationFailure();
                }
            }

            @Override
            public void onFailure(Call<List<LtsItem>> call, Throwable t) {
                presenter.getListLocationFailure();
            }
        });
    }
}