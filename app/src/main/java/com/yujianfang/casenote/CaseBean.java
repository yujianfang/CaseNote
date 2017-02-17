/******************************************************************************
 * Copyright (c) 2012,山东山大华天软件有限公司
 * All rights reserved.
 * <p>
 * 文件名称： CaseBean.java
 * <p>
 * 创 建 人： 俞建方
 * 创建日期：  2017/2/16
 * 初始版本： 1.0
 ******************************************************************************/
package com.yujianfang.casenote;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.UUID;

/**
 * Created by yujianfang on 2017/2/16.
 */
public class CaseBean {
    private static final String ID= "uuid";
    private static final String CASH_TYPE= "caseType";
    private static final String CASH_COUNT= "caseCount";
    private static final String CASH_DATE= "caseDate";
    private static final String CASE_DESCRIBE= "caseDescribe";
   public CaseBean(){
       mUUid = UUID.randomUUID();
       mDate = new Date();
   }

    public CaseBean(JSONObject jsonObject)throws JSONException{
        mUUid = UUID.fromString(jsonObject.getString(ID));
        mCaseType = jsonObject.getBoolean(CASH_TYPE);
        mNumber = jsonObject.getDouble(CASH_COUNT);
        mDate = (Date)jsonObject.get(CASH_DATE);
        mDescribe = jsonObject.getString(CASE_DESCRIBE);
    }

    public JSONObject toJson()throws JSONException{
        JSONObject json = new JSONObject();
        json.put(ID,mUUid.toString());
        json.put(CASH_TYPE,mCaseType);
        json.put(CASH_COUNT,mNumber);
        json.put(CASH_DATE,mDate);
        json.put(CASE_DESCRIBE,mDescribe);

        return json;
    }
    public UUID getmUUid() {
        return mUUid;
    }

    public void setmUUid(UUID mUUid) {
        this.mUUid = mUUid;
    }

    public Boolean getmCaseType() {
        return mCaseType;
    }

    public void setmCaseType(Boolean mCaseType) {
        this.mCaseType = mCaseType;
    }

    public double getmNumber() {
        return mNumber;
    }

    public void setmNumber(double mNumber) {
        this.mNumber = mNumber;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public String getmDescribe() {
        return mDescribe;
    }

    public void setmDescribe(String mDescribe) {
        this.mDescribe = mDescribe;
    }

    private UUID mUUid;
    private Boolean mCaseType;
    private double mNumber;
    private Date mDate;
    private String mDescribe;

}
