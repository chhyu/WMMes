package com.weimi.wmmess.widget.datepick.listener;


import com.weimi.wmmess.widget.datepick.bean.DateBean;
import com.weimi.wmmess.widget.datepick.bean.DateType;

/**
 * Created by codbking on 2016/9/22.
 */

public interface WheelPickerListener {
     void onSelect(DateType type, DateBean bean);
}
