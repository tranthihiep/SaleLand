package com.example.hiep.bds.Presenter;

import com.example.hiep.bds.model.DataResponnse;
import com.example.hiep.bds.view.sale.GetDataView;
import com.example.hiep.bds.utilts.ApiClient;
import com.example.hiep.bds.utilts.ApiInterface;
import com.example.hiep.bds.utilts.GetView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataPre extends GetView<GetDataView> implements GetData {
    public DataPre(GetDataView getDiscoverView) {
        super(getDiscoverView);
    }

    @Override
    public void getData(int page) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<DataResponnse> call = apiService.getMovieDetails(page);
        call.enqueue(new Callback<DataResponnse>() {
            @Override
            public void onResponse(Call<DataResponnse> call, Response<DataResponnse> response) {
                if (response.code() == 200) {
                    presenter.getListMovieSuccess(response.body().getData());
                } else {
                    presenter.getListMovieFailure();
                }
            }

            @Override
            public void onFailure(Call<DataResponnse> call, Throwable t) {
                presenter.getListMovieFailure();
            }
        });
    }
}