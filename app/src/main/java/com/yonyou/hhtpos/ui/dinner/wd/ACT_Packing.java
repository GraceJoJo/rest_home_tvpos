package com.yonyou.hhtpos.ui.dinner.wd;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.yonyou.framework.library.bean.ErrorBean;
import com.yonyou.framework.library.eventbus.EventCenter;
import com.yonyou.framework.library.netstatus.NetUtils;
import com.yonyou.hhtpos.base.ACT_BaseMultiple;

/**
 * 外带页面
 * 作者：liushuofei on 2017/7/4 16:41
 */
public class ACT_Packing extends ACT_BaseMultiple implements View.OnClickListener {

    private FRA_PackingLeft mPackingLeft;
    private FRA_PackingDetail mPackingDetail;

    @Override
    protected void initView() {
        mPackingLeft= new FRA_PackingLeft();
        mPackingDetail = new FRA_PackingDetail();
    }

    @Override
    protected float getLeftWeight() {
        return 0.65f;
    }

    @Override
    protected Fragment getLeftContent() {
        return mPackingLeft;
    }

    @Override
    protected Fragment getRightContent() {
        return mPackingDetail;
    }

    @Override
    protected boolean isApplyKitKatTranslucency() {
        return false;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return false;
    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return true;
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return TransitionMode.RIGHT;
    }

    @Override
    public void showBusinessError(ErrorBean error) {

    }

    public void requestPackingDetail(String tableBillId) {
        mPackingDetail.requestPackingDetail(tableBillId);
    }

}
