package com.weimi.wmmess.business.workOrder.params;

import com.weimi.wmmess.params.GeneralParam;

/**
 * Create by chhyu
 * on 2019/7/3
 * Describle:
 */
public class MobileWorkOrderParam {

    private GeneralParam param;

    private WorkOrderVO workOrderVO;

    public GeneralParam getParam() {
        return param;
    }

    public void setParam(GeneralParam param) {
        this.param = param;
    }

    public WorkOrderVO getWorkOrderVO() {
        return workOrderVO;
    }

    public void setWorkOrderVO(WorkOrderVO workOrderVO) {
        this.workOrderVO = workOrderVO;
    }
}
