package com.weimi.wmmess.params;

/**
 * Create by chhyu
 * on 2019/7/2
 * Describle: 工单列表参数
 */
public class WorkListParams {

    private String keyword;
    private int size;
    private int current;
    private String workOrderType;

    public String getWorkOrderType() {
        return workOrderType;
    }

    public void setWorkOrderType(String workOrderType) {
        this.workOrderType = workOrderType;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
}
