package com.weimi.wmmess.business.procedureOutput.api;

import com.weimi.wmmess.business.procedureOutput.bean.ProcedureOutputDetailResbean;
import com.weimi.wmmess.business.procedureOutput.bean.ProcedureOutputResbean;
import com.weimi.wmmess.business.procedureOutput.params.WmMesWorkshopProcedureOutputRegister;
import com.weimi.wmmess.http.BaseUrl;
import com.weimi.wmmess.model.ListModel;
import com.weimi.wmmess.model.ResultModel;
import com.weimi.wmmess.params.GeneralParam;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Create by chhyu
 * on 2019/7/24
 * Describle:
 */
public interface ProcedureOutputService {

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/workshop/pageMesWorkshopProcedureOutput")
    Observable<ResultModel<ListModel<ProcedureOutputResbean>>> loadProcedureOutputList(@Body GeneralParam param);

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/workshop/insertMesWorkshopProcedureOutput")
    Observable<ResultModel<Object>> insertMesWorkshopProcedureOutput(@Body WmMesWorkshopProcedureOutputRegister param);

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/workshop/updateMesWorkshopProcedureOutput")
    Observable<ResultModel<Object>> updateMesWorkshopProcedureOutput(@Body WmMesWorkshopProcedureOutputRegister param);

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/workshop/getMesWorkshopProcedureOutputById")
    Observable<ResultModel<ProcedureOutputDetailResbean>> getMesWorkshopProcedureOutputById(@Query("recordId") String recordId);

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/workshop/deleteMesWorkshopProcedureOutput")
    Observable<ResultModel<Object>> deleteMesWorkshopProcedureOutput(@Query("recordId") String recordId);
}
