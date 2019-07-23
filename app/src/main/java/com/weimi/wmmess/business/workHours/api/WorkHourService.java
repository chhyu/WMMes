package com.weimi.wmmess.business.workHours.api;

import com.weimi.wmmess.business.workHours.bean.DeviceParentResbean;
import com.weimi.wmmess.business.workHours.bean.EmployeeResbean;
import com.weimi.wmmess.business.workHours.bean.ProcedureResbean;
import com.weimi.wmmess.business.workHours.bean.WorkCenterResbean;
import com.weimi.wmmess.business.workHours.bean.WorkHourDetailResbean;
import com.weimi.wmmess.business.workHours.bean.WorkHourListResbean;
import com.weimi.wmmess.business.workHours.bean.WorkTimeTypeResbean;
import com.weimi.wmmess.business.workHours.bean.WorkTypeResbean;
import com.weimi.wmmess.business.workHours.params.WmMesWorkshopProcedureWorkhoursRegister;
import com.weimi.wmmess.business.workOrder.bean.WorkOrderListResbean;
import com.weimi.wmmess.business.workOrder.params.MobileWorkOrderParam;
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
import retrofit2.http.QueryMap;

/**
 * Create by chhyu
 * on 2019/7/22
 * Describle:
 */
public interface WorkHourService {

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/workshop/pageMesWorkshopWorkhours")
    Observable<ResultModel<ListModel<WorkHourListResbean>>> loadWorkHourList(@Body GeneralParam param);

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/workshop/deleteMesWorkshopWorkhours")
    Observable<ResultModel<Object>> deleteWorkHour(@Query("recordId") String recordId);

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/workshop/pageMesCommonProductWorkOrder")
    Observable<ResultModel<ListModel<WorkOrderListResbean>>> pageMesCommonProductWorkOrder(@Body MobileWorkOrderParam param);

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/workshop/listBomProcedure")
    Observable<ResultModel<List<ProcedureResbean>>> getBomProcedureList(@Query("bomId") String bomId, @Query("craftId") String craftId);

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/workshop/listUmlWorkcenter")
    Observable<ResultModel<List<WorkCenterResbean>>> getWorkcenterList();

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/workshop/pageWorkHourKind")
    Observable<ResultModel<ListModel<WorkTimeTypeResbean>>> pageWorkHourKind(@Body GeneralParam param);

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/workshop/listAllEquipmentsByType")
    Observable<ResultModel<List<DeviceParentResbean>>> listAllEquipmentsByType();

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/workshop/pageOrgEmp")
    Observable<ResultModel<ListModel<EmployeeResbean>>> pageOrgEmp(@Body GeneralParam param);

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/workshop/pageWorkKind")
    Observable<ResultModel<ListModel<WorkTypeResbean>>> pageWorkKind(@Body GeneralParam param);

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/workshop/insertMesWorkshopWorkhours")
    Observable<ResultModel<Object>> insertMesWorkshopWorkhours(@Body WmMesWorkshopProcedureWorkhoursRegister wmMesWorkshopWorkhours);

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/workshop/getMesWorkshopWorkhoursById")
    Observable<ResultModel<WorkHourDetailResbean>> getMesWorkshopWorkhoursById(@Query("recordId") String recordId);

    @Headers({BaseUrl.HEADER_HOST})
    @POST("mes/mobile/workshop/updateMesWorkshopWorkhours")
    Observable<ResultModel<Object>> updateMesWorkshopWorkhours(@Body WmMesWorkshopProcedureWorkhoursRegister wmMesWorkshopWorkhours);
}
