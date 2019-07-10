package com.weimi.wmmess.model;

import java.util.List;

/**
 * 列表模型
 * Created by chhyu on 2019/6/28.
 */
public class ListModel<T> {

    /**
     * dataList : []
     * current : 1
     * pages : 1
     * searchCount : true
     * size : 10
     * total : 2
     */

    private int current;
    private int pages;
    private boolean searchCount;
    private int size;
    private int total;
    private List<T> records;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public boolean isSearchCount() {
        return searchCount;
    }

    public void setSearchCount(boolean searchCount) {
        this.searchCount = searchCount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public boolean isHasNextPage() {
        if (getTotal() - (getCurrent() * size) > 0) {
            return true;
        }
        return false;
    }
}
