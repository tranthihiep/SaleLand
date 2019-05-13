package com.example.hiep.bds.presenter;

import com.example.hiep.bds.model.modelLocation.Tinh;
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
        Call<List<Tinh>> call = apiService.getCity();
        call.enqueue(new Callback<List<Tinh>>() {
            @Override
            public void onResponse(Call<List<Tinh>> call, Response<List<Tinh>> response) {
                if (response.code() == 200) {
                    presenter.getListLocationSucces(response.body());
                } else {
                    presenter.getListLocationFailure();
                }
            }

            @Override
            public void onFailure(Call<List<Tinh>> call, Throwable t) {
                presenter.getListLocationFailure();
            }
        });
    }
}