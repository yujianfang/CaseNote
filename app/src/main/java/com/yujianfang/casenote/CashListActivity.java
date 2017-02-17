/******************************************************************************
 * Copyright (c) 2012,山东山大华天软件有限公司
 * All rights reserved.
 * <p>
 * 文件名称： CashListActivity.java
 * <p>
 * 创 建 人： 俞建方
 * 创建日期：  2017/2/17
 * 初始版本： 1.0
 ******************************************************************************/
package com.yujianfang.casenote;

import android.app.Fragment;
import android.content.Intent;

import java.util.UUID;

/**
 * Created by yujianfang on 2017/2/17.
 */
public class CashListActivity extends BaseActivity implements CashListFragment.Callback{
    @Override
    public Fragment createFragment() {
        return new CashListFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.base_fragment_layout;
    }

    @Override
    public void onCashItemSelect(UUID uuid) {
        Intent intent = new Intent(this,CashActivity.class);
        intent.putExtra(CaseNoteFragment.CASH_ID,uuid);
        startActivity(intent);
    }
}
