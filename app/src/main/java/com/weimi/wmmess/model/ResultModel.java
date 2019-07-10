package com.weimi.wmmess.model;

/**
 * 网络数据模型
 * Created by Jason on 2017/5/2.
 */
public class ResultModel<T> {

//    {"data":"1145872630394654720","status":"1","useTime":0.0}

    /**
     * data : 1145872630394654720
     * useTime : 0.0
     * status : 1
     */
    private T data;
    private double useTime;
    private String status;

    public void setData(T data) {
        this.data = data;
    }

    public void setUseTime(double useTime) {
        this.useTime = useTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public double getUseTime() {
        return useTime;
    }

    public String getStatus() {
        return status;
    }
}
