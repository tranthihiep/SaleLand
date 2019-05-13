package com.example.hiep.bds.presenter;

import com.example.hiep.bds.model.DataDetail;
import com.example.hiep.bds.utilts.ApiClient;
import com.example.hiep.bds.utilts.ApiInterface;
import com.example.hiep.bds.utilts.GetView;
import com.example.hiep.bds.view.detailData.GetDataDetailView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataDetailPre extends GetView<GetDataDetailView> implements GetDataDetail {
    public DataDetailPre(GetDataDetailView getDiscoverView) {
        super(getDiscoverView);
    }

    @Override
    public void getDetailMovie(int id) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<DataDetail> call = apiService.getDataDetail(id);
        call.enqueue(new Callback<DataDetail>() {
            @Override
            public void onResponse(Call<DataDetail> call, Response<DataDetail> response) {
                if (response.code() == 200) {
                    presenter.getDataDetailSuccess((DataDetail) response.body());
                } else {
                    presenter.getDataDetailFailure();
                }
            }

            @Override
            public void onFailure(Call<DataDetail> call, Throwable t) {
                presenter.getDataDetailFailure();
            }
        });
    }
}