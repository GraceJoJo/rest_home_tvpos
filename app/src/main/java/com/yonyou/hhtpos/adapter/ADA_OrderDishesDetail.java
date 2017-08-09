package com.yonyou.hhtpos.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.yonyou.framework.library.adapter.lv.CommonAdapterListView;
import com.yonyou.framework.library.adapter.lv.ViewHolderListView;
import com.yonyou.framework.library.common.utils.AppDateUtil;
import com.yonyou.framework.library.common.utils.StringUtil;
import com.yonyou.hhtpos.R;
import com.yonyou.hhtpos.bean.wd.WDDishDetaiListlEntity;

/**
 * Created by zj on 2017/7/5.
 * 邮箱：zjuan@yonyou.com
 * 描述：外带-分组列表-点菜明细的适配器
 */
public class ADA_OrderDishesDetail extends CommonAdapterListView<WDDishDetaiListlEntity> {
    public ADA_OrderDishesDetail(Context context) {
        super(context);
    }

    @Override
    protected int itemLayoutId() {
        return R.layout.item_order_dishes;
    }

    @Override
    protected void convert(ViewHolderListView holder, WDDishDetaiListlEntity dishDetaiListlEntity, int position) {
        ImageView ivDishSign = holder.getView(R.id.iv_dish_status_sign);
        if (dishDetaiListlEntity != null) {
            if (dishDetaiListlEntity.orderTime != null) {
                if (dishDetaiListlEntity.orderTime != 0) {
                    holder.setText(R.id.tv_header_time, String.valueOf(AppDateUtil.getTimeStamp(dishDetaiListlEntity.orderTime, AppDateUtil.HH_MM)) + mContext.getResources().getString(R.string.string_order_dish));
                } else {
                    holder.setText(R.id.tv_header_time, mContext.getResources().getString(R.string.string_unordered_dishes));
                }
            }
            //设置份数
            holder.setText(R.id.tv_quanlity, dishDetaiListlEntity.quantity + mContext.getResources().getString(R.string.string_unit_quanlity));
            holder.setText(R.id.tv_dish_name, dishDetaiListlEntity.dishName);
            //设置原价
            holder.setText(R.id.tv_dish_price, mContext.getResources().getString(R.string.RMB_symbol) + dishDetaiListlEntity.getDishPrice());
            //设置会员价
            if (!TextUtils.isEmpty(dishDetaiListlEntity.getMemberPrice())) {
                holder.setVisible(R.id.tv_member_price, true);
                holder.setText(R.id.tv_member_price, mContext.getResources().getString(R.string.RMB_symbol) + dishDetaiListlEntity.getMemberPrice());
            } else {
                holder.setVisible(R.id.tv_member_price, false);
            }
            //设置规格
            holder.setText(R.id.tv_standard_name, dishDetaiListlEntity.standardName);
            //设置退赠表标记:菜品状态:赠菜=gift，退菜=retreat，等叫：waitCall
            if (dishDetaiListlEntity.dishAbnormalStatus != null) {
                ivDishSign.setVisibility(View.VISIBLE);
                if (dishDetaiListlEntity.dishAbnormalStatus.equals("gift")) {
                    holder.setImageResource(R.id.iv_dish_status_sign, R.drawable.ic_dish_gift);
                } else if (dishDetaiListlEntity.dishAbnormalStatus.equals("retreat")) {
                    ivDishSign.setImageResource(R.drawable.ic_dish_back);
                }
            } else {
                ivDishSign.setVisibility(View.GONE);
            }
        }
        if (dishDetaiListlEntity.orderTime != null) {
            //根据菜品的提交订单时间，分组显示列表
            if (position == getFirstVisiblePosition(StringUtil.getString(dishDetaiListlEntity.orderTime))) {
                holder.setVisible(R.id.tv_header_time, true);
            } else {
                holder.setVisible(R.id.tv_header_time, false);
            }
        }
    }

    /**
     * 根据订单统一提交的时间，找到分组中每组应显示标题的第一个position
     *
     * @param orderTime
     */
    private int getFirstVisiblePosition(String orderTime) {
        for (int i = 0; i < mDatas.size(); i++) {
            WDDishDetaiListlEntity dishDetaiListlEntity = mDatas.get(i);
            if (dishDetaiListlEntity.orderTime != null) {
                String order_time = StringUtil.getString(dishDetaiListlEntity.orderTime);
                if (orderTime.equals(order_time)) {
                    return i;
                }
            }
        }
        return -1;
    }
}
