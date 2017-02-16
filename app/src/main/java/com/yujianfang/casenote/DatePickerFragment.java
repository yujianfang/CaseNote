/******************************************************************************
 * Copyright (c) 2012,山东山大华天软件有限公司
 * All rights reserved.
 * <p>
 * 文件名称： DatePickerFragment.java
 * <p>
 * 创 建 人： 俞建方
 * 创建日期：  2017/2/16
 * 初始版本： 1.0
 ******************************************************************************/
package com.yujianfang.casenote;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by yujianfang on 2017/2/16.
 */
public class DatePickerFragment extends DialogFragment{
    public static final String DATE_PICKER = "com.yujianfnag.casenote.DatePickerFragment";

    private Date mDate;

    public static DatePickerFragment newInstence(Date date){
        Bundle bundle = new Bundle();
        bundle.putSerializable(DATE_PICKER,date);
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setArguments(bundle);
        return  datePickerFragment;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDate = (Date)getArguments().getSerializable(DATE_PICKER);
        //获取日期
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mDate);
        int year = calendar.get(Calendar.YEAR);
        int mouth = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DAY_OF_MONTH);

        //初始化dialog
        View v  = getActivity().getLayoutInflater().inflate(R.layout.date_picker_layout,null);

        DatePicker datePicker =(DatePicker) v.findViewById(R.id.datePicker);

        datePicker.init(year, mouth, date, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                mDate = new GregorianCalendar(i,i1,i2).getTime();
                getArguments().putSerializable(DATE_PICKER,mDate);
            }
        });

        return new AlertDialog.Builder(getActivity()).setView(v).setTitle("请选择日期").setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sendResult(Activity.RESULT_OK);
            }
        }).create();
    }

    private void sendResult(int resultOk) {
        if(getTargetFragment() == null) return;
        Intent intent = new Intent();
        intent.putExtra(DATE_PICKER,mDate);
        getTargetFragment().onActivityResult(getTargetRequestCode(),resultOk,intent);
    }
}
