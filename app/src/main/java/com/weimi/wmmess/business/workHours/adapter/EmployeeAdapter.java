package com.weimi.wmmess.business.workHours.adapter;

import android.content.Context;

import com.weimi.wmmess.R;
import com.weimi.wmmess.base.adapter.CommAdapter;
import com.weimi.wmmess.base.adapter.ViewHolder;
import com.weimi.wmmess.business.workHours.bean.EmployeeResbean;

/**
 * Create by chhyu
 * on 2019/7/23
 * Describle:
 */
public class EmployeeAdapter extends CommAdapter<EmployeeResbean> {
    public EmployeeAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, EmployeeResbean employeeResbean, int position) {
        super.convert(holder, employeeResbean, position);

        holder.setText(R.id.tvEmpName, employeeResbean.getEmpName());
        holder.setText(R.id.tvEmpId, employeeResbean.getEmpId());
        holder.setText(R.id.tvEmpCode, employeeResbean.getEmpCode());
        holder.setText(R.id.tvEmail, employeeResbean.getEmail());
        holder.setText(R.id.tvTelephoneNumber, employeeResbean.getMobileNo());
    }
}
