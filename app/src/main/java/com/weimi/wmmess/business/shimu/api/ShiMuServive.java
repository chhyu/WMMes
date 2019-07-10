package com.weimi.wmmess.business.shimu.api;

import com.weimi.wmmess.business.shimu.bean.craftParams.CraftConfirmResbean;
import com.weimi.wmmess.business.shimu.bean.craftParams.ModelInfoResbean;
import com.weimi.wmmess.business.shimu.bean.history.HistoryResbean;
import com.weimi.wmmess.business.shimu.params.MobileTrialModeSaveParam;
import com.weimi.wmmess.business.workOrder.params.MobileWorkOrderParam;
import com.weimi.wmmess.http.BaseUrl;
import com.weimi.wmmess.model.ListModel;
import com.weimi.wmmess.model.ResultModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Create by chhyu
 * on 2019/6/28
 * Describle: 工单service
 */
public interface ShiMuServive {

    /*保存试模工艺参数*/
    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/insertTrialParameterRecord")
    Observable<ResultModel<Object>> saveCraftParams2Service(@Body MobileTrialModeSaveParam paramParams);

    /*获取试模工艺参数*/
    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/listTrialRecordDetailByParameterId")
    Observable<ResultModel<List<CraftConfirmResbean>>> getTrialParameterInfoById(@Query("craftParameterId") String craftParameterId);

    /*获取试模历史记录*/
    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/pageTrialRecordByOrderId")
    Observable<ResultModel<ListModel<HistoryResbean>>> getShiMuHistoryRecord(@Body MobileWorkOrderParam paramParams);

    /*获取模具和机台信息*/
    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/getTrialParameterInfoById")
    Observable<ResultModel<ModelInfoResbean>> getModelInfoById(@Body MobileTrialModeSaveParam paramParam);
}
