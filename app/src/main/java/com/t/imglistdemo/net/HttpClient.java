package com.t.imglistdemo.net;


import com.t.imglistdemo.common.Constant;

public class HttpClient {
    private static HttpService httpService;

    static {
        httpService = RetrofitFactory.getRetrofit(HttpService.class, HttpUrl.BASE_URL);
    }

    public static HttpService getHttpService() {
        return httpService;
    }
}

