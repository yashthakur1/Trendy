package com.heady.explora.data;

import com.heady.explora.screens.models.ResponseData;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by yashthakur on 26/08/18.
 */

public interface ApiService {

    @GET("/json")
    Observable<ResponseData> getCatalogData();

}
