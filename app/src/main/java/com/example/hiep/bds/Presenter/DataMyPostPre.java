package com.example.hiep.bds.Presenter;

import android.widget.Toast;
import com.example.hiep.bds.model.DataMyPostResponse;
import com.example.hiep.bds.model.DataResponnse;
import com.example.hiep.bds.utilts.ApiClient;
import com.example.hiep.bds.utilts.ApiInterface;
import com.example.hiep.bds.utilts.GetView;
import com.example.hiep.bds.view.myPost.GetDataMyPostView;
import com.example.hiep.bds.view.sale.GetDataView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataMyPostPre extends GetView<GetDataMyPostView> implements GetDataMyPost {
    public DataMyPostPre(GetDataMyPostView getDiscoverView) {
        super(getDiscoverView);
    }

    @Override
    public void getDataMyPost(String auth) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<DataMyPostResponse> call = apiService.getDataMyPost(auth);
        call.enqueue(new Callback<DataMyPostResponse>() {
            @Override
            public void onResponse(Call<DataMyPostResponse> call, Response<DataMyPostResponse> response) {
                    presenter.getListMovieSuccess(response.body().getData());
                }
            @Override
            public void onFailure(Call<DataMyPostResponse> call, Throwable t) {
                //Toast.makeText(presenter.getContext(), "Lỗi data", Toast.LENGTH_SHORT).show();
                presenter.getListMovieFailure();
            }
        });
    }
}
