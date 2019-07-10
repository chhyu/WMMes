package com.weimi.wmmess.business.workOrder.bean;

import java.util.List;

/**
 * Create by chhyu
 * on 2019/7/4
 * Describle:
 */
public class CraftRebean {

    private String productCode;
    private List<CraftParamsResbean> craftParamsResbeans;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public List<CraftParamsResbean> getCraftParamsResbeans() {
        return craftParamsResbeans;
    }

    public void setCraftParamsResbeans(List<CraftParamsResbean> craftParamsResbeans) {
        this.craftParamsResbeans = craftParamsResbeans;
    }
}
