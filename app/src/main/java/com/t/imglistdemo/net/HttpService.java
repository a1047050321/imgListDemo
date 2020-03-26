package com.t.imglistdemo.net;

import com.t.imglistdemo.entity.ImageEntity;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface HttpService {

    @GET
    Observable<ImageEntity> getImgList(@Url String url, @QueryMap Map<String, Object> params);

}
