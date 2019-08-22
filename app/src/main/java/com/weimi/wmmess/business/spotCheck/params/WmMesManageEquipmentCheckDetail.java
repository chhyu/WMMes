package com.weimi.wmmess.business.spotCheck.params;

/**
 * <p>
 *
 * </p>
 *
 * @author wm
 * @since 2019-07-26
 */
public class WmMesManageEquipmentCheckDetail {

    /**
     * 详情主键
     */
    private String detailId;

    /**
     * 记录主键
     */
    private String recordId;

    /**
     * 点检项目主键
     */
    private String contentId;

    /**
     * 项目名称
     */
    private String content;

    /**
     * 点检结果
     */
    private String result;

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

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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
