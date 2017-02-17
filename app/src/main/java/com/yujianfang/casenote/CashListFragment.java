/******************************************************************************
 * Copyright (c) 2012,山东山大华天软件有限公司
 * All rights reserved.
 * <p>
 * 文件名称： CashListFragment.java
 * <p>
 * 创 建 人： 俞建方
 * 创建日期：  2017/2/16
 * 初始版本： 1.0
 ******************************************************************************/
package com.yujianfang.casenote;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.UUID;

/**
 * Created by yujianfang on 2017/2/16.
 */
public class CashListFragment extends Fragment{
    interface Callback{
        void onCashItemSelect(UUID uuid);
    }
    private Callback callback;
    private CashListAdapter mCashListAdapter;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (Callback)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.cash_list_fragment_layout,null);

        initData();
        initView(view);
        return view;
    }

    private void initData() {
        CashLab.get(getActivity()).getCrimes();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

            super.onCreateOptionsMenu(menu, inflater);
            inflater.inflate(R.menu.fragment_cash_menu_list, menu);
            MenuItem item = menu.findItem(R.id.menu_item_new_cash);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_item_new_cash){
            CaseBean caseBean = new CaseBean();
            CashLab.get(getActivity()).addCash(caseBean);
            Intent intent = new Intent(getActivity(),CashActivity.class);
            intent.putExtra(CaseNoteFragment.CASH_ID,caseBean.getmUUid());
            getActivity().startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void initView(View view) {
       ListView listView = (ListView)view.findViewById(R.id.cash_list_view);
        mCashListAdapter = new CashListAdapter(getActivity());
        listView.setAdapter(mCashListAdapter);
    }

}
