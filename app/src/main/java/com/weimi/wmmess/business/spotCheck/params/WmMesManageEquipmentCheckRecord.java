package com.weimi.wmmess.business.spotCheck.params;

/**
 * <p>
 *
 * </p>
 *
 * @author wm
 * @since 2019-07-26
 */
public class WmMesManageEquipmentCheckRecord {


    /**
     * 记录ID
     */
    private String recordId;

    /**
     * 记录编码
     */
    private String recordCode;

    /**
     * 点检类型名称
     */
    private String checkTypeName;

    /**
     * 点检类型code
     */
    private String checkTypeCode;

    /**
     * 设备Id
     */
    private String businessId;

    /**
     * 设备名称
     */
    private String businessName;

    /**
     * 设备Code
     */
    private String businessCode;


    /**
     * code
     */
    private String businessType;

    /**
     * 工单
     */
    private String workOrderId;

    /**
     * 工单Code
     */
    private String workOrderCode;

    /**
     * 删除标记
     */
    private String statusCode;

    /**
     * 创建人
     */
    private String creatorId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 更新人
     */
    private String updaterId;

    /**
     * 更新时间
     */
    private String updateTime;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getRecordCode() {
        return recordCode;
    }

    public void setRecordCode(String recordCode) {
        this.recordCode = recordCode;
    }

    public String getCheckTypeName() {
        return checkTypeName;
    }

    public void setCheckTypeName(String checkTypeName) {
        this.checkTypeName = checkTypeName;
    }

    public String getCheckTypeCode() {
        return checkTypeCode;
    }

    public void setCheckTypeCode(String checkTypeCode) {
        this.checkTypeCode = checkTypeCode;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(String workOrderId) {
        this.workOrderId = workOrderId;
    }

    public String getWorkOrderCode() {
        return workOrderCode;
    }

    public void setWorkOrderCode(String workOrderCode) {
        this.workOrderCode = workOrderCode;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(String updaterId) {
        this.updaterId = updaterId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
