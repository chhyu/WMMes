package com.weimi.wmmess.business.shimu.bean;

/**
 * Create by chhyu
 * on 2019/4/28
 * Describle: 步骤（头部）
 */
public class StepResbean {

    /**
     * 当前流程是否已走过
     */
    private boolean isChecked;

    /**
     * 步骤名称
     */
    private String stepName;

    /**
     * 当前步骤数
     */
    private int currentStepPosition;

    public StepResbean(boolean isChecked, String stepName, int currentStepPosition) {
        this.isChecked = isChecked;
        this.stepName = stepName;
        this.currentStepPosition = currentStepPosition;
    }

    public int getCurrentStepPosition() {
        return currentStepPosition;
    }

    public void setCurrentStepPosition(int currentStepPosition) {
        this.currentStepPosition = currentStepPosition;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }
}
