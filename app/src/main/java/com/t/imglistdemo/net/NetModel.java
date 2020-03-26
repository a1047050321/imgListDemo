package com.t.imglistdemo.net;

import com.t.imglistdemo.entity.ImageEntity;

import java.util.Map;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class NetModel {

    private static NetModel instance;

    public static NetModel getInstance() {
        if (instance == null) {
            synchronized (NetModel.class) {
                if (instance == null) {
                    instance = new NetModel();
                }
            }
        }
        return instance;
    }

    private NetModel() {

    }

    public Disposable getImageList(String url, Map<String, Object> params, final IResponseListener<ImageEntity> listener) {
        return HttpClient.getHttpService().getImgList(url, params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ImageEntity>() {
                    @Override
                    public void accept(ImageEntity imageEntity) throws Exception {
                        if (listener != null) {
                            listener.onSuccess(imageEntity);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (listener != null) {

                        }
                    }
                });
    }


}
