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
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Checkable;

import java.util.List;

/**
 * Created by yujianfang on 2017/2/16.
 */
public class CashListAdapter extends BaseAdapter{
    private List<CaseBean> mCaseBeans;
    private Context mContext;
    public  CashListAdapter(Context context){
        mCaseBeans =CashLab.get(context).getCrimes();
    }
    @Override
    public int getCount() {
        return mCaseBeans.size();
    }

    @Override
    public Object getItem(int i) {
        return mCaseBeans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        return null;
    }
}
