package com.weimi.wmmess.api;

import com.weimi.wmmess.http.BaseUrl;
import com.weimi.wmmess.model.ResultModel;
import com.weimi.wmmess.params.LoginParams;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Create by chhyu
 * on 2019/6/28
 * Describle:
 */
public interface MainServive {

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/checkUserInfoTrial")
    Observable<ResultModel<String>> doLogin(@Body LoginParams user);

}
