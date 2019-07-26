package com.weimi.wmmess.business.defectRegister.bean;

import java.util.List;

/**
 * Create by chhyu
 * on 2019/7/25
 * Describle:
 */
public class DefectParentResbean {

    /**
     * parentDefectId :
     * defectCode : 12
     * children : [{"parentDefectId":"1135362554319867904","defectCode":"test","createTime":"2019-06-26T15:29:09.357","updaterId":"1131791525542629376","creatorId":"1131791525542629376","defectName":"银丝","description":"2","updateTime":"2019-06-26T15:29:09.357","defectId":"1143783239236980736","defectType":"defectType2","statusCode":"0"}]
     * createTime : 2019-06-03T09:48:21.623
     * updaterId : 1131791525542629376
     * creatorId : 1128500091712442369
     * defectName : 毛边
     * description : 1
     * updateTime : 2019-06-26T15:28:29.743
     * defectId : 1135362554319867904
     * defectType : defectType1
     * statusCode : 0
     */
    private String parentDefectId;
    private String defectCode;
    private List<DefectChildResbean> children;
    private String createTime;
    private String updaterId;
    private String creatorId;
    private String defectName;
    private String description;
    private String updateTime;
    private String defectId;
    private String defectType;
    private String statusCode;

    public void setParentDefectId(String parentDefectId) {
        this.parentDefectId = parentDefectId;
    }

    public void setDefectCode(String defectCode) {
        this.defectCode = defectCode;
    }

    public void setChildren(List<DefectChildResbean> children) {
        this.children = children;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setUpdaterId(String updaterId) {
        this.updaterId = updaterId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public void setDefectName(String defectName) {
        this.defectName = defectName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public void setDefectId(String defectId) {
        this.defectId = defectId;
    }

    public void setDefectType(String defectType) {
        this.defectType = defectType;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getParentDefectId() {
        return parentDefectId;
    }

    public String getDefectCode() {
        return defectCode;
    }

    public List<DefectChildResbean> getChildren() {
        return children;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getUpdaterId() {
        return updaterId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public String getDefectName() {
        return defectName;
    }

    public String getDescription() {
        return description;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public String getDefectId() {
        return defectId;
    }

    public String getDefectType() {
        return defectType;
    }

    public String getStatusCode() {
        return statusCode;
    }

}