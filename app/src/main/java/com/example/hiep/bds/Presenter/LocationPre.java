package com.example.hiep.bds.Presenter;

import com.example.hiep.bds.model.modelLocation.CityResponse;
import com.example.hiep.bds.utilts.ApiClient;
import com.example.hiep.bds.utilts.ApiInterface;
import com.example.hiep.bds.utilts.GetView;
import com.example.hiep.bds.view.GetLocationView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationPre extends GetView<GetLocationView> implements GetLocation {
    public LocationPre(GetLocationView getDiscoverView) {
        super(getDiscoverView);
    }

    @Override
    public void getLocation() {
        ApiInterface apiService = ApiClient.getCity().create(ApiInterface.class);
        Call<CityResponse> call = apiService.getCity();
        call.enqueue(new Callback<CityResponse>() {
            @Override
            public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
                if (response.code() == 200) {
                    presenter.getListLocationSucces(response.body().getLtsItem());
                } else {
                    presenter.getListLocationFailure();
                }
            }

            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {
                presenter.getListLocationFailure();
            }
        });
    }
}