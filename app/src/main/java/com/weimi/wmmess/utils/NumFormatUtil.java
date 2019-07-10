package com.weimi.wmmess.utils;

import java.math.BigDecimal;

/**
 * Create by chhyu
 * on 2019/5/23
 * Describle:
 */
public class NumFormatUtil {

    /**
     * 获取两位小数的字符串
     *
     * @param b
     * @return
     */
    public static String getDoubleNumberString(double b) {
        BigDecimal bg = new BigDecimal(b);
        double f1 = bg.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
        return String.valueOf(f1);
    }
}
