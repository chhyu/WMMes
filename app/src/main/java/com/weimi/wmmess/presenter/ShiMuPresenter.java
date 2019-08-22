package com.weimi.wmmess.presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.base.BaseObserver;
import com.weimi.wmmess.base.CommonPresenter;
import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.shimu.api.ShiMuServive;
import com.weimi.wmmess.business.shimu.bean.HeadInfoResbean;
import com.weimi.wmmess.business.shimu.bean.craftParams.CraftConfirmResbean;
import com.weimi.wmmess.business.shimu.bean.craftParams.ModelInfoResbean;
import com.weimi.wmmess.business.shimu.bean.history.HistoryResbean;
import com.weimi.wmmess.business.shimu.entity.CraftParamsName;
import com.weimi.wmmess.business.shimu.params.MobileTrialModeParam;
import com.weimi.wmmess.business.shimu.params.MobileTrialModeSaveParam;
import com.weimi.wmmess.business.shimu.viewInterface.ICraftConfirmView;
import com.weimi.wmmess.business.shimu.viewInterface.IModelView;
import com.weimi.wmmess.business.shimu.viewInterface.IShiMuHistoryView;
import com.weimi.wmmess.business.workOrder.params.MobileWorkOrderParam;
import com.weimi.wmmess.business.workOrder.params.WorkOrderVO;
import com.weimi.wmmess.constants.HostAddress;
import com.weimi.wmmess.http.HttpClient;
import com.weimi.wmmess.model.ListModel;
import com.weimi.wmmess.model.ResultModel;
import com.weimi.wmmess.params.GeneralParam;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Create by chhyu
 * on 2019/6/28
 * Describle: 试模presenter
 */
public class ShiMuPresenter extends CommonPresenter {

    private ShiMuServive service;

    public ShiMuPresenter(IBaseView view) {
        super(view);
        service = HttpClient
                .builder()
                .baseUrl(HostAddress.HOST_API)
                .build()
                .getRetrofit()
                .create(ShiMuServive.class);
    }

    /**
     * 检查头部信息是否有空数据（模具、机台信息从模具或BOM中获取，暂时不加判空）
     *
     * @param headInfoResbean
     * @return
     */
    public boolean checkHeadInfoIsHaveNull(HeadInfoResbean headInfoResbean) {
        if (isNull(headInfoResbean.getGanZaoWenDu(), "请输入干燥温度")) {
            return true;
        }
        if (isNull(headInfoResbean.getGanZaoShiJian(), "请输入干燥时间")) {
            return true;
        }
        if (isNull(headInfoResbean.getYuanLiaoHanShuiFen(), "请输入原料含水分")) {
            return true;
        }
        if (isNull(headInfoResbean.getChengXingWenDu(), "请输入成型温度")) {
            return true;
        }

        if (isNull(headInfoResbean.getMuJuWenDu(), "请输入模具温度")) {
            return true;
        }
        if (isNull(headInfoResbean.getSheChuYaLi(), "请输入射出压力")) {
            return true;
        }
        if (isNull(headInfoResbean.getSheChuShiJian(), "请输入射出时间")) {
            return true;
        }
        if (isNull(headInfoResbean.getSheChuSuDu(), "请输入射出速度")) {
            return true;
        }

        if (isNull(headInfoResbean.getBaoYaYaLi(), "请输入保压压力")) {
            return true;
        }
        if (isNull(headInfoResbean.getBaoYaShiJian(), "请输入保压时间")) {
            return true;
        }
        if (isNull(headInfoResbean.getSuoMuLi(), "请输入锁模力")) {
            return true;
        }
        if (isNull(headInfoResbean.getKaiHeMuShiJian(), "请输入开合模时间")) {
            return true;
        }

        if (isNull(headInfoResbean.getLengQueShiJian(), "请输入冷却时间")) {
            return true;
        }
        if (isNull(headInfoResbean.getLuoGanSuDu(), "请输入螺杆速度")) {
            return true;
        }
        if (isNull(headInfoResbean.getDingTuiChuShiJian(), "请输入顶退出时间")) {
            return true;
        }
        if (isNull(headInfoResbean.getBeiYa(), "请输入背压")) {
            return true;
        }
        return false;
    }

    /**
     * 是否为null
     *
     * @param data   要判断的数据
     * @param remind 提示语
     * @return 是否为null
     */
    private boolean isNull(String data, String remind) {
        if (StringUtils.isEmpty(data)) {
            ToastUtils.showShort(remind);
            return true;
        }
        return false;
    }

    /**
     * 获取试模历史记录列表
     *
     * @param size
     * @param current
     * @param workOrderId
     * @param procedureId
     */
    public void requestShiMuHistory(int size, int current, String workOrderId, String procedureId) {
        MobileWorkOrderParam param = new MobileWorkOrderParam();
        GeneralParam generalParam = new GeneralParam();
        generalParam.setSize(size);
        generalParam.setCurrent(current);
        WorkOrderVO workOrderVO = new WorkOrderVO();
        workOrderVO.setWorkOrderId(workOrderId);
        workOrderVO.setProcedureId(procedureId);
        param.setWorkOrderVO(workOrderVO);
        param.setParam(generalParam);
        service.getShiMuHistoryRecord(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new BaseObserver<ResultModel<ListModel<HistoryResbean>>>() {
                    @Override
                    public void onSuccess(ResultModel<ListModel<HistoryResbean>> result) {
                        Log.e("lmsg", "result==" + JSON.toJSONString(result));
                        List<HistoryResbean> records = result.getData().getRecords();
                        ((IShiMuHistoryView) mBaseView).onLoadShiMuRecodeSuccess(records);
                    }
                });
    }

    public void getModelInfoById(String workOrderId, String procedureId) {
        MobileTrialModeSaveParam param = new MobileTrialModeSaveParam();
        param.setWorkOrderId(workOrderId);
        param.setProcedureId(procedureId);

        service.getModelInfoById(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new BaseObserver<ResultModel<ModelInfoResbean>>() {
                    @Override
                    public void onSuccess(ResultModel<ModelInfoResbean> result) {
                        Log.e("lmsg", "Object==" + JSON.toJSONString(result));
                        ((IModelView) mBaseView).getModelInfoSuccess(result.getData());
                    }
                });
    }

    /**
     * 根据工单ID和工序ID获取试模工艺参数
     */
    public void getTrialParameterInfoById(String craftPaeameterId) {
        service.getTrialParameterInfoById(craftPaeameterId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new BaseObserver<ResultModel<List<CraftConfirmResbean>>>() {
                    @Override
                    public void onSuccess(ResultModel<List<CraftConfirmResbean>> result) {
//                        Log.e("lmsg", "getTrialParameterInfoById==" + JSON.toJSONString(result));
                        List<CraftConfirmResbean> data = result.getData();
                        ((ICraftConfirmView) mBaseView).onLoadCraftParamsSuccess(data);
                    }
                });
    }

    /**
     * 保存工艺参数（工艺确定界面）
     *
     * @param list
     * @param orderId
     * @param procedureId
     */
    public void saveCraftParams2Service(List<MobileTrialModeParam> list, String orderId, String procedureId) {
        MobileTrialModeSaveParam paramParams = new MobileTrialModeSaveParam();
        paramParams.setTrialModeParams(list);
        paramParams.setProcedureId(procedureId);
        paramParams.setWorkOrderId(orderId);
        service.saveCraftParams2Service(paramParams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//多个subscribeOn只有第一个起作用
                .doOnSubscribe(disposable -> mBaseView.showProgress())
                .doFinally(() -> mBaseView.hideProgress())
                .compose(getLifecycleProvider().bindToLifecycle())
                .compose(getLifecycleProvider().bindToLifecycle())
                .subscribe(new BaseObserver<ResultModel<Object>>() {
                    @Override
                    public void onSuccess(ResultModel<Object> result) {
                        Log.e("lmsg", "result==" + JSON.toJSONString(result));
                        ((ICraftConfirmView) mBaseView).onSaveCraftParamsSuccess();
                    }
                });
    }

    /**
     * 检查数据是否有null
     *
     * @param mobileTrialModeParams
     * @return
     */
    public boolean checkDataIsHaveNull(List<MobileTrialModeParam> mobileTrialModeParams) {
        if (mobileTrialModeParams == null || mobileTrialModeParams.size() <= 0) {
            ToastUtils.showShort("请输入所需数据");
            return true;
        }
        for (int i = 0; i < mobileTrialModeParams.size(); i++) {
            MobileTrialModeParam mobileTrialModeParam = mobileTrialModeParams.get(i);
            String name = mobileTrialModeParam.getParameterName();
            if (name.equals(CraftParamsName.GANZAOWENDU) || name.equals(CraftParamsName.GANZAOSHIJIAN) || name.equals(CraftParamsName.YUANLIAOHANSHUIFEN) || name.equals(CraftParamsName.CHENGXINGWENDU)
                    || name.equals(CraftParamsName.MUJUWENDU) || name.equals(CraftParamsName.SHECHUYALI) || name.equals(CraftParamsName.SHECHUSHIJIAN2)
                    || name.equals(CraftParamsName.SHECHUSUDU) || name.equals(CraftParamsName.BAOYAYALI) || name.equals(CraftParamsName.BAOYASHIJIAN)
                    || name.equals(CraftParamsName.SUOMOLI2) || name.equals(CraftParamsName.KAIHEMOSHIJIAN) || name.equals(CraftParamsName.LENGQUESHIJIAN2)
                    || name.equals(CraftParamsName.LUOGANZHUANSU2) || name.equals(CraftParamsName.DINGTUICHUSHIJIAN) || name.equals(CraftParamsName.BEIYA2)) {
                if (StringUtils.isEmpty(mobileTrialModeParam.getDefaultValue())) {
                    ToastUtils.showShort("请输入原料使用范围数据");
                    return true;
                }
            } else {
                if (StringUtils.isEmpty(mobileTrialModeParam.getDefaultValue())) {
                    ToastUtils.showShort("请输入" + mobileTrialModeParam.getParameterName() + "设定值");
                    return true;
                } else if (StringUtils.isEmpty(mobileTrialModeParam.getActualValue())) {
                    ToastUtils.showShort("请输入" + mobileTrialModeParam.getParameterName() + "实际值");
                    return true;
                } else if (StringUtils.isEmpty(mobileTrialModeParam.getLowerLimit())) {
                    ToastUtils.showShort("请输入" + mobileTrialModeParam.getParameterName() + "最小值");
                    return true;
                } else if (StringUtils.isEmpty(mobileTrialModeParam.getUpperLimit())) {
                    ToastUtils.showShort("请输入" + mobileTrialModeParam.getParameterName() + "最大值");
                    return true;
                } else if (StringUtils.isEmpty(mobileTrialModeParam.getCheckDate())) {
                    ToastUtils.showShort("请输入" + mobileTrialModeParam.getParameterName() + "检查日期");
                    return true;
                }
            }
        }
        return false;
    }
}
