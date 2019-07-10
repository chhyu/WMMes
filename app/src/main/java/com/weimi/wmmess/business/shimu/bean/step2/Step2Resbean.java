package com.weimi.wmmess.business.shimu.bean.step2;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Create by chhyu
 * on 2019/5/11
 * Describle: 第二步数据实体类
 */
@Entity
public class Step2Resbean {

    @Id
    private Long step2Id;

    /**
     * 当前步骤是否检测完成
     */
    private boolean currentStepIsChecked;

    //直接用字母给选赋值1==OK  2==NG  3==NA
    private int a;

    private int b;

    private int c;

    private int d;

    private int e;

    private int f;

    private int g;

    private int h;

    private int i;

    private int j;

    private int k;

    private int l;

    private int m;

    private int n;

    private int o;

    public int getO() {
        return this.o;
    }

    public void setO(int o) {
        this.o = o;
    }

    public int getN() {
        return this.n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getM() {
        return this.m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getL() {
        return this.l;
    }

    public void setL(int l) {
        this.l = l;
    }

    public int getK() {
        return this.k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public int getJ() {
        return this.j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getI() {
        return this.i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getH() {
        return this.h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getG() {
        return this.g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getF() {
        return this.f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getE() {
        return this.e;
    }

    public void setE(int e) {
        this.e = e;
    }

    public int getD() {
        return this.d;
    }

    public void setD(int d) {
        this.d = d;
    }

    public int getC() {
        return this.c;
    }

    public void setC(int c) {
        this.c = c;
    }

    public int getB() {
        return this.b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getA() {
        return this.a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public boolean getCurrentStepIsChecked() {
        return this.currentStepIsChecked;
    }

    public void setCurrentStepIsChecked(boolean currentStepIsChecked) {
        this.currentStepIsChecked = currentStepIsChecked;
    }

    public Long getStep2Id() {
        return this.step2Id;
    }

    public void setStep2Id(Long step2Id) {
        this.step2Id = step2Id;
    }

    @Generated(hash = 1834690569)
    public Step2Resbean(Long step2Id, boolean currentStepIsChecked, int a, int b,
            int c, int d, int e, int f, int g, int h, int i, int j, int k, int l,
            int m, int n, int o) {
        this.step2Id = step2Id;
        this.currentStepIsChecked = currentStepIsChecked;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.i = i;
        this.j = j;
        this.k = k;
        this.l = l;
        this.m = m;
        this.n = n;
        this.o = o;
    }

    @Generated(hash = 954488755)
    public Step2Resbean() {
    }


}
