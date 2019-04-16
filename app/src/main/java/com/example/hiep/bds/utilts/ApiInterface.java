package com.example.hiep.bds.utilts;

import com.example.hiep.bds.model.DataDetail;
import com.example.hiep.bds.model.DataResponnse;
import com.example.hiep.bds.model.modelLocation.CityResponse;
import com.example.hiep.bds.model.modelLocation.Huyen;
import com.example.hiep.bds.model.modelLocation.Phuong;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("api/posts")
    Call<DataResponnse> getMovieDetails(@Query("page") int page);

    @GET("api/posts/{id}")
    Call<List<DataDetail>> getDataDetail(@Path("id") int id);

    @GET("api/city")
    Call<CityResponse> getCity();

    @GET("api/city/{id}/district")
    Call<List<Huyen>> getHuyen(@Path("id") int id);

    @GET("api/district/{id}/ward")
    Call<List<Phuong>> getPhuong(@Path("id") int id);
}
