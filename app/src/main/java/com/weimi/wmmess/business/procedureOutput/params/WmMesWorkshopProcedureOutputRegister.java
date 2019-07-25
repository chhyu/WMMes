package com.weimi.wmmess.business.procedureOutput.params;


import com.weimi.wmmess.business.procedureOutput.bean.ProcedureOutputDetailResbean;

/**
 * <p>
 *
 * </p>
 *
 * @author wm
 * @since 2019-07-16
 */
public class WmMesWorkshopProcedureOutputRegister {

    private static final long serialVersionUID = 1L;

    /**
     * 记录主键
     */
    private String recordId;

    /**
     * 凭证Id
     */
    private String proofId;

    /**
     * 凭证日期
     */
    private String proofDate;

    /**
     * 工单Id
     */
    private String workOrderId;

    /**
     * 工单code
     */
    private String workOrderCode;

    /**
     * 设备Id
     */
    private String equipmentId;

    /**
     * 设备名称
     */
    private String equipmentName;

    /**
     * 工作中心名称
     */
    private String workcenterName;

    /**
     * 工作中心id
     */
    private String workcenterId;

    /**
     * 设备编码
     */
    private String equipmentCode;

    /**
     * 工序Id
     */
    private String procedureId;

    /**
     * 工序名称
     */
    private String procedureName;

    /**
     * 员工Id
     */
    private String empId;

    /**
     * 员工编码
     */
    private String empCode;

    /**
     * 员工名称
     */
    private String empName;

    /**
     * 数量
     */
    private Integer amount;

    /**
     * 计划开始时间
     */
    private String planStartTime;

    /**
     * 计划结束时间
     */
    private String planEndTime;

    /**
     * 实际开始时间
     */
    private String actualStartTime;

    /**
     * 实际结束时间
     */
    private String actualEndTime;

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

    /**
     * 上一次的数量
     */
    private Integer lastAmount;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getProofId() {
        return proofId;
    }

    public void setProofId(String proofId) {
        this.proofId = proofId;
    }

    public String getProofDate() {
        return proofDate;
    }

    public void setProofDate(String proofDate) {
        this.proofDate = proofDate;
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

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getWorkcenterName() {
        return workcenterName;
    }

    public void setWorkcenterName(String workcenterName) {
        this.workcenterName = workcenterName;
    }

    public String getWorkcenterId() {
        return workcenterId;
    }

    public void setWorkcenterId(String workcenterId) {
        this.workcenterId = workcenterId;
    }

    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    public String getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(String procedureId) {
        this.procedureId = procedureId;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getPlanStartTime() {
        return planStartTime;
    }

    public void setPlanStartTime(String planStartTime) {
        this.planStartTime = planStartTime;
    }

    public String getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(String planEndTime) {
        this.planEndTime = planEndTime;
    }

    public String getActualStartTime() {
        return actualStartTime;
    }

    public void setActualStartTime(String actualStartTime) {
        this.actualStartTime = actualStartTime;
    }

    public String getActualEndTime() {
        return actualEndTime;
    }

    public void setActualEndTime(String actualEndTime) {
        this.actualEndTime = actualEndTime;
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

    public Integer getLastAmount() {
        return lastAmount;
    }

    public void setLastAmount(Integer lastAmount) {
        this.lastAmount = lastAmount;
    }

    /**
     *
     * @param bean
     */
    public void copyData2Self(ProcedureOutputDetailResbean bean) {
        this.setRecordId(bean.getRecordId());

        this.setLastAmount(bean.getAmount());//上一次的数量

        this.setWorkOrderId(bean.getWorkOrderId());
        this.setWorkOrderCode(bean.getWorkOrderCode());

        this.setProcedureName(bean.getProcedureName());
        this.setProcedureId(bean.getProcedureId());

        this.setWorkcenterName(bean.getWorkcenterName());
        this.setWorkcenterId(bean.getWorkcenterId());

        this.setEquipmentCode(bean.getEquipmentCode());
        this.setEquipmentId(bean.getEquipmentId());
        this.setEquipmentName(bean.getEquipmentName());

        this.setEmpCode(bean.getEmpCode());
        this.setEmpId(bean.getEmpId());
        this.setEmpName(bean.getEmpName());

        this.setAmount(bean.getAmount());

        this.setPlanStartTime(bean.getPlanStartTime());
        this.setPlanEndTime(bean.getPlanEndTime());

        this.setActualStartTime(bean.getActualStartTime());
        this.setActualEndTime(bean.getActualEndTime());
    }
}
