package com.example.hiep.bds.utilts;

import com.example.hiep.bds.model.Conveniences;
import com.example.hiep.bds.model.DataDetail;
import com.example.hiep.bds.model.DataMyPostResponse;
import com.example.hiep.bds.model.DataResponnse;
import com.example.hiep.bds.model.Delete;
import com.example.hiep.bds.model.Login;
import com.example.hiep.bds.model.User;
import com.example.hiep.bds.model.modelLocation.Huyen;
import com.example.hiep.bds.model.modelLocation.LtsItem;
import com.example.hiep.bds.model.modelLocation.Phuong;
import com.example.hiep.bds.model.postModel.DataPost;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("api/posts")
    Call<DataResponnse> getMovieDetails(@Query("page") int page);

    @GET("api/posts/{id}")
    Call<DataDetail> getDataDetail(@Path("id") int id);

    @GET("api/cities")
    Call<List<LtsItem>> getCity();

    @GET("api/cities/{id}/districts")

    Call<List<Huyen>> getHuyen(@Path("id") int id);

    @GET("api/district/{id}/ward")
    Call<List<Phuong>> getPhuong(@Path("id") int id);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("api/register")
    Call<User> register(@Body User user);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("api/login")
    Call<Login> login(@Body User user);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("api/me")
    Call<User> me(@Header("Authorization") String authHeader);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("api/posts")
    Call<DataPost>postData(@Header("Authorization") String authHeader, @Body DataPost dataPost);

    @GET("api/conveniences?type=interior")
    Call<List<Conveniences>> getAmenties();

    @GET("api/conveniences?type=exterior")
    Call<List<Conveniences>> getExterior();

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("api/my-posts")
    Call<DataMyPostResponse> getDataMyPost(@Header("Authorization") String authHeader);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @DELETE("api/posts/{id}")
    Call<Delete> delete(@Header("Authorization") String authHeader,@Path("id") int id);
//    @POST("api/posts/{id}/edit")
//    Call<Delete> delete(@Header("Authorization") String authHeader,@Path("id") int id);
}
