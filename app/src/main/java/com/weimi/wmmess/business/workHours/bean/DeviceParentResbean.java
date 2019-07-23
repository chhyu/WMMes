package com.weimi.wmmess.business.workHours.bean;

import java.util.List;

/**
 * Create by chhyu
 * on 2019/7/22
 * Describle:
 */
public class DeviceParentResbean {

    /**
     * children : [{"equipmentCode":"M001","parentEquipmentRoot":"MOLD_TEMPERATURE","equipmentName":"M001","equipmentId":"1133721695258152960","equipmentTypeCode":"MOLD_TEMPERATURE"},{"equipmentCode":"M002","parentEquipmentRoot":"MOLD_TEMPERATURE","equipmentName":"ass","equipmentId":"1135366136792354816","equipmentTypeCode":"MOLD_TEMPERATURE"}]
     * equipmentName : 模温机
     * equipmentId : MOLD_TEMPERATURE
     */
    private List<DeviceChildResbean> children;
    private String equipmentName;
    private String equipmentId;

    public List<DeviceChildResbean> getChildren() {
        return children;
    }

    public void setChildren(List<DeviceChildResbean> children) {
        this.children = children;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }
}
