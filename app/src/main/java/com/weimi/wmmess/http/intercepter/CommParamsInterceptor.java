package com.weimi.wmmess.http.intercepter;

import com.weimi.wmmess.utils.ConfigUtils;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 网络请求公共参数插入器
 * Created by Jason on 2018/7/3.
 */
public class CommParamsInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl httpUrl = request.url().newBuilder()
                .addQueryParameter("ticket", ConfigUtils.getTicket())
                .build();
        request = request.newBuilder().url(httpUrl).build();
        return chain.proceed(request);
    }
}