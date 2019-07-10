package com.weimi.wmmess.business.workOrder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.weimi.wmmess.R;
import com.weimi.wmmess.business.workOrder.bean.CraftParamsResbean;
import com.weimi.wmmess.business.workOrder.bean.CraftRebean;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by chhyu
 * on 2019/7/3
 * Describle:
 */
public class CraftParamsListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<CraftRebean> craftRebeans;

    public CraftParamsListAdapter(Context context, List<CraftRebean> craftRebeans) {
        this.context = context;
        this.craftRebeans = craftRebeans;
    }

    @Override
    public int getGroupCount() {
        return craftRebeans == null ? 0 : craftRebeans.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return craftRebeans.get(groupPosition).getCraftParamsResbeans().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return craftRebeans.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return craftRebeans.get(groupPosition).getCraftParamsResbeans().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    public void addDatas(List<CraftRebean> datas) {
        if (datas == null || datas.size() == 0) {
            return;
        }
        if (craftRebeans == null) {
            craftRebeans = new ArrayList<>();
        } else {
            craftRebeans.clear();
        }
        for (int i = 0; i < datas.size(); i++) {
            craftRebeans.add(datas.get(i));
        }
        notifyDataSetChanged();
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderOfParent holderOfParent;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_craft_params_list_parent, null);
            holderOfParent = new ViewHolderOfParent();
            holderOfParent.tvParentName = convertView.findViewById(R.id.tvParentName);
            convertView.setTag(holderOfParent);
        } else {
            holderOfParent = (ViewHolderOfParent) convertView.getTag();
        }
        CraftRebean CraftRebean = craftRebeans.get(groupPosition);
        holderOfParent.tvParentName.setText(CraftRebean.getProductCode());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderOfChild holderOfChild;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_craft_params_list_child, null);
            holderOfChild = new ViewHolderOfChild();
            holderOfChild.tvProcedureName = convertView.findViewById(R.id.tvProcedureName);
            holderOfChild.tvCraftParameterId = convertView.findViewById(R.id.tvCraftParameterId);
            holderOfChild.tvEquipmentName = convertView.findViewById(R.id.tvEquipmentName);
            holderOfChild.tvUpperLimit = convertView.findViewById(R.id.tvUpperLimit);
            holderOfChild.tvLowerLimit = convertView.findViewById(R.id.tvLowerLimit);
            holderOfChild.tvModelKernelCode = convertView.findViewById(R.id.tvModelKernelCode);
            holderOfChild.tvModelFrameCode = convertView.findViewById(R.id.tvModelFrameCode);
            convertView.setTag(holderOfChild);
        } else {
            holderOfChild = (ViewHolderOfChild) convertView.getTag();
        }
        CraftParamsResbean craftParamsResbean = craftRebeans.get(groupPosition).getCraftParamsResbeans().get(childPosition);
        holderOfChild.tvProcedureName.setText(craftParamsResbean.getProcedureName());
        holderOfChild.tvCraftParameterId.setText(craftParamsResbean.getCraftParameterId());
        holderOfChild.tvEquipmentName.setText(craftParamsResbean.getEquipmentCode());
        holderOfChild.tvUpperLimit.setText(craftParamsResbean.getUpperLimit());
        holderOfChild.tvLowerLimit.setText(craftParamsResbean.getLowerLimit());
        holderOfChild.tvModelKernelCode.setText(craftParamsResbean.getModelKernelCode());
        holderOfChild.tvModelFrameCode.setText(craftParamsResbean.getModelFrameCode());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ViewHolderOfParent {
        private TextView tvParentName;
    }

    class ViewHolderOfChild {
        private TextView tvProcedureName;
        private TextView tvCraftParameterId;
        private TextView tvEquipmentName;
        private TextView tvUpperLimit;
        private TextView tvLowerLimit;
        private TextView tvModelKernelCode;
        private TextView tvModelFrameCode;
    }
}
