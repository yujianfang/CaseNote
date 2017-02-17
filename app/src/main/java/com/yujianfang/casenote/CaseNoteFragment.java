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

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.Date;
import java.util.UUID;

/**
 * Created by yujianfang on 2017/2/15.
 */
public class CaseNoteFragment extends Fragment implements View.OnClickListener{

    //定义控制控件
    private RadioGroup mCashTypeRadioGroup;
    private EditText mCaseMoney;
    private Button mCaseDateButton;
    private EditText mCaseDescribe;
    private Button mCaseSaveButton;
    private Button mCaseClearInfoButton;

    //定义日期返回处理值
    private static final int REQUEST_DATE = 1111;
    private static final String DATA_DIALOG = "date";
    public static final String CASH_ID = "cashUUID";
    private CaseBean mCaseBean;

    public static  Fragment newInstance(UUID uuid){
        //将uuid序列化到类中，便于获取
        Bundle data = new Bundle();
        data.putSerializable(CASH_ID,uuid);
        CaseNoteFragment caseNoteFragment = new CaseNoteFragment();
        caseNoteFragment.setArguments(data);
        return caseNoteFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.case_detail_layout,container,false);
        initCash();
        initView(v);
        initListener();
        return v;
    }

    private void initCash() {
        UUID uuid = (UUID) getArguments().getSerializable(CASH_ID);
        mCaseBean = CashLab.get(getActivity()).getCash(uuid);
    }

    private void initListener() {
        mCaseDateButton.setOnClickListener(this);
        mCaseSaveButton.setOnClickListener(this);
        mCaseClearInfoButton.setOnClickListener(this);
    }

    private void initView(View v) {
        //收支类型
        mCashTypeRadioGroup = (RadioGroup)v.findViewById(R.id.case_type);
        //收支金额
        mCaseMoney = (EditText) v.findViewById(R.id.moneycount);
        //收支时间
        mCaseDateButton = (Button) v.findViewById(R.id.selectDate);
        //收支描述
        mCaseDescribe = (EditText) v.findViewById(R.id.describe_cash);
        //保存
        mCaseSaveButton = (Button) v.findViewById(R.id.save_cash);
        mCaseClearInfoButton= (Button) v.findViewById(R.id.clear_case_info);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.selectDate:
                selectCashDate();
                break;
            case R.id.save_cash:
                saveCashInfo();
                break;
            case R.id.clear_case_info:
                clearCashInfo();
                break;
            default:
                break;
        }
    }

    private void clearCashInfo() {
    }

    private void saveCashInfo() {
    }
    //选择日期
    private void selectCashDate() {
        FragmentManager fragmentManager = getFragmentManager();
        Date date = new Date();

        DatePickerFragment datePickerFragment = DatePickerFragment.newInstence(date);

        datePickerFragment.setTargetFragment(CaseNoteFragment.this,REQUEST_DATE);
        datePickerFragment.show(fragmentManager,DATA_DIALOG);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_DATE){
            Date d = (Date)data.getSerializableExtra(DatePickerFragment.DATE_PICKER);
           mCaseDateButton.setText(d.toString());

        }
    }
}
