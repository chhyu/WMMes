package com.weimi.wmmess.business.shimu.presenter;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.weimi.wmmess.base.BasePresenter;
import com.weimi.wmmess.base.interfaces.IBaseView;
import com.weimi.wmmess.business.shimu.bean.step11.Step11Resbean;

public class Step11Presenter extends BasePresenter {
    public Step11Presenter(IBaseView view) {
        super(view);
    }

    /**
     * 检查数据是否合法
     *
     * @param step11Resbean
     * @return
     */
    public boolean checkDataIsLegal(Step11Resbean step11Resbean) {
        if (StringUtils.isEmpty(step11Resbean.getHan1())) {
            ToastUtils.showShort("请输入第一模含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getHan2())) {
            ToastUtils.showShort("请输入第二模含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getHan3())) {
            ToastUtils.showShort("请输入第三模含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getHan4())) {
            ToastUtils.showShort("请输入第四模含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getHan5())) {
            ToastUtils.showShort("请输入第五模含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getHan6())) {
            ToastUtils.showShort("请输入第六模含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getHan7())) {
            ToastUtils.showShort("请输入第七模含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getHan8())) {
            ToastUtils.showShort("请输入第八模含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getHan9())) {
            ToastUtils.showShort("请输入第九模含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getHan10())) {
            ToastUtils.showShort("请输入第十模含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getHan11())) {
            ToastUtils.showShort("请输入第十一模含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getHan12())) {
            ToastUtils.showShort("请输入第十二模含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getHan13())) {
            ToastUtils.showShort("请输入第十三模含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getHan14())) {
            ToastUtils.showShort("请输入第十四模含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getHan15())) {
            ToastUtils.showShort("请输入第十五模含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getHan16())) {
            ToastUtils.showShort("请输入第十六模含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getHan17())) {
            ToastUtils.showShort("请输入第十七模含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getHan18())) {
            ToastUtils.showShort("请输入第十八模含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getHan19())) {
            ToastUtils.showShort("请输入第十九模含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getHan20())) {
            ToastUtils.showShort("请输入第二十模含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getHan21())) {
            ToastUtils.showShort("请输入第二十一模含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getHan22())) {
            ToastUtils.showShort("请输入第二十二模含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getHan23())) {
            ToastUtils.showShort("请输入第二十三模含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getHan24())) {
            ToastUtils.showShort("请输入第二十四模含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getHan25())) {
            ToastUtils.showShort("请输入第二十五模含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getHan26())) {
            ToastUtils.showShort("请输入第二十六模含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getHan27())) {
            ToastUtils.showShort("请输入第二十七模含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getHan28())) {
            ToastUtils.showShort("请输入第二十八模含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getHan29())) {
            ToastUtils.showShort("请输入第二十九模含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getHan30())) {
            ToastUtils.showShort("请输入第三十模含料头一模质量");
            return false;
        }


        if (StringUtils.isEmpty(step11Resbean.getBuHan1())) {
            ToastUtils.showShort("请输入第一模不含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getBuHan2())) {
            ToastUtils.showShort("请输入第二模不含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getBuHan3())) {
            ToastUtils.showShort("请输入第三模不含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getBuHan4())) {
            ToastUtils.showShort("请输入第四模不含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getBuHan5())) {
            ToastUtils.showShort("请输入第五模不含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getBuHan6())) {
            ToastUtils.showShort("请输入第六模不含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getBuHan7())) {
            ToastUtils.showShort("请输入第七模不含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getBuHan8())) {
            ToastUtils.showShort("请输入第八模不含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getBuHan9())) {
            ToastUtils.showShort("请输入第九模不含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getBuHan10())) {
            ToastUtils.showShort("请输入第十模不含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getBuHan11())) {
            ToastUtils.showShort("请输入第十一模不含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getBuHan12())) {
            ToastUtils.showShort("请输入第十二模不含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getBuHan13())) {
            ToastUtils.showShort("请输入第十三模不含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getBuHan14())) {
            ToastUtils.showShort("请输入第十四模不含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getBuHan15())) {
            ToastUtils.showShort("请输入第十五模不含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getBuHan16())) {
            ToastUtils.showShort("请输入第十六模不含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getBuHan17())) {
            ToastUtils.showShort("请输入第十七模不含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getBuHan18())) {
            ToastUtils.showShort("请输入第十八模不含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getBuHan19())) {
            ToastUtils.showShort("请输入第十九模不含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getBuHan20())) {
            ToastUtils.showShort("请输入第二十模不含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getBuHan21())) {
            ToastUtils.showShort("请输入第二十一模不含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getBuHan22())) {
            ToastUtils.showShort("请输入第二十二模不含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getBuHan23())) {
            ToastUtils.showShort("请输入第二十三模不含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getBuHan24())) {
            ToastUtils.showShort("请输入第二十四模不含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getBuHan25())) {
            ToastUtils.showShort("请输入第二十五模不含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getBuHan26())) {
            ToastUtils.showShort("请输入第二十六模不含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getBuHan27())) {
            ToastUtils.showShort("请输入第二十七模不含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getBuHan28())) {
            ToastUtils.showShort("请输入第二十八模不含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getBuHan29())) {
            ToastUtils.showShort("请输入第二十九模不含料头一模质量");
            return false;
        }
        if (StringUtils.isEmpty(step11Resbean.getBuHan30())) {
            ToastUtils.showShort("请输入第三十模不含料头一模质量");
            return false;
        }

        if (step11Resbean.getOkOrNG()==0){
            ToastUtils.showShort("请确认是否可以全自动生产");
            return false;
        }
        return true;
    }
}
