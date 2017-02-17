/******************************************************************************
 * Copyright (c) 2012,山东山大华天软件有限公司
 * All rights reserved.
 * <p>
 * 文件名称： CashListAdapter.java
 * <p>
 * 创 建 人： 俞建方
 * 创建日期：  2017/2/16
 * 初始版本： 1.0
 ******************************************************************************/
package com.yujianfang.casenote;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by yujianfang on 2017/2/16.
 */
public class CashListAdapter extends BaseAdapter{
    private List<CaseBean> mCaseBeans;
    private Context mContext;
    private CashListFragment.Callback mCallback;
    public  CashListAdapter(Context context){
        mCaseBeans =CashLab.get(context).getCrimes();
        mCallback = (CashListFragment.Callback) context;
    }
    @Override
    public int getCount() {
        return mCaseBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return mCaseBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        CaseBean caseBean = mCaseBeans.get(position);
        if(view == null){
            view = (View)LayoutInflater.from(mContext).inflate(R.layout.cash_list_item_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.mDateTimeTextView = (TextView)view.findViewById(R.id.datetime);
            viewHolder.mCashTypeTextView = (TextView)view.findViewById(R.id.cash_type_item);
            viewHolder.mCashCountTextView = (TextView)view.findViewById(R.id.cash_count_item);
            viewHolder.mCashDetailTextView = (ImageView) view.findViewById(R.id.detail_info);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)view.getTag();
        }
        setViewData(viewHolder,caseBean);
        return null;
    }

    private void setViewData(ViewHolder viewHolder, final CaseBean caseBean) {
        //时间
        Date date = caseBean.getmDate();
        if(date!=null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String time = sdf.format(date);
            viewHolder.mDateTimeTextView.setText(time);
        }
        //收支类型
        if(caseBean.getmCaseType()){
            viewHolder.mCashTypeTextView.setText(mContext.getResources().getText(R.string.income));
        }else{
            viewHolder.mCashTypeTextView.setText(mContext.getResources().getText(R.string.payout));
        }
        //收支金额
        viewHolder.mCashCountTextView.setText(String.valueOf(caseBean.getmNumber()));
        viewHolder.mCashDetailTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UUID u = caseBean.getmUUid();
                mCallback.onCashItemSelect(u);
            }
        });

    }

    class ViewHolder{
        TextView mDateTimeTextView;
        TextView mCashTypeTextView;
        TextView mCashCountTextView;
        ImageView mCashDetailTextView;
    }
}
