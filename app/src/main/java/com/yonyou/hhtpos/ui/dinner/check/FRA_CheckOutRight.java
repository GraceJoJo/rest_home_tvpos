package com.yonyou.hhtpos.ui.dinner.check;

import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yonyou.framework.library.base.BaseFragment;
import com.yonyou.framework.library.bean.ErrorBean;
import com.yonyou.framework.library.eventbus.EventCenter;
import com.yonyou.hhtpos.R;
import com.yonyou.hhtpos.adapter.ADA_CheckOutPayType;
import com.yonyou.hhtpos.adapter.ADA_DiscountType;
import com.yonyou.hhtpos.bean.check.RequestPayEntity;
import com.yonyou.hhtpos.bean.check.SettleAccountDataEntity;
import com.yonyou.hhtpos.dialog.DIA_AutoDismiss;
import com.yonyou.hhtpos.dialog.DIA_CheckOutByCash;
import com.yonyou.hhtpos.global.API;
import com.yonyou.hhtpos.presenter.IQueryBillInfoPresenter;
import com.yonyou.hhtpos.presenter.Impl.QueryBillInfoPresenterImpl;
import com.yonyou.hhtpos.view.IQueryBillInfoView;

import java.util.ArrayList;

import butterknife.Bind;
import de.greenrobot.event.Subscribe;
import de.greenrobot.event.ThreadMode;

/**
 * 结账页面右侧fragment
 * 作者：liushuofei on 2017/7/19 14:49
 */
public class FRA_CheckOutRight extends BaseFragment implements IQueryBillInfoView {

    @Bind(R.id.layout_root)
    LinearLayout layoutRoot;
    @Bind(R.id.gv_discount_type)
    GridView mDiscountTypeGv;
    @Bind(R.id.gv_pay_type)
    GridView mPayTypeGv;
    @Bind(R.id.tv_dish_status)
    TextView tvDishStatus;//菜品状态
    @Bind(R.id.tv_discount_total)
    TextView tvDiscountTotal;//合计优惠
    @Bind(R.id.tv_discounmt_money)
    TextView tvDiscounmtMoney;//折扣金额
    @Bind(R.id.tv_ignore_money)
    TextView tvIgnoreMoney;//抹零金额
    @Bind(R.id.tv_paid_money)
    TextView tvPaidMoney;//已支付
    @Bind(R.id.tv_unpaid_money)
    TextView tvUnpaidMoney;//未支付

    private ADA_DiscountType mDiscountAdapter;
    private ADA_CheckOutPayType mPayTypeAdapter;
    private SettleAccountDataEntity dataBean;
    private String[] payTypeNames = {"现金", "免单", "零结", "会员余额", "聚合支付", "畅捷POS", "微信支付", "支付宝", "更多"};
    private DIA_CheckOutByCash mDiaCheckOutByCash;
    private IQueryBillInfoPresenter mPresenter;
    private String tableBillId;

    @Subscribe(threadMode = ThreadMode.MainThread)
    public void onRefreshRight(SettleAccountDataEntity settleAccountDataEntity) {
        this.dataBean = settleAccountDataEntity;
        setData();
    }

    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected View getLoadingTargetView() {
        return layoutRoot;
    }

    @Override
    protected void initViewsAndEvents() {
        mDiaCheckOutByCash = new DIA_CheckOutByCash(getActivity());
        mPresenter = new QueryBillInfoPresenterImpl(mContext, this);
        tableBillId = ((ACT_CheckOut) getActivity()).getTableBillId();
        mDiscountAdapter = new ADA_DiscountType(mContext);
        mPayTypeAdapter = new ADA_CheckOutPayType(mContext);
        mDiscountTypeGv.setAdapter(mDiscountAdapter);
        mPayTypeGv.setAdapter(mPayTypeAdapter);

        for (int i = 0; i < 3; i++) {
            mDiscountAdapter.update("");
        }
        ArrayList<String> payTypeList = new ArrayList<>();
        for (int i = 0; i < payTypeNames.length; i++) {
            payTypeList.add(payTypeNames[i]);
        }
        mPayTypeAdapter.update(payTypeList);
        mPayTypeGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    mDiaCheckOutByCash.show();
                }
            }
        });
        //收到现金
        mDiaCheckOutByCash.setOnReceiveMoneyListener(new DIA_CheckOutByCash.OnReceiveMoneyListener() {
            @Override
            public void onReceiveMoney(String money) {
                RequestPayEntity requestPayEntity = new RequestPayEntity();
                requestPayEntity.payMoney = money;
                requestPayEntity.payType = "现金";
                mPresenter.queryBillInfo(API.compId, API.shopId, tableBillId, true, requestPayEntity);
            }
        });
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fra_check_out_right;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    public void showBusinessError(ErrorBean error) {

    }

    /**
     * 刷新界面数据
     */
    private void setData() {
        //待支付
        if (!TextUtils.isEmpty(dataBean.getUnpaidMoney())) {
            tvUnpaidMoney.setText(mContext.getResources().getString(R.string.RMB_symbol) + dataBean.getUnpaidMoney());
        }
        //优惠合计
        if (!TextUtils.isEmpty(dataBean.getDiscountTotal())) {
            tvDiscountTotal.setText(mContext.getResources().getString(R.string.RMB_symbol) + dataBean.getDiscountTotal());
        }
        //折扣
        if (!TextUtils.isEmpty(dataBean.getDiscountMoney())) {
            tvDiscounmtMoney.setText(mContext.getResources().getString(R.string.RMB_symbol) + dataBean.getDiscountMoney());
        }
        //自动抹零
        if (!TextUtils.isEmpty(dataBean.getIgnoreMoney())) {
            tvIgnoreMoney.setText(mContext.getResources().getString(R.string.RMB_symbol) + dataBean.getIgnoreMoney());
        }
        //代金券
        //支付方式
        //发票
        //已支付
        if (!TextUtils.isEmpty(dataBean.getPaidMoney())) {
            tvPaidMoney.setText(mContext.getResources().getString(R.string.RMB_symbol) + dataBean.getPaidMoney());
        }
        if (!TextUtils.isEmpty(dataBean.getUnpaidMoney())) {
            mDiaCheckOutByCash.setMaxInputMoneyHint(dataBean.getUnpaidMoney());
        }

    }

    @Override
    public void queryBillInfo(SettleAccountDataEntity settleAccountDataEntity) {
        new DIA_AutoDismiss(mContext, getString(R.string.string_receive_money_successful)).show();
    }
}
