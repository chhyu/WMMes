package com.weimi.wmmess.http;

/**
 * Created by Jason on 2018/10/18.
 */
public interface UploadProgressListener {
    /**
     * 上传进度
     *
     * @param currentBytesCount
     * @param totalBytesCount
     */
    void onProgress(long currentBytesCount, long totalBytesCount);
}
