package com.weimi.wmmess.business.workHours.params;

import com.weimi.wmmess.business.workHours.bean.WorkHourDetailResbean;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author wm
 * @since 2019-07-16
 */
public class WmMesWorkshopProcedureWorkhoursRegister {


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
    private LocalDateTime proofDate;

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
     * 工作中心
     */
    private String workcenterId;

    /**
     * 工作中心名称
     */
    private String workcenterName;

    /**
     * 工种
     */
    private String workKindId;

    /**
     * 工种名称
     */
    private String workKindName;

    /**
     * 工时
     */
    private String workHourKindId;

    /**
     * 工时名称
     */
    private String workHourKindName;

    /**
     * 数量
     */
    private Integer amount;

    /**
     * 单价
     */
    private Double singlePrice;

    /**
     * 班次
     */
    private String shift;

    /**
     * 工作时间
     */
    private String workStartTime;

    /**
     * 持续时间
     */
    private Double workTime;

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
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private String updaterId;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

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

    public LocalDateTime getCreateTime() {
        return createTime;
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

    public LocalDateTime getProofDate() {
        return proofDate;
    }

    public void setProofDate(LocalDateTime proofDate) {
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

    public String getWorkcenterId() {
        return workcenterId;
    }

    public void setWorkcenterId(String workcenterId) {
        this.workcenterId = workcenterId;
    }

    public String getWorkcenterName() {
        return workcenterName;
    }

    public void setWorkcenterName(String workcenterName) {
        this.workcenterName = workcenterName;
    }

    public String getWorkKindId() {
        return workKindId;
    }

    public void setWorkKindId(String workKindId) {
        this.workKindId = workKindId;
    }

    public String getWorkKindName() {
        return workKindName;
    }

    public void setWorkKindName(String workKindName) {
        this.workKindName = workKindName;
    }

    public String getWorkHourKindId() {
        return workHourKindId;
    }

    public void setWorkHourKindId(String workHourKindId) {
        this.workHourKindId = workHourKindId;
    }

    public String getWorkHourKindName() {
        return workHourKindName;
    }

    public void setWorkHourKindName(String workHourKindName) {
        this.workHourKindName = workHourKindName;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(Double singlePrice) {
        this.singlePrice = singlePrice;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getWorkStartTime() {
        return workStartTime;
    }

    public void setWorkStartTime(String workStartTime) {
        this.workStartTime = workStartTime;
    }

    public Double getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Double workTime) {
        this.workTime = workTime;
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

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(String updaterId) {
        this.updaterId = updaterId;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 将数据赋值给自己
     *
     * @param bean
     */
    public void copyData2Self(WorkHourDetailResbean bean) {
        this.setWorkOrderCode(bean.getWorkOrderCode());
        this.setWorkOrderId(bean.getWorkOrderId());

        this.setProcedureId(bean.getProcedureId());
        this.setProcedureName(bean.getProcedureName());

        this.setWorkcenterId(bean.getWorkcenterId());
        this.setWorkcenterName(bean.getWorkcenterName());

        this.setWorkHourKindId(bean.getWorkHourKindId());
        this.setWorkHourKindName(bean.getWorkHourKindName());

        this.setSinglePrice(bean.getSinglePrice());

        this.setWorkStartTime(bean.getWorkStartTime());

        this.setEquipmentCode(bean.getEquipmentCode());
        this.setEquipmentId(bean.getEquipmentId());
        this.setEquipmentName(bean.getEquipmentName());

        this.setEmpCode(bean.getEmpCode());
        this.setEmpId(bean.getEmpId());
        this.setEmpName(bean.getEmpName());

        this.setWorkKindId(bean.getWorkKindId());
        this.setWorkKindName(bean.getWorkKindName());

        this.setAmount(bean.getAmount());

        this.setShift(bean.getShift());

        this.setWorkTime(bean.getWorkTime());

        this.setActualEndTime(bean.getActualEndTime());

        this.setRecordId(bean.getRecordId());
    }
}
