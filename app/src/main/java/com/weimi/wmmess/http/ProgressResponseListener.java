package com.weimi.wmmess.http;

/**
 * Created by Jason on 2018/10/18.
 */
public interface ProgressResponseListener {
    void onResponseProgress(long bytesRead, long contentLength, boolean done);
}
