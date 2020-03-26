package com.t.imglistdemo.net;

public interface IResponseListener<T> {
    void onSuccess(T t);

    void onFailure(int code, String msg);

}
