package com.weimi.wmmess;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.Logger;
import com.weimi.wmmess.base.lifecycled.WGAActivityLifecycleCallbacks;
import com.weimi.wmmess.business.shimu.bean.DaoMaster;
import com.weimi.wmmess.business.shimu.bean.DaoSession;
import com.weimi.wmmess.constants.Constants;

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

//        File file = new File(Environment.getExternalStorageDirectory(), "ticket.txt");
//        File file = new File(Environment.getExternalStorageDirectory(), "test.apk");
//        Log.e("lmsg", file.getAbsolutePath());
//        try {
//            FileInputStream fileInputStream = new FileInputStream(file);
//
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
