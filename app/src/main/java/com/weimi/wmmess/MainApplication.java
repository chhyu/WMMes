package com.weimi.wmmess;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.Logger;
import com.weimi.wmmess.base.lifecycled.WGAActivityLifecycleCallbacks;
import com.weimi.wmmess.constants.Constants;
import com.weimi.wmmess.business.shimu.bean.DaoMaster;
import com.weimi.wmmess.business.shimu.bean.DaoSession;
import com.weimi.wmmess.business.shimu.bean.ShiMuResbean;
import com.weimi.wmmess.business.shimu.bean.ShiMuResbeanDao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainApplication extends Application {

    public static long thisTimeId = System.currentTimeMillis();
    public static DaoSession daoSession;
    private static MainApplication sInstance;

    public static MainApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        registerActivityLifecycleCallbacks(new WGAActivityLifecycleCallbacks());
        daoSession = DaoMaster.newDevSession(this, Constants.SHUMU_DB_NAME);

        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.addLogAdapter(new DiskLogAdapter());
    }
}
