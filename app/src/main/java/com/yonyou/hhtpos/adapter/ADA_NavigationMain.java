package com.yonyou.hhtpos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.yonyou.framework.library.base.BaseAbsAdapter;
import com.yonyou.hhtpos.R;

/**
 * 左导航栏一级菜单adapter
 * 作者：liushuofei on 2017/6/27 15:54
 */
public class ADA_NavigationMain extends BaseAbsAdapter<String> {

    private Context context;
    private OnCheckChangedListener onCheckChangedListener;
    private RadioButton mCurrentSelected;

    public ADA_NavigationMain(Context context, OnCheckChangedListener onCheckChangedListener) {
        super(context);

        this.context = context;
        this.onCheckChangedListener = onCheckChangedListener;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_navigation_main, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        handleDataSource(position, holder);
        return convertView;
    }

    private void handleDataSource(int position, final ViewHolder holder) {
        String category = "";
        switch (position) {
            case 0:
                category = "堂食";
                break;

            case 1:
                category = "外卖";
                break;

            case 2:
                category = "预订";
                break;

            case 3:
                category = "会员管理";
                break;

            case 4:
                category = "基础设置";
                break;

            case 5:
                category = "营业概况";
                break;

            default:
                break;
        }

        holder.mTxt.setText(category);

        holder.mTxt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (null != onCheckChangedListener){
                    onCheckChangedListener.onChange();
                }

                if (isChecked){
                    if (null != mCurrentSelected){
                        mCurrentSelected.setChecked(false);
                    }

                    mCurrentSelected = holder.mTxt;
                }
            }
        });
    }

    static class ViewHolder {
        RadioButton mTxt;

        ViewHolder(View v) {
            mTxt = (RadioButton) v.findViewById(R.id.rb_category);
        }
    }

    public interface OnCheckChangedListener {
        void onChange();
    }
}
