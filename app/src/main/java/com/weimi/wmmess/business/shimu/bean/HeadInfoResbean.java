package com.weimi.wmmess.business.shimu.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Create by chhyu
 * on 2019/5/11
 * Describle: 头部信息（）
 */
@Entity
public class HeadInfoResbean {

    @Id
    private Long headInfoId;

    private String date;       //日期

    private String mujuBianHao; //模具编号

    private String xiangmuBianHao;//项目编号

    private String liuDaoLeiXing;//流到类型

    private String jiQiBianHao;  //机器编号

    private String jiQiDunWei;  //机器吨位

    private String luoGanZhiJing;//螺杆直径

    private String sheZuiZhiJing; //射嘴直径

    private String chanPinMingCheng;//产品名称

    private String chanPinBianHao; //产品编号

    private String keHuLiaoHao;     //客户料号

    private String chanPinBanBen;  //产品版本

    private String yuanLiaoMingCheng; //原料名称

    private String yuanLiaoLiaoHao; //原料料号

    private String yuanLiaoGongYingShang;//原料供应商

    private String RLDJRDS;//热流道加热点数

    private String ganZaoWenDu;     //干燥温度

    private String ganZaoShiJian;   //干燥时间

    private String yuanLiaoHanShuiFen; //原料含水分

    private String chengXingWenDu;  //成型温度

    private String muJuWenDu;       //模具温度

    private String sheChuYaLi;      //射出压力

    private String sheChuShiJian;   //射出时间

    private String sheChuSuDu;      //射出速度

    private String baoYaYaLi;       //保压压力

    private String baoYaShiJian;    //保压时间

    private String suoMuLi;         //锁模力

    private String kaiHeMuShiJian;  //开合模时间

    private String lengQueShiJian;  //冷却时间

    private String luoGanSuDu;      //螺杆速度

    private String dingTuiChuShiJian;//定退出时间

    private String beiYa;           //背压

    public String getBeiYa() {
        return this.beiYa;
    }

    public void setBeiYa(String beiYa) {
        this.beiYa = beiYa;
    }

    public String getDingTuiChuShiJian() {
        return this.dingTuiChuShiJian;
    }

    public void setDingTuiChuShiJian(String dingTuiChuShiJian) {
        this.dingTuiChuShiJian = dingTuiChuShiJian;
    }

    public String getLuoGanSuDu() {
        return this.luoGanSuDu;
    }

    public void setLuoGanSuDu(String luoGanSuDu) {
        this.luoGanSuDu = luoGanSuDu;
    }

    public String getLengQueShiJian() {
        return this.lengQueShiJian;
    }

    public void setLengQueShiJian(String lengQueShiJian) {
        this.lengQueShiJian = lengQueShiJian;
    }

    public String getKaiHeMuShiJian() {
        return this.kaiHeMuShiJian;
    }

    public void setKaiHeMuShiJian(String kaiHeMuShiJian) {
        this.kaiHeMuShiJian = kaiHeMuShiJian;
    }

    public String getSuoMuLi() {
        return this.suoMuLi;
    }

    public void setSuoMuLi(String suoMuLi) {
        this.suoMuLi = suoMuLi;
    }

    public String getBaoYaShiJian() {
        return this.baoYaShiJian;
    }

    public void setBaoYaShiJian(String baoYaShiJian) {
        this.baoYaShiJian = baoYaShiJian;
    }

    public String getBaoYaYaLi() {
        return this.baoYaYaLi;
    }

    public void setBaoYaYaLi(String baoYaYaLi) {
        this.baoYaYaLi = baoYaYaLi;
    }

    public String getSheChuSuDu() {
        return this.sheChuSuDu;
    }

    public void setSheChuSuDu(String sheChuSuDu) {
        this.sheChuSuDu = sheChuSuDu;
    }

    public String getSheChuShiJian() {
        return this.sheChuShiJian;
    }

    public void setSheChuShiJian(String sheChuShiJian) {
        this.sheChuShiJian = sheChuShiJian;
    }

    public String getSheChuYaLi() {
        return this.sheChuYaLi;
    }

    public void setSheChuYaLi(String sheChuYaLi) {
        this.sheChuYaLi = sheChuYaLi;
    }

    public String getMuJuWenDu() {
        return this.muJuWenDu;
    }

    public void setMuJuWenDu(String muJuWenDu) {
        this.muJuWenDu = muJuWenDu;
    }

    public String getChengXingWenDu() {
        return this.chengXingWenDu;
    }

    public void setChengXingWenDu(String chengXingWenDu) {
        this.chengXingWenDu = chengXingWenDu;
    }

    public String getYuanLiaoHanShuiFen() {
        return this.yuanLiaoHanShuiFen;
    }

    public void setYuanLiaoHanShuiFen(String yuanLiaoHanShuiFen) {
        this.yuanLiaoHanShuiFen = yuanLiaoHanShuiFen;
    }

    public String getGanZaoShiJian() {
        return this.ganZaoShiJian;
    }

    public void setGanZaoShiJian(String ganZaoShiJian) {
        this.ganZaoShiJian = ganZaoShiJian;
    }

    public String getGanZaoWenDu() {
        return this.ganZaoWenDu;
    }

    public void setGanZaoWenDu(String ganZaoWenDu) {
        this.ganZaoWenDu = ganZaoWenDu;
    }

    public String getRLDJRDS() {
        return this.RLDJRDS;
    }

    public void setRLDJRDS(String RLDJRDS) {
        this.RLDJRDS = RLDJRDS;
    }

    public String getYuanLiaoGongYingShang() {
        return this.yuanLiaoGongYingShang;
    }

    public void setYuanLiaoGongYingShang(String yuanLiaoGongYingShang) {
        this.yuanLiaoGongYingShang = yuanLiaoGongYingShang;
    }

    public String getYuanLiaoLiaoHao() {
        return this.yuanLiaoLiaoHao;
    }

    public void setYuanLiaoLiaoHao(String yuanLiaoLiaoHao) {
        this.yuanLiaoLiaoHao = yuanLiaoLiaoHao;
    }

    public String getYuanLiaoMingCheng() {
        return this.yuanLiaoMingCheng;
    }

    public void setYuanLiaoMingCheng(String yuanLiaoMingCheng) {
        this.yuanLiaoMingCheng = yuanLiaoMingCheng;
    }

    public String getChanPinBanBen() {
        return this.chanPinBanBen;
    }

    public void setChanPinBanBen(String chanPinBanBen) {
        this.chanPinBanBen = chanPinBanBen;
    }

    public String getKeHuLiaoHao() {
        return this.keHuLiaoHao;
    }

    public void setKeHuLiaoHao(String keHuLiaoHao) {
        this.keHuLiaoHao = keHuLiaoHao;
    }

    public String getChanPinBianHao() {
        return this.chanPinBianHao;
    }

    public void setChanPinBianHao(String chanPinBianHao) {
        this.chanPinBianHao = chanPinBianHao;
    }

    public String getChanPinMingCheng() {
        return this.chanPinMingCheng;
    }

    public void setChanPinMingCheng(String chanPinMingCheng) {
        this.chanPinMingCheng = chanPinMingCheng;
    }

    public String getSheZuiZhiJing() {
        return this.sheZuiZhiJing;
    }

    public void setSheZuiZhiJing(String sheZuiZhiJing) {
        this.sheZuiZhiJing = sheZuiZhiJing;
    }

    public String getLuoGanZhiJing() {
        return this.luoGanZhiJing;
    }

    public void setLuoGanZhiJing(String luoGanZhiJing) {
        this.luoGanZhiJing = luoGanZhiJing;
    }

    public String getJiQiDunWei() {
        return this.jiQiDunWei;
    }

    public void setJiQiDunWei(String jiQiDunWei) {
        this.jiQiDunWei = jiQiDunWei;
    }

    public String getJiQiBianHao() {
        return this.jiQiBianHao;
    }

    public void setJiQiBianHao(String jiQiBianHao) {
        this.jiQiBianHao = jiQiBianHao;
    }

    public String getLiuDaoLeiXing() {
        return this.liuDaoLeiXing;
    }

    public void setLiuDaoLeiXing(String liuDaoLeiXing) {
        this.liuDaoLeiXing = liuDaoLeiXing;
    }

    public String getXiangmuBianHao() {
        return this.xiangmuBianHao;
    }

    public void setXiangmuBianHao(String xiangmuBianHao) {
        this.xiangmuBianHao = xiangmuBianHao;
    }

    public String getMujuBianHao() {
        return this.mujuBianHao;
    }

    public void setMujuBianHao(String mujuBianHao) {
        this.mujuBianHao = mujuBianHao;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getHeadInfoId() {
        return this.headInfoId;
    }

    public void setHeadInfoId(Long headInfoId) {
        this.headInfoId = headInfoId;
    }

    @Generated(hash = 1313982279)
    public HeadInfoResbean(Long headInfoId, String date, String mujuBianHao,
            String xiangmuBianHao, String liuDaoLeiXing, String jiQiBianHao,
            String jiQiDunWei, String luoGanZhiJing, String sheZuiZhiJing,
            String chanPinMingCheng, String chanPinBianHao, String keHuLiaoHao,
            String chanPinBanBen, String yuanLiaoMingCheng, String yuanLiaoLiaoHao,
            String yuanLiaoGongYingShang, String RLDJRDS, String ganZaoWenDu,
            String ganZaoShiJian, String yuanLiaoHanShuiFen, String chengXingWenDu,
            String muJuWenDu, String sheChuYaLi, String sheChuShiJian,
            String sheChuSuDu, String baoYaYaLi, String baoYaShiJian,
            String suoMuLi, String kaiHeMuShiJian, String lengQueShiJian,
            String luoGanSuDu, String dingTuiChuShiJian, String beiYa) {
        this.headInfoId = headInfoId;
        this.date = date;
        this.mujuBianHao = mujuBianHao;
        this.xiangmuBianHao = xiangmuBianHao;
        this.liuDaoLeiXing = liuDaoLeiXing;
        this.jiQiBianHao = jiQiBianHao;
        this.jiQiDunWei = jiQiDunWei;
        this.luoGanZhiJing = luoGanZhiJing;
        this.sheZuiZhiJing = sheZuiZhiJing;
        this.chanPinMingCheng = chanPinMingCheng;
        this.chanPinBianHao = chanPinBianHao;
        this.keHuLiaoHao = keHuLiaoHao;
        this.chanPinBanBen = chanPinBanBen;
        this.yuanLiaoMingCheng = yuanLiaoMingCheng;
        this.yuanLiaoLiaoHao = yuanLiaoLiaoHao;
        this.yuanLiaoGongYingShang = yuanLiaoGongYingShang;
        this.RLDJRDS = RLDJRDS;
        this.ganZaoWenDu = ganZaoWenDu;
        this.ganZaoShiJian = ganZaoShiJian;
        this.yuanLiaoHanShuiFen = yuanLiaoHanShuiFen;
        this.chengXingWenDu = chengXingWenDu;
        this.muJuWenDu = muJuWenDu;
        this.sheChuYaLi = sheChuYaLi;
        this.sheChuShiJian = sheChuShiJian;
        this.sheChuSuDu = sheChuSuDu;
        this.baoYaYaLi = baoYaYaLi;
        this.baoYaShiJian = baoYaShiJian;
        this.suoMuLi = suoMuLi;
        this.kaiHeMuShiJian = kaiHeMuShiJian;
        this.lengQueShiJian = lengQueShiJian;
        this.luoGanSuDu = luoGanSuDu;
        this.dingTuiChuShiJian = dingTuiChuShiJian;
        this.beiYa = beiYa;
    }

    @Generated(hash = 280818002)
    public HeadInfoResbean() {
    }



}
