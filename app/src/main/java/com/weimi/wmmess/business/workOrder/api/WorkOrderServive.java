package com.weimi.wmmess.business.workOrder.api;

import com.weimi.wmmess.business.workOrder.bean.CraftParamsResbean;
import com.weimi.wmmess.business.workOrder.bean.WorkOrderListResbean;
import com.weimi.wmmess.business.workOrder.params.FilterWorkOrderParams;
import com.weimi.wmmess.http.BaseUrl;
import com.weimi.wmmess.model.ListModel;
import com.weimi.wmmess.model.ResultModel;
import com.weimi.wmmess.business.workOrder.params.MobileWorkOrderParam;
import com.weimi.wmmess.params.PageInfoParams;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Create by chhyu
 * on 2019/6/28
 * Describle: 工单service
 */
public interface WorkOrderServive {

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/pageMesProductWorkOrder")
    Observable<ResultModel<ListModel<WorkOrderListResbean>>> getWorkOrderList(@Body PageInfoParams pageInfoParams, @Body FilterWorkOrderParams filterWorkOrderParams);


    //获取工单列表
    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/pageMesProductWorkOrder")
    Observable<ResultModel<ListModel<WorkOrderListResbean>>> getWorkOrderList(@Body MobileWorkOrderParam mobileWorkOrderParam);


    //检查是否有工艺参数
    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/checkWorkOrderHavingParameter")
    Observable<ResultModel<Boolean>> checkWorkOrderHavingParameter(@Query("workOrderId") String orderId, @Query("procedureId") String procedureId);

    //工艺参数列表
    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/pageTrialParameterByOrderId")
    Observable<ResultModel<ListModel<CraftParamsResbean>>> listWorkOrderParameterByOrderId(@Body MobileWorkOrderParam param);

}
