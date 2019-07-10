package com.weimi.wmmess.params;

/**
 * Create by chhyu
 * on 2019/7/3
 * Describle: 分页信息参数
 */
public class PageInfoParams {

    private int size;
    private int current;

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
