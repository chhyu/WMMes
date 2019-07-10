package com.weimi.wmmess.business.shimu.params;

import java.util.List;

/**
 * Create by chhyu
 * on 2019/7/8
 * Describle:
 */
public class MobileTrialModeSaveParam {

    private List<MobileTrialModeParam> trialModeParams;
    private String workOrderId;
    private String procedureId;

    public List<MobileTrialModeParam> getTrialModeParams() {
        return trialModeParams;
    }

    public void setTrialModeParams(List<MobileTrialModeParam> trialModeParams) {
        this.trialModeParams = trialModeParams;
    }

    public String getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(String workOrderId) {
        this.workOrderId = workOrderId;
    }

    public String getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(String procedureId) {
        this.procedureId = procedureId;
    }
}
