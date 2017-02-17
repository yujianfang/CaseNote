/******************************************************************************
 * Copyright (c) 2012,山东山大华天软件有限公司
 * All rights reserved.
 * <p>
 * 文件名称： CashActivity.java
 * <p>
 * 创 建 人： 俞建方
 * 创建日期：  2017/2/17
 * 初始版本： 1.0
 ******************************************************************************/
package com.yujianfang.casenote;

import android.app.Fragment;

import java.util.UUID;

/**
 * Created by yujianfang on 2017/2/17.
 */
public class CashActivity extends BaseActivity{
    @Override
    public Fragment createFragment() {
        UUID uuid = (UUID)getIntent().getSerializableExtra(CaseNoteFragment.CASH_ID);
        Fragment fragment = CaseNoteFragment.newInstance(uuid);
        return fragment;
    }
}
