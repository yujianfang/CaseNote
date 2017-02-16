/******************************************************************************
 * Copyright (c) 2012,山东山大华天软件有限公司
 * All rights reserved.
 * <p>
 * 文件名称： CaseNoteFragment.java
 * <p>
 * 创 建 人： 俞建方
 * 创建日期：  2017/2/15
 * 初始版本： 1.0
 ******************************************************************************/
package com.yujianfang.casenote;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

/**
 * Created by yujianfang on 2017/2/15.
 */
public class CaseNoteFragment extends Fragment{

    //定义控制控件
    private RadioGroup mCashTypeRadioGroup;

    private CaseBean mCaseBean;
    public static Fragment getCaseNoteFragment(){
        CaseNoteFragment c = new CaseNoteFragment();
        return  c;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.case_detail_layout,container,false);
        mCaseBean = new CaseBean();
        initView(v);

        return v;
    }

    private void initView(View v) {
        mCashTypeRadioGroup = (RadioGroup)v.findViewById(R.id.case_type);
        mCashTypeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i == R.id.case_income){
                    mCaseBean.setmCaseType(true);
                }else{
                    mCaseBean.setmCaseType(false);
                }
            }
        });
    }
}
