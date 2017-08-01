package com.yonyou.hhtpos.bean.dish;

import java.io.Serializable;

/**
 * 修改菜品实体类
 * 作者：liushuofei on 2017/8/1 14:27
 */
public class RequestEditDishEntity implements Serializable {
    /**菜品规格id */
    private String dishStandardId;

    /**菜品类型 */
    private String dishType;

    /**点餐菜品id */
    private String id;

    /**所选做法id列表 */
    private String practices;

    /**数量 */
    private String quantity;

    /**备注 */
    private String remark;

    /**所选备注id列表 */
    private String remarks;

    /**餐厅id */
    private String shopId;

    public String getDishStandardId() {
        return dishStandardId;
    }

    public void setDishStandardId(String dishStandardId) {
        this.dishStandardId = dishStandardId;
    }

    public String getDishType() {
        return dishType;
    }

    public void setDishType(String dishType) {
        this.dishType = dishType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPractices() {
        return practices;
    }

    public void setPractices(String practices) {
        this.practices = practices;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "RequestEditDishEntity{" +
                "dishStandardId='" + dishStandardId + '\'' +
                ", dishType='" + dishType + '\'' +
                ", id='" + id + '\'' +
                ", practices='" + practices + '\'' +
                ", quantity='" + quantity + '\'' +
                ", remark='" + remark + '\'' +
                ", remarks='" + remarks + '\'' +
                ", shopId='" + shopId + '\'' +
                '}';
    }
}
