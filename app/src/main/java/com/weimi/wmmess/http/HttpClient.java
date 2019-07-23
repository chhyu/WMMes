package com.weimi.wmmess.http;

import android.text.TextUtils;

import com.weimi.wmmess.constants.HostAddress;
import com.weimi.wmmess.http.intercepter.CommParamsInterceptor;
import com.weimi.wmmess.http.intercepter.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * Created by Jason on 2018/7/9.
 */
public class HttpClient {
    private static String BASE_URL = "";
    private String baseUrl = "";
    private Retrofit retrofit;
    private static final int HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 10 * 1024 * 1024;//缓存文件最大值为10Mb

    public HttpClient() {

    }

    private void setBuilder(Builder builder) {
        BASE_URL = builder.apiUrl;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static HttpClient getInstance() {
        return HttpClient.HttpClientHolder.sInstance;
    }

    private static class HttpClientHolder {
        private static final HttpClient sInstance = new HttpClient();
    }


    /**
     * 构建OkHttp
     */
    private static final class OKHttpHolder {
        private static final int TIME_OUT = 10; //建立连接的超时时间
        private static final int READ_TIME_OUT = 10 * 60; //读取数据的超时时间
        private static final int WRITE_TIME_OUT = 120; //写入数据的超时时间
        //        private static final ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(AppUtils.));


        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                //.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
                // .hostnameVerifier(HttpsUtil.getHostnameVerifier())
//
//                .addInterceptor(new MoreBaseUrlInterceptor())
                .addInterceptor(new CommParamsInterceptor())
                .addInterceptor(new LoggingInterceptor())
//                .authenticator((route, response) -> {
//                    String url = response.request().url().url().toString();
//                    if (url.contains(HostAddress.HOST_CLOUDDISK_API)) {
//                        String token = getCDToken();
//                        return response.request().newBuilder()
//                                .header("token", token != null ? token : "")
//                                .build();
//                    } else if (url.contains(HostAddress.HOST_ATTENDANCE_API)) {
//                        String token = getAttendanceToken();
//                        return response.request().newBuilder()
//                                .header("Authorization", token != null ? token : "")
//                                .build();
//                    } else if (url.contains(HostAddress.HOST_EMERGENCY_API)) {
//                        String token = getEmergencyToken();
//                        return response.request().newBuilder()
//                                .header("Authorization", token != null ? token : "")
//                                .build();
//                    } else {
//                        String token = getToken();
//                        final String realTokenAccess = String.format("Bearer %s", token != null ? token : "");
//                        return response.request().newBuilder()
//                                .header("Authorization", realTokenAccess)
//                                .build();
//                    }
//                })
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
//                .cookieJar(cookieJar)
                .build();

    }

    /**
     * 构建Retrofit
     */
    public Retrofit getRetrofit() {
        if (retrofit == null) {
            baseUrl = BASE_URL;
            retrofit = new Retrofit.Builder()
                    .baseUrl(HostAddress.HOST_API)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(FastJsonConverterFactory.create())
                    .client(OKHttpHolder.OK_HTTP_CLIENT)
                    .build();
        }
        return retrofit;
    }

    public static final class Builder {
        private String apiUrl;

        public Builder() {
        }

        public Builder baseUrl(String val) {
            if (TextUtils.isEmpty(val)) {
                throw new IllegalArgumentException("baseurl can not be empty");
            }
            apiUrl = val;
            return this;
        }

        public HttpClient build() {
            HttpClient client = HttpClient.getInstance();
            client.setBuilder(this);
            return client;
        }
    }


}
