package com.rnm.test.viewpagerexample;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Mahe on 11/15/2017.
 */

public interface RetrofitService {

    String ENDPOINT = "https://unsplash.it/";

    @GET("/list")
    void getFeed(Callback<List<Images>> callback);
}
