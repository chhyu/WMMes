package com.weimi.wmmess.business.defectRegister.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.weimi.wmmess.R;
import com.weimi.wmmess.business.defectRegister.bean.DefectChildResbean;
import com.weimi.wmmess.business.defectRegister.bean.DefectParentResbean;
import com.weimi.wmmess.business.workHours.bean.DeviceParentResbean;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by chhyu
 * on 2019/7/25
 * Describle:
 */
public class DefectTreeAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<DefectParentResbean> list;

    public DefectTreeAdapter(Context context, List<DefectParentResbean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getGroupCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        List<DefectChildResbean> children = list.get(groupPosition).getChildren();
        return children == null ? 0 : children.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return list.get(groupPosition).getChildren().get(childPosition);
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

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ParentViewHolder holderOfParent;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_device_list_parent, null);
            holderOfParent = new ParentViewHolder();
            holderOfParent.tvParentDefectName = convertView.findViewById(R.id.tvParentName);
            convertView.setTag(holderOfParent);
        } else {
            holderOfParent = (ParentViewHolder) convertView.getTag();
        }
        DefectParentResbean defectParentResbean = list.get(groupPosition);
        holderOfParent.tvParentDefectName.setText(defectParentResbean.getDefectName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder holderOfChild;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_device_list_child, null);
            holderOfChild = new ChildViewHolder();
            holderOfChild.tvDefectName = convertView.findViewById(R.id.tvDeviceName);
            holderOfChild.tvDefectId = convertView.findViewById(R.id.tvDeviceId);
            holderOfChild.tvDefectCode = convertView.findViewById(R.id.tvDeviceCode);
            convertView.setTag(holderOfChild);
        } else {
            holderOfChild = (ChildViewHolder) convertView.getTag();
        }
        DefectChildResbean defectChildResbean = list.get(groupPosition).getChildren().get(childPosition);
        holderOfChild.tvDefectName.setText(defectChildResbean.getDefectName());
        holderOfChild.tvDefectId.setText(defectChildResbean.getDefectId());
        holderOfChild.tvDefectCode.setText(defectChildResbean.getDefectCode());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public void addDatas(List<DefectParentResbean> datas) {
        if (datas == null || datas.size() == 0) {
            return;
        }
        if (list == null) {
            list = new ArrayList<>();
        } else {
            list.clear();
        }
        for (int i = 0; i < datas.size(); i++) {
            list.add(datas.get(i));
        }
        notifyDataSetChanged();
    }

    class ParentViewHolder {
        private TextView tvParentDefectName;
    }

    class ChildViewHolder {
        private TextView tvDefectName;
        private TextView tvDefectId;
        private TextView tvDefectCode;
    }
}
