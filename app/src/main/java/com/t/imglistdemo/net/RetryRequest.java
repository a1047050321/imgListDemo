package com.t.imglistdemo.net;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * 重试请求
 */
public class RetryRequest implements Function<Observable<Throwable>, ObservableSource<?>> {
    private static final String TAG = "RetryRequest";
    private int maxConnectCount = 10;
    private int currentRetryCount = 0;
    private int waitRetryTime = 0;

    public RetryRequest(int maxConnectCount, int waitRetryTime) {
        this.maxConnectCount = maxConnectCount;
        this.waitRetryTime = waitRetryTime;
    }

    @Override
    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Throwable throwable) throws Exception {
                if (throwable instanceof IOException) {//重试
                    if (currentRetryCount < maxConnectCount) {
                        currentRetryCount++;
                        return Observable.just(1).delay(waitRetryTime, TimeUnit.SECONDS);
                    } else {
                        return Observable.error(new Throwable("重试次数已超过设置次数 = " + currentRetryCount + "，即不再重试"));
                    }
                } else {//若发生的异常不属于I/O异常，则不重试
                    return Observable.error(new Throwable("发生了非网络异常（非I/O异常"));
                }
            }
        });
    }
}

