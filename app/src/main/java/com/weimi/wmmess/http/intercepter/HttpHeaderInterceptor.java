package com.weimi.wmmess.http.intercepter;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 网络请求公共头信息插入器
 * Created by Jason on 2018/7/3.
 */
public class HttpHeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
                .header("User-Agent", "Android, xxx")
                .header("Accept", "application/json")
                .header("Content-type", "application/json")
                .method(original.method(), original.body())
                .build();
        return chain.proceed(request);
    }
}
