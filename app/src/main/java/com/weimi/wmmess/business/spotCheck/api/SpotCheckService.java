package com.weimi.wmmess.business.spotCheck.api;

import com.weimi.wmmess.http.BaseUrl;
import com.weimi.wmmess.model.ListModel;
import com.weimi.wmmess.model.ResultModel;

import io.reactivex.Observable;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Create by chhyu
 * on 2019/7/19
 * Describle: 项目点检service
 */
public interface SpotCheckService {

    //获取工单列表
    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/...")
    Observable<ResultModel<ListModel<Object>>> getSpotCheckProjectList( );
}
