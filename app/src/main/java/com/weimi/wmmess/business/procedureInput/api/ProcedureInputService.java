package com.weimi.wmmess.business.procedureInput.api;

import com.weimi.wmmess.business.procedureInput.bean.ProcedureInputResbean;
import com.weimi.wmmess.http.BaseUrl;
import com.weimi.wmmess.model.ListModel;
import com.weimi.wmmess.model.ResultModel;
import com.weimi.wmmess.params.GeneralParam;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Create by chhyu
 * on 2019/7/23
 * Describle:
 */
public interface ProcedureInputService {

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/workshop/pageMesWorkshopProcedureInput")
    Observable<ResultModel<ListModel<ProcedureInputResbean>>> loadProcedureInputList(@Body GeneralParam param);
}
