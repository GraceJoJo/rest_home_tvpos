package com.yonyou.hhtpos.bean.dish;

import com.yonyou.framework.library.common.utils.StringUtil;

/**
 * Created by zj on 2017/7/19.
 * 邮箱：zjuan@yonyou.com
 * 描述：新加菜品请求参数实体类-（非套餐和临时菜）
 */
public class RequestAddDishEntity {
    /**
     * 公司id
     */
    public String companyId;
    /**
     * 菜品分类id
     */
    public String dishClassid;
    /**
     * id
     */
    public String id;
    /**
     * 菜品名称
     */
    public String dishName;
    /**
     * 菜品价格
     */
    private String dishPrice;
    /**
     * 菜品关联id
     */
    public String dishRelateId;
    /**
     * dishId
     */
    public String dishId;
    /**
     * 菜品状态：等叫：7，即起：8，催菜：6，确认上菜：5
     */
    public String dishStatus;
    /**
     * 菜品类型：	菜品：1，固定套餐：2，N选N套餐：3，临时菜：4
     */
    public String dishType;
    /**
     * 列表展示做法：把所有已选做法名连接到一起的字符串，逗号分隔
     */
    public String listShowPractice;
    /**
     * 列表展示备注：把所有已选和手填的备注名连接到一起的字符串，逗号分隔
     */
    public String listShowRemark;

    /**
     * 所选做法id列表
     */
    public String practices;

    /**
     * 数量
     */
    public String quantity;
    /**
     * 备注
     */
    public String remark;

    /**
     * 所选备注id列表
     */
    public String remarks;
    /**
     * shopId
     */
    public String shopId;
    /**
     * 规格id
     */
    public String standardId;
    /**
     * 账单id
     */
    public String tableBillId;
    /**
     * 是否称重
     */
    public String isWeighDish;
    /**
     * 单位：0 非称重菜 1称重单位为斤
     */
    public String unit;
    /**
     * 服务员id
     */
    public String waiterId;
    /**
     * 销售模式：1：堂食 2：外带 3：外卖
     */
    public String saleManner;
    /**
     * 账单：开单1，下单2，结账3，退款4
     */
    public String orderState;
    /**
     * 是否折扣
     */
    public String isDiscount;

    public String getDishPrice() {
        return StringUtil.getFormattedMoney(dishPrice);
    }

    public void setDishPrice(String dishPrice) {
        this.dishPrice = dishPrice;
    }

}
