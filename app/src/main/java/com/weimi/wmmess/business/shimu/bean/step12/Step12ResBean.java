package com.weimi.wmmess.business.shimu.bean.step12;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Create by chhyu
 * on 2019/5/20
 * Describle:
 */
@Entity
public class Step12ResBean {

    @Id
    private Long step12Id;

    /**
     * 当前步骤是否检测完成
     */
    private boolean currentStepIsChecked;

    private String addZero;
    private String addOne;
    private String addTwo;
    private String addThree;
    private String addFour;
    private String addFive;
    private String addHopperOne;
    private String addHopperTwo;
    private String addOutSide;
    private String addSize;
    private String addZhiLiangOne;
    private String addZhiLiangTwo;

    private String subtractZero;
    private String subtractOne;
    private String subtractTwo;
    private String subtractThree;
    private String subtractFour;
    private String subtractFive;
    private String subtractHopperOne;
    private String subtractHopperTwo;
    private String subtractOutside;
    private String subtractSize;
    private String subtractZhiLiangOne;
    private String subtractZhiLiangTwo;

    private String addWendu;
    private String muJuAddWaiGuan;
    private String muJuAddChiCun;
    private String muJuAddZhiLiang;

    private String subtractWenDu;
    private String muJuSubtractWaiGuan;
    private String muJuSubtractChiCun;
    private String muJuSubtractZhiLiang;
    public String getMuJuSubtractZhiLiang() {
        return this.muJuSubtractZhiLiang;
    }
    public void setMuJuSubtractZhiLiang(String muJuSubtractZhiLiang) {
        this.muJuSubtractZhiLiang = muJuSubtractZhiLiang;
    }
    public String getMuJuSubtractChiCun() {
        return this.muJuSubtractChiCun;
    }
    public void setMuJuSubtractChiCun(String muJuSubtractChiCun) {
        this.muJuSubtractChiCun = muJuSubtractChiCun;
    }
    public String getMuJuSubtractWaiGuan() {
        return this.muJuSubtractWaiGuan;
    }
    public void setMuJuSubtractWaiGuan(String muJuSubtractWaiGuan) {
        this.muJuSubtractWaiGuan = muJuSubtractWaiGuan;
    }
    public String getSubtractWenDu() {
        return this.subtractWenDu;
    }
    public void setSubtractWenDu(String subtractWenDu) {
        this.subtractWenDu = subtractWenDu;
    }
    public String getMuJuAddZhiLiang() {
        return this.muJuAddZhiLiang;
    }
    public void setMuJuAddZhiLiang(String muJuAddZhiLiang) {
        this.muJuAddZhiLiang = muJuAddZhiLiang;
    }
    public String getMuJuAddChiCun() {
        return this.muJuAddChiCun;
    }
    public void setMuJuAddChiCun(String muJuAddChiCun) {
        this.muJuAddChiCun = muJuAddChiCun;
    }
    public String getMuJuAddWaiGuan() {
        return this.muJuAddWaiGuan;
    }
    public void setMuJuAddWaiGuan(String muJuAddWaiGuan) {
        this.muJuAddWaiGuan = muJuAddWaiGuan;
    }
    public String getAddWendu() {
        return this.addWendu;
    }
    public void setAddWendu(String addWendu) {
        this.addWendu = addWendu;
    }
    public String getSubtractZhiLiangTwo() {
        return this.subtractZhiLiangTwo;
    }
    public void setSubtractZhiLiangTwo(String subtractZhiLiangTwo) {
        this.subtractZhiLiangTwo = subtractZhiLiangTwo;
    }
    public String getSubtractZhiLiangOne() {
        return this.subtractZhiLiangOne;
    }
    public void setSubtractZhiLiangOne(String subtractZhiLiangOne) {
        this.subtractZhiLiangOne = subtractZhiLiangOne;
    }
    public String getSubtractSize() {
        return this.subtractSize;
    }
    public void setSubtractSize(String subtractSize) {
        this.subtractSize = subtractSize;
    }
    public String getSubtractOutside() {
        return this.subtractOutside;
    }
    public void setSubtractOutside(String subtractOutside) {
        this.subtractOutside = subtractOutside;
    }
    public String getSubtractHopperTwo() {
        return this.subtractHopperTwo;
    }
    public void setSubtractHopperTwo(String subtractHopperTwo) {
        this.subtractHopperTwo = subtractHopperTwo;
    }
    public String getSubtractHopperOne() {
        return this.subtractHopperOne;
    }
    public void setSubtractHopperOne(String subtractHopperOne) {
        this.subtractHopperOne = subtractHopperOne;
    }
    public String getSubtractFive() {
        return this.subtractFive;
    }
    public void setSubtractFive(String subtractFive) {
        this.subtractFive = subtractFive;
    }
    public String getSubtractFour() {
        return this.subtractFour;
    }
    public void setSubtractFour(String subtractFour) {
        this.subtractFour = subtractFour;
    }
    public String getSubtractThree() {
        return this.subtractThree;
    }
    public void setSubtractThree(String subtractThree) {
        this.subtractThree = subtractThree;
    }
    public String getSubtractTwo() {
        return this.subtractTwo;
    }
    public void setSubtractTwo(String subtractTwo) {
        this.subtractTwo = subtractTwo;
    }
    public String getSubtractOne() {
        return this.subtractOne;
    }
    public void setSubtractOne(String subtractOne) {
        this.subtractOne = subtractOne;
    }
    public String getSubtractZero() {
        return this.subtractZero;
    }
    public void setSubtractZero(String subtractZero) {
        this.subtractZero = subtractZero;
    }
    public String getAddZhiLiangTwo() {
        return this.addZhiLiangTwo;
    }
    public void setAddZhiLiangTwo(String addZhiLiangTwo) {
        this.addZhiLiangTwo = addZhiLiangTwo;
    }
    public String getAddZhiLiangOne() {
        return this.addZhiLiangOne;
    }
    public void setAddZhiLiangOne(String addZhiLiangOne) {
        this.addZhiLiangOne = addZhiLiangOne;
    }
    public String getAddSize() {
        return this.addSize;
    }
    public void setAddSize(String addSize) {
        this.addSize = addSize;
    }
    public String getAddOutSide() {
        return this.addOutSide;
    }
    public void setAddOutSide(String addOutSide) {
        this.addOutSide = addOutSide;
    }
    public String getAddHopperTwo() {
        return this.addHopperTwo;
    }
    public void setAddHopperTwo(String addHopperTwo) {
        this.addHopperTwo = addHopperTwo;
    }
    public String getAddHopperOne() {
        return this.addHopperOne;
    }
    public void setAddHopperOne(String addHopperOne) {
        this.addHopperOne = addHopperOne;
    }
    public String getAddFive() {
        return this.addFive;
    }
    public void setAddFive(String addFive) {
        this.addFive = addFive;
    }
    public String getAddFour() {
        return this.addFour;
    }
    public void setAddFour(String addFour) {
        this.addFour = addFour;
    }
    public String getAddThree() {
        return this.addThree;
    }
    public void setAddThree(String addThree) {
        this.addThree = addThree;
    }
    public String getAddTwo() {
        return this.addTwo;
    }
    public void setAddTwo(String addTwo) {
        this.addTwo = addTwo;
    }
    public String getAddOne() {
        return this.addOne;
    }
    public void setAddOne(String addOne) {
        this.addOne = addOne;
    }
    public String getAddZero() {
        return this.addZero;
    }
    public void setAddZero(String addZero) {
        this.addZero = addZero;
    }
    public boolean getCurrentStepIsChecked() {
        return this.currentStepIsChecked;
    }
    public void setCurrentStepIsChecked(boolean currentStepIsChecked) {
        this.currentStepIsChecked = currentStepIsChecked;
    }
    public Long getStep12Id() {
        return this.step12Id;
    }
    public void setStep12Id(Long step12Id) {
        this.step12Id = step12Id;
    }
    @Generated(hash = 1707308648)
    public Step12ResBean(Long step12Id, boolean currentStepIsChecked,
            String addZero, String addOne, String addTwo, String addThree,
            String addFour, String addFive, String addHopperOne,
            String addHopperTwo, String addOutSide, String addSize,
            String addZhiLiangOne, String addZhiLiangTwo, String subtractZero,
            String subtractOne, String subtractTwo, String subtractThree,
            String subtractFour, String subtractFive, String subtractHopperOne,
            String subtractHopperTwo, String subtractOutside, String subtractSize,
            String subtractZhiLiangOne, String subtractZhiLiangTwo,
            String addWendu, String muJuAddWaiGuan, String muJuAddChiCun,
            String muJuAddZhiLiang, String subtractWenDu,
            String muJuSubtractWaiGuan, String muJuSubtractChiCun,
            String muJuSubtractZhiLiang) {
        this.step12Id = step12Id;
        this.currentStepIsChecked = currentStepIsChecked;
        this.addZero = addZero;
        this.addOne = addOne;
        this.addTwo = addTwo;
        this.addThree = addThree;
        this.addFour = addFour;
        this.addFive = addFive;
        this.addHopperOne = addHopperOne;
        this.addHopperTwo = addHopperTwo;
        this.addOutSide = addOutSide;
        this.addSize = addSize;
        this.addZhiLiangOne = addZhiLiangOne;
        this.addZhiLiangTwo = addZhiLiangTwo;
        this.subtractZero = subtractZero;
        this.subtractOne = subtractOne;
        this.subtractTwo = subtractTwo;
        this.subtractThree = subtractThree;
        this.subtractFour = subtractFour;
        this.subtractFive = subtractFive;
        this.subtractHopperOne = subtractHopperOne;
        this.subtractHopperTwo = subtractHopperTwo;
        this.subtractOutside = subtractOutside;
        this.subtractSize = subtractSize;
        this.subtractZhiLiangOne = subtractZhiLiangOne;
        this.subtractZhiLiangTwo = subtractZhiLiangTwo;
        this.addWendu = addWendu;
        this.muJuAddWaiGuan = muJuAddWaiGuan;
        this.muJuAddChiCun = muJuAddChiCun;
        this.muJuAddZhiLiang = muJuAddZhiLiang;
        this.subtractWenDu = subtractWenDu;
        this.muJuSubtractWaiGuan = muJuSubtractWaiGuan;
        this.muJuSubtractChiCun = muJuSubtractChiCun;
        this.muJuSubtractZhiLiang = muJuSubtractZhiLiang;
    }
    @Generated(hash = 1235704158)
    public Step12ResBean() {
    }

}
