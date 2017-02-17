/******************************************************************************
 * Copyright (c) 2012,山东山大华天软件有限公司
 * All rights reserved.
 * <p>
 * 文件名称： BaseActivity.java
 * <p>
 * 创 建 人： 俞建方
 * 创建日期：  2017/2/15
 * 初始版本： 1.0
 ******************************************************************************/
package com.yujianfang.casenote;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

/**
 * Created by yujianfang on 2017/2/15.
 */
public abstract class BaseActivity extends Activity{

    public abstract Fragment createFragment();
    protected int getLayoutId(){
        return R.layout.base_fragment_layout;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        FragmentManager fragmentManager = getFragmentManager();

        Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentLayoutContainer);

        if(fragment == null) {
            fragment = createFragment();
            fragmentManager.beginTransaction().add(R.id.fragmentLayoutContainer,fragment).commit();
        }
    }
}
