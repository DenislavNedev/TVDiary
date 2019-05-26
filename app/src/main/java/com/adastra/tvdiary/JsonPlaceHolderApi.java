package com.adastra.tvdiary;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("shows")
    Call<List<Show>> getShows();
}
