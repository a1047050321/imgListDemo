package com.t.imglistdemo.net;


public class HttpClient {
    private static HttpService httpService;

    static {
        httpService = RetrofitFactory.getRetrofit(HttpService.class, HttpUrl.BASE_URL);
    }

    public static HttpService getHttpService() {
        return httpService;
    }
}

