package com.weimi.wmmess.constants;

/**
 * Created by chhyu on 2019/7/2.
 */
public class ConstantValues {

    public static final int MAX_ATTACHMENT_COUNT = 9;

    public static final long UPDATE_NO_DISTURB_DURATION = 4 * 1000 * 60 * 60;   //四小时之内不再提示更新（除非是强制更新）;

    public static final String SF_VERSION = "version";

    public static final String SF_FIRST = "isFirstStart";

    // SharedPreferences本地Token标识
    public static final String SF_User = "user";

    public static final String SF_TOKEN = "token";

    public static final String SF_CMP_User = "cmpUser";

    public static final String SF_Fingerprint = "Fingerprint";  //指纹密码bool

    public static final String SF_GestureLock = "GestureLock";  //手势密码String
}
