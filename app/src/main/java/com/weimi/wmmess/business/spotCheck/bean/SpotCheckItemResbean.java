package com.weimi.wmmess.business.spotCheck.bean;

/**
 * Create by chhyu
 * on 2019/7/26
 * Describle: 点检项
 */
public class SpotCheckItemResbean {

    /**
     * createTime : 2019-07-26T10:29:13.750
     * updaterId : 1141542354835214336
     * checkTypeId : 1154236205697863680
     * contentId : 1154579396158558209
     * creatorId : 1141542354835214336
     * updateTime : 2019-07-26T10:29:13.750
     * content : 3.加温是否正常
     * statusCode : 0
     */
    private String createTime;
    private String updaterId;
    private String checkTypeId;
    private String contentId;
    private String creatorId;
    private String updateTime;
    private String content;
    private String statusCode;

    //增加结果 true or false
    private boolean checkResult;

    public boolean isCheckResult() {
        return checkResult;
    }

    public void setCheckResult(boolean checkResult) {
        this.checkResult = checkResult;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setUpdaterId(String updaterId) {
        this.updaterId = updaterId;
    }

    public void setCheckTypeId(String checkTypeId) {
        this.checkTypeId = checkTypeId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getCreateTime() {
        return createTime;
    }

    public String getUpdaterId() {
        return updaterId;
    }

    public String getCheckTypeId() {
        return checkTypeId;
    }

    public String getContentId() {
        return contentId;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public String getContent() {
        return content;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
