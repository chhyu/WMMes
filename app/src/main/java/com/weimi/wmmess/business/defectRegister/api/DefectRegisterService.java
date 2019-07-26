package com.weimi.wmmess.business.defectRegister.api;

import com.weimi.wmmess.business.defectRegister.bean.DefectDetailResbean;
import com.weimi.wmmess.business.defectRegister.bean.DefectParentResbean;
import com.weimi.wmmess.business.defectRegister.bean.DefectRegisterResbean;
import com.weimi.wmmess.business.defectRegister.params.WmMesWorkshopProcedureDefectRegister;
import com.weimi.wmmess.http.BaseUrl;
import com.weimi.wmmess.model.ListModel;
import com.weimi.wmmess.model.ResultModel;
import com.weimi.wmmess.params.GeneralParam;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Create by chhyu
 * on 2019/7/25
 * Describle:
 */
public interface DefectRegisterService {

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/workshop/pageMesWorkshopDefectRegister")
    Observable<ResultModel<ListModel<DefectRegisterResbean>>> loadDefectRegisterputList(@Body GeneralParam param);

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/workshop/listTreeMesWorkshopDefect")
    Observable<ResultModel<List<DefectParentResbean>>> listTreeMesWorkshopDefect();

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/workshop/insertMesWorkshopDefectRegister")
    Observable<ResultModel<Object>> insertMesWorkshopDefectRegister(@Body WmMesWorkshopProcedureDefectRegister params);

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/workshop/updateMesWorkshopDefectRegister")
    Observable<ResultModel<Object>> updateMesWorkshopDefectRegister  (@Body WmMesWorkshopProcedureDefectRegister params);

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/workshop/getMesWorkshopDefectRegisterById")
    Observable<ResultModel<DefectDetailResbean>> getMesWorkshopDefectRegisterById(@Query("recordId") String recordId);

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/workshop/deleteMesWorkshopDefectRegister")
    Observable<ResultModel<Object>> deleteMesWorkshopDefectRegister (@Query("recordId") String recordId);
}
