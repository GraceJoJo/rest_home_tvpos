package com.yonyou.framework.library.loading;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yonyou.framework.library.R;
import com.yonyou.framework.library.common.CommonUtils;
import com.yonyou.framework.library.global.TypeLinkUtils;

/**
 * 作者：addison on 11/12/15 14:53
 * 邮箱：lsf@yonyou.com
 */
public class VaryViewHelperController {
    private IVaryViewHelper helper;

    public VaryViewHelperController(View view) {
        this(new VaryViewHelper(view));
    }

    public VaryViewHelperController(IVaryViewHelper helper) {
        super();
        this.helper = helper;
    }


//    /**
//     * 网络错误
//     */
//    public void showNetworkError(View.OnClickListener onClickListener) {
//        View layout = helper.inflate(R.layout.message);
//        TextView textView = (TextView) layout.findViewById(R.id.message_info);
//        textView.setText(helper.getContext().getResources().getString(R.string.common_no_network_msg));
//
//        ImageView imageView = (ImageView) layout.findViewById(R.id.message_icon);
//        imageView.setImageResource(R.drawable.ic_exception);
//
//        if (null != onClickListener) {
//            layout.setOnClickListener(onClickListener);
//        }
//
//        helper.showLayout(layout);
//    }

//    /**
//     *
//     * @param onClickListener
//     */
//    public void showNetworkError(View.OnClickListener onClickListener){
//        View layout = helper.inflate(R.layout.message);
//        TextView textView = (TextView) layout.findViewById(R.id.message_info);
//        textView.setText(helper.getContext().getResources().getString(R.string.common_no_network_msg));
//
//        ImageView imageView = (ImageView) layout.findViewById(R.id.message_icon);
//        imageView.setImageResource(R.drawable.ic_network_error);
//
//        TextView networkRefresh = (TextView)layout.findViewById(R.id.message_network_refresh);
//
//        if (null != onClickListener) {
//            networkRefresh.setVisibility(View.VISIBLE);
//            networkRefresh.setOnClickListener(onClickListener);
//        }
//
//        helper.showLayout(layout);
//    }

    /**
     * loading
     */
    public void showLoading(String msg) {
        View layout = helper.inflate(R.layout.loading);
        if (!CommonUtils.isEmpty(msg)) {
            TextView textView = (TextView) layout.findViewById(R.id.loading_msg);
            textView.setText(msg);
        }
        helper.showLayout(layout);
    }


    /**
     * 数据异常错误
     */
    public void showError(String errorMsg, View.OnClickListener onClickListener) {
        View layout = helper.inflate(R.layout.message);
        TextView textView = (TextView) layout.findViewById(R.id.message_info);
        if (!CommonUtils.isEmpty(errorMsg)) {
            textView.setText(errorMsg);
        } else {
            textView.setText(helper.getContext().getResources().getString(R.string.common_error_msg));
        }

        ImageView imageView = (ImageView) layout.findViewById(R.id.message_icon);
        imageView.setImageResource(R.drawable.ic_exception);

        if (null != onClickListener) {
            layout.setOnClickListener(onClickListener);
        }

        helper.showLayout(layout);
    }

    /**
     * 服务器错误
     */
    public void showServerError() {
        View layout = helper.inflate(R.layout.message_center);
        TextView tvServerError = (TextView) layout.findViewById(R.id.message_info);
        ImageView ivServerError = (ImageView) layout.findViewById(R.id.message_icon);

        tvServerError.setText(helper.getContext().getResources().getString(R.string.server_error));
        ivServerError.setImageResource(R.drawable.empty_no_response);

        helper.showLayout(layout);
    }


    /**
     * 可点击的无数据
     */
    public void showNetworkError(View.OnClickListener onClickListener) {
        View layout = helper.inflate(R.layout.message_center);
        TextView textView = (TextView) layout.findViewById(R.id.message_info);
        textView.setText(helper.getContext().getResources().getString(R.string.common_no_network_msg));

        ImageView imageView = (ImageView) layout.findViewById(R.id.message_icon);
        imageView.setImageResource(R.drawable.ic_network_error);

        TextView mRefreshTxt = (TextView) layout.findViewById(R.id.message_network_refresh);
        mRefreshTxt.setText(helper.getContext().getResources().getString(R.string.common_refresh_msg));

        if (null != onClickListener) {
            mRefreshTxt.setVisibility(View.VISIBLE);
            mRefreshTxt.setOnClickListener(onClickListener);
        }

        helper.showLayout(layout);
    }

    /**
     * 可点击的无数据
     */
    public void showEmpty(int emptyImg, String emptyMsg, String refreshTxt, View.OnClickListener onClickListener) {
        View layout = helper.inflate(R.layout.message_center);
        TextView textView = (TextView) layout.findViewById(R.id.message_info);
        if (!CommonUtils.isEmpty(emptyMsg)) {
            textView.setText(emptyMsg);
        } else {
            textView.setText(helper.getContext().getResources().getString(R.string.common_empty_msg));
        }

        ImageView imageView = (ImageView) layout.findViewById(R.id.message_icon);
        imageView.setImageResource(emptyImg);

        TextView mRefreshTxt = (TextView) layout.findViewById(R.id.message_network_refresh);
        if (!CommonUtils.isEmpty(refreshTxt)) {
            mRefreshTxt.setText(refreshTxt);
        } else {
            mRefreshTxt.setText("刷新");
        }
        if (null != onClickListener) {
            mRefreshTxt.setVisibility(View.VISIBLE);
            mRefreshTxt.setOnClickListener(onClickListener);
        }

        helper.showLayout(layout);
    }

//    /**
//     * 不可点击的无数据
//     * @param emptyImg
//     * @param emptyMsg
//     */
//    public void showEmpty(int emptyImg, String emptyMsg){
//        View layout = helper.inflate(R.layout.message);
//        TextView textView = (TextView) layout.findViewById(R.id.message_info);
//        if (!CommonUtils.isEmpty(emptyMsg)) {
//            textView.setText(emptyMsg);
//        } else {
//            textView.setText(helper.getContext().getResources().getString(R.string.common_empty_msg));
//        }
//
//        ImageView imageView = (ImageView) layout.findViewById(R.id.message_icon);
//        imageView.setImageResource(emptyImg);
//
//        helper.showLayout(layout);
//    }

    /**
     * 不可点击的无数据
     *
     * @param emptyImg
     * @param emptyMsg
     */
    public void showEmpty(int emptyImg, String emptyMsg) {
        View layout = helper.inflate(R.layout.empty_layout);
        ImageView imageView = (ImageView) layout.findViewById(R.id.iv_empty_img);
        TextView textView = (TextView) layout.findViewById(R.id.tv_empty_txt);

        // 图片
        imageView.setImageResource(emptyImg);
        // 文本
        if (!CommonUtils.isEmpty(emptyMsg)) {
            textView.setText(emptyMsg);
        }
        helper.showLayout(layout);
    }

    /**
     * 不可点击的无数据-新增样式一
     *
     * @param emptyImg
     * @param emptyMsg
     */
    public void showEmpty(int emptyImg, String emptyMsg, int bgColor, int textColor, String otherEmptyMsg) {
        View layout = helper.inflate(R.layout.empty_layout);
        LinearLayout llEmpty = (LinearLayout) layout.findViewById(R.id.ll_empty);
        ImageView imageView = (ImageView) layout.findViewById(R.id.iv_empty_img);
        TextView textView = (TextView) layout.findViewById(R.id.tv_empty_txt);
        TextView tvContactPhone = (TextView) layout.findViewById(R.id.tv_contact_phone);
        //文字颜色
        textView.setTextColor(textColor);
        //空页面背景
        llEmpty.setBackgroundColor(bgColor);
        // 图片
        imageView.setImageResource(emptyImg);
        // 文本
        if (!CommonUtils.isEmpty(emptyMsg)) {
            textView.setText(emptyMsg);
        }
        if (!TextUtils.isEmpty(otherEmptyMsg)) {
            tvContactPhone.setVisibility(View.VISIBLE);
            tvContactPhone.setText(otherEmptyMsg);
        }
        helper.showLayout(layout);
    }

    /**
     * 带超链接的空页面
     */
    public void showEmptyHyperLink(final Context context, final String linkUrl, String clickText) {
        View layout = helper.inflate(R.layout.empty_layout_with_url);
        LinearLayout llEmpty = (LinearLayout) layout.findViewById(R.id.ll_empty_root);
        ImageView imageView = (ImageView) layout.findViewById(R.id.iv_empty_img);
        ImageView iv_jump_html = (ImageView) layout.findViewById(R.id.iv_jump_html);
        TextView tv_hyper_link = (TextView) layout.findViewById(R.id.tv_hyper_link);

        String defaultClickText = context.getResources().getString(R.string.string_operation_paltform);
        final String mLink;
        if (!TextUtils.isEmpty(clickText)) {
            mLink = "<a href=" + "\"" + linkUrl + "\"" + ">" + clickText + "</a>";
        } else {
            mLink = "<a href=" + "\"" + linkUrl + "\"" + ">" + defaultClickText + "</a>";
        }

        //设置超链接

        //方法重新设置文字背景为透明色，否则点击时文字背景会有颜色
        tv_hyper_link.setHighlightColor(context.getResources().getColor(android.R.color.transparent));
        //设置超链接文字的样式
        tv_hyper_link.setText(TypeLinkUtils.getClickableHtml(tv_hyper_link.getText().toString() + mLink));
        //设置超链接可点击
        tv_hyper_link.setMovementMethod(LinkMovementMethod.getInstance());

        iv_jump_html.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Uri uri = Uri.parse(linkUrl);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    context.startActivity(intent);
                } catch (ActivityNotFoundException a) {
                    a.getMessage();
                    CommonUtils.makeEventToast(context, context.getResources().getString(R.string.string_install_brower), false);
                }
            }
        });

        helper.showLayout(layout);
    }

    /**
     * 显示公司信息
     */
    public void showCompanyInfo() {
        View layout = helper.inflate(R.layout.company_info);
        helper.showLayout(layout);
    }

    public void restore() {
        helper.restoreView();
    }


}
