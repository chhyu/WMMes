package com.weimi.wmmess.business.spotCheck.params;

import java.util.List;

/**
 * Create by chhyu
 * on 2019/7/26
 * Describle:
 */
public class MobileInspectRecordParam extends WmMesManageEquipmentCheckRecord {

    private List<WmMesManageEquipmentCheckDetail> checkDetails;

    public List<WmMesManageEquipmentCheckDetail> getCheckDetails() {
        return checkDetails;
    }

    public void setCheckDetails(List<WmMesManageEquipmentCheckDetail> checkDetails) {
        this.checkDetails = checkDetails;
    }
}
