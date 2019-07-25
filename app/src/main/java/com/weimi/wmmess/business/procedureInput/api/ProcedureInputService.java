package com.weimi.wmmess.business.procedureInput.api;

import com.weimi.wmmess.business.procedureInput.bean.PorcedureInputDetailResbean;
import com.weimi.wmmess.business.procedureInput.bean.ProcedureInputResbean;
import com.weimi.wmmess.business.procedureInput.params.WmMesWorkshopProcedureInputRegister;
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
 * on 2019/7/23
 * Describle:
 */
public interface ProcedureInputService {

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/workshop/pageMesWorkshopProcedureInput")
    Observable<ResultModel<ListModel<ProcedureInputResbean>>> loadProcedureInputList(@Body GeneralParam param);

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/workshop/insertMesWorkshopProcedureInput")
    Observable<ResultModel<Object>> insertMesWorkshopProcedureInput(@Body WmMesWorkshopProcedureInputRegister param);

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/workshop/updateMesWorkshopProcedureInput")
    Observable<ResultModel<Object>> updateMesWorkshopProcedureInput(@Body WmMesWorkshopProcedureInputRegister param);

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/workshop/deleteMesWorkshopProcedureInput")
    Observable<ResultModel<Object>> deleteMesWorkshopProcedureInput(@Query("recordId") String recordId);

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/workshop/getMesWorkshopProcedureInputById")
    Observable<ResultModel<PorcedureInputDetailResbean>> getMesWorkshopProcedureInputById(@Query("recordId") String recordId);
}
