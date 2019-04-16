package com.example.hiep.bds.Presenter;

import com.example.hiep.bds.model.DataDetail;
import com.example.hiep.bds.model.DataResponnse;
import com.example.hiep.bds.utilts.ApiClient;
import com.example.hiep.bds.utilts.ApiInterface;
import com.example.hiep.bds.utilts.GetView;
import com.example.hiep.bds.view.detailData.GetDataDetailView;
import com.example.hiep.bds.view.sale.GetDataView;
import java.util.List;
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
        Call<List<DataDetail>> call = apiService.getDataDetail(id);
        call.enqueue(new Callback<List<DataDetail>>() {
            @Override
            public void onResponse(Call<List<DataDetail>> call, Response<List<DataDetail>> response) {
                if (response.code() == 200) {
                    presenter.getDetailMovieSuccess((DataDetail) response.body());
                } else {
                    presenter.getDetailMovieFailure();
                }
            }

            @Override
            public void onFailure(Call<List<DataDetail>> call, Throwable t) {
                presenter.getDetailMovieFailure();
            }
        });
    }
}