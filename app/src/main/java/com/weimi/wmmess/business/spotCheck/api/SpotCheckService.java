package com.weimi.wmmess.business.spotCheck.api;

import com.weimi.wmmess.business.spotCheck.bean.EquipmentTypeResbean;
import com.weimi.wmmess.business.spotCheck.bean.ModelFrameResbean;
import com.weimi.wmmess.business.spotCheck.bean.ModelKernelResbean;
import com.weimi.wmmess.business.spotCheck.bean.SpotCheckItemResbean;
import com.weimi.wmmess.business.spotCheck.bean.SpotCheckProjectResbean;
import com.weimi.wmmess.business.spotCheck.params.MobileInspectRecordParam;
import com.weimi.wmmess.business.spotCheck.params.MobileListInspectDetailParam;
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
 * on 2019/7/19
 * Describle: 项目点检service
 */
public interface SpotCheckService {

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/inspect/pageCheckType")
    Observable<ResultModel<ListModel<SpotCheckProjectResbean>>> getSpotCheckProjectList(@Body GeneralParam param);

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/inspect/listCheckContentByType")
    Observable<ResultModel<List<SpotCheckItemResbean>>> listCheckContent(@Body MobileListInspectDetailParam inspectDetailParam);

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/inspect/insertInspectContentRecord")
    Observable<ResultModel<Object>> insertInspectContentRecord(@Body MobileInspectRecordParam param);

    @Headers({BaseUrl.HEADER_HOST})
    @POST("sys/dict/listDictCodeByDictTypeCode")
    Observable<ResultModel<List<EquipmentTypeResbean>>> getEquipmentTypeList(@Query("typeCode") String facilityTypeCode);

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/inspect/pageMesModelFrame")
    Observable<ResultModel<ListModel<ModelFrameResbean>>> pageMesModelFrame(@Body GeneralParam param);

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/inspect/pageMesModelKernel")
    Observable<ResultModel<ListModel<ModelKernelResbean>>> pageMesModelKernel(@Body GeneralParam param);
}
