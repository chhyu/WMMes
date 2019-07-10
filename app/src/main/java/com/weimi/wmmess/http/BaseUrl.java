package com.weimi.wmmess.http;


import com.weimi.wmmess.constant.HostAddress;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jason on 2018/8/6.
 */
public class BaseUrl {

    private static final String HOST = "login";


    public static final String KEY = "urlName";
    public static final String[] VALUES = {HOST};

    public static final String HEADER_HOST = KEY + ":" + HOST;


    private static final Map<String, String> MAP = new HashMap<String, String>() {
        {
            put(VALUES[0], HostAddress.HOST_API);
        }
    };

    public static final String getBaseUrl(String value) {
        return MAP.get(value);
    }
}
