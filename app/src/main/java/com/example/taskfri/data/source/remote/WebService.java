package com.example.taskfri.data.source.remote;

import com.example.taskfri.data.model.ResponseItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WebService {
    @GET("posts")
    Call<List<ResponseItem>>getResponse();
    @GET("posts/{id}")
    Call<ResponseItem> getPost(@Path("id") int id);
}
