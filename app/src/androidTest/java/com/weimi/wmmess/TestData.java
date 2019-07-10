package com.weimi.wmmess;

import android.support.test.InstrumentationRegistry;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.weimi.wmmess.business.shimu.bean.ShiMuResbean;
import com.weimi.wmmess.business.workOrder.presenter.WorkOrderPresenter;
import com.weimi.wmmess.utils.RSAEncryptUtils;
import com.weimi.wmmess.utils.TimeUtils;

import org.junit.Test;

import java.util.Date;

/**
 * Create by chhyu
 * on 2019/6/3
 * Describle:
 */
public class TestData {

    @Test
    public void testDate() {

        ShiMuResbean shiMuResbean = MainApplication.daoSession.getShiMuResbeanDao().load(MainApplication.thisTimeId);

        Log.e("lmsg", "shiMuResbean==" + JSON.toJSONString(shiMuResbean));
    }

    @Test
    public void testRSAEncry() {
        String s = RSAEncryptUtils.encrypt("123456");
        Log.e("lmsg", "s==" + s);


        String decrypt = RSAEncryptUtils.decrypt(s);
        Log.e("lmsg", "decrypt==" + decrypt);
    }

    @Test
    public void testCurrentTime() {
        String formatTime = TimeUtils.getFormatTime(new Date());
        Log.e("lmsg", "formatTime==" + formatTime);
    }
}
