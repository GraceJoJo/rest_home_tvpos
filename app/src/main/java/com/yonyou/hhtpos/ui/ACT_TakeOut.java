package com.yonyou.hhtpos.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.yonyou.framework.library.bean.ErrorBean;
import com.yonyou.framework.library.eventbus.EventCenter;
import com.yonyou.framework.library.netstatus.NetUtils;
import com.yonyou.hhtpos.base.ACT_BaseMultiple;
import com.yonyou.hhtpos.ui.book.FRA_BookDetail;

/**
 * 外带页面
 * 作者：liushuofei on 2017/7/4 16:41
 */
public class ACT_TakeOut extends ACT_BaseMultiple {

    private FRA_TakeOutLeft mTakeOutLeft;

    @Override
    protected void initView() {
        mTakeOutLeft = new FRA_TakeOutLeft();
    }

    @Override
    protected float getLeftWeight() {
        return 0.65f;
    }

    @Override
    protected Fragment getLeftContent() {
        return mTakeOutLeft;
    }

    @Override
    protected Fragment getRightContent() {
        return new FRA_BookDetail();
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
}