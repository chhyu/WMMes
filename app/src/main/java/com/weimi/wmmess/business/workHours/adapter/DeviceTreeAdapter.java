package com.weimi.wmmess.business.workHours.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.weimi.wmmess.R;
import com.weimi.wmmess.business.workHours.bean.DeviceChildResbean;
import com.weimi.wmmess.business.workHours.bean.DeviceParentResbean;
import com.weimi.wmmess.business.workOrder.bean.CraftRebean;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by chhyu
 * on 2019/7/23
 * Describle: 设备树adapter
 */
public class DeviceTreeAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<DeviceParentResbean> list;

    public DeviceTreeAdapter(Context context, List<DeviceParentResbean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getGroupCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        List<DeviceChildResbean> children = list.get(groupPosition).getChildren();
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

    public void addDatas(List<DeviceParentResbean> datas) {
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

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderOfParent holderOfParent;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_device_list_parent, null);
            holderOfParent = new ViewHolderOfParent();
            holderOfParent.tvParentName = convertView.findViewById(R.id.tvParentName);
            convertView.setTag(holderOfParent);
        } else {
            holderOfParent = (ViewHolderOfParent) convertView.getTag();
        }
        DeviceParentResbean deviceParentResbean = list.get(groupPosition);
        holderOfParent.tvParentName.setText(deviceParentResbean.getEquipmentName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderOfChild holderOfChild;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_device_list_child, null);
            holderOfChild = new ViewHolderOfChild();
            holderOfChild.tvDeviceName = convertView.findViewById(R.id.tvDeviceName);
            holderOfChild.tvDeviceId = convertView.findViewById(R.id.tvDeviceId);
            holderOfChild.tvDeviceCode = convertView.findViewById(R.id.tvDeviceCode);
            convertView.setTag(holderOfChild);
        } else {
            holderOfChild = (ViewHolderOfChild) convertView.getTag();
        }
        DeviceChildResbean deviceChildResbean = list.get(groupPosition).getChildren().get(childPosition);
        holderOfChild.tvDeviceName.setText(deviceChildResbean.getEquipmentName());
        holderOfChild.tvDeviceId.setText(deviceChildResbean.getEquipmentId());
        holderOfChild.tvDeviceCode.setText(deviceChildResbean.getEquipmentCode());
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
        private TextView tvDeviceName;
        private TextView tvDeviceId;
        private TextView tvDeviceCode;
    }
}
