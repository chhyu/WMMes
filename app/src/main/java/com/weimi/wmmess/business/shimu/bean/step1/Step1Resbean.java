package com.weimi.wmmess.business.shimu.bean.step1;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Create by chhyu
 * on 2019/5/10
 * Describle: 第一步数据
 */
@Entity
public class Step1Resbean {

    /**
     * 与试模实体类一对一外联的key
     */
    @Id
    private Long step1ResbeanId;

    /**
     * 当前步骤是否检测完成
     */
    private boolean currentStepIsChecked;

    private boolean oneIsChecked;

    private boolean twoIsChecked;

    private boolean threeIsChecked;

    private boolean fourIsChecked;

    private boolean fiveIsChecked;

    private boolean sixIsChecked;

    public boolean getSixIsChecked() {
        return this.sixIsChecked;
    }

    public void setSixIsChecked(boolean sixIsChecked) {
        this.sixIsChecked = sixIsChecked;
    }

    public boolean getFiveIsChecked() {
        return this.fiveIsChecked;
    }

    public void setFiveIsChecked(boolean fiveIsChecked) {
        this.fiveIsChecked = fiveIsChecked;
    }

    public boolean getFourIsChecked() {
        return this.fourIsChecked;
    }

    public void setFourIsChecked(boolean fourIsChecked) {
        this.fourIsChecked = fourIsChecked;
    }

    public boolean getThreeIsChecked() {
        return this.threeIsChecked;
    }

    public void setThreeIsChecked(boolean threeIsChecked) {
        this.threeIsChecked = threeIsChecked;
    }

    public boolean getTwoIsChecked() {
        return this.twoIsChecked;
    }

    public void setTwoIsChecked(boolean twoIsChecked) {
        this.twoIsChecked = twoIsChecked;
    }

    public boolean getOneIsChecked() {
        return this.oneIsChecked;
    }

    public void setOneIsChecked(boolean oneIsChecked) {
        this.oneIsChecked = oneIsChecked;
    }

    public boolean getCurrentStepIsChecked() {
        return this.currentStepIsChecked;
    }

    public void setCurrentStepIsChecked(boolean currentStepIsChecked) {
        this.currentStepIsChecked = currentStepIsChecked;
    }

    public Long getStep1ResbeanId() {
        return this.step1ResbeanId;
    }

    public void setStep1ResbeanId(Long step1ResbeanId) {
        this.step1ResbeanId = step1ResbeanId;
    }

    @Generated(hash = 168172237)
    public Step1Resbean(Long step1ResbeanId, boolean currentStepIsChecked,
            boolean oneIsChecked, boolean twoIsChecked, boolean threeIsChecked,
            boolean fourIsChecked, boolean fiveIsChecked, boolean sixIsChecked) {
        this.step1ResbeanId = step1ResbeanId;
        this.currentStepIsChecked = currentStepIsChecked;
        this.oneIsChecked = oneIsChecked;
        this.twoIsChecked = twoIsChecked;
        this.threeIsChecked = threeIsChecked;
        this.fourIsChecked = fourIsChecked;
        this.fiveIsChecked = fiveIsChecked;
        this.sixIsChecked = sixIsChecked;
    }

    @Generated(hash = 806145695)
    public Step1Resbean() {
    }


}
