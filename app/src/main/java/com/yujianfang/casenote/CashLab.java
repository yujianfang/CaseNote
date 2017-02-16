package com.yujianfang.casenote;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.json.JSONException;

import android.content.Context;

public class CashLab {
	private List<CaseBean> cashes;
	
	private static CashLab sCrimeLab;
	private Context mAppContext;
	private static final String FILE_NAME = "crime.json";
	
	private CashInstanceJSONSerializer mCrimeSerializer;
	private CashLab(Context appContext){
		mAppContext = appContext;
		mCrimeSerializer = new CashInstanceJSONSerializer(mAppContext, FILE_NAME);
		try {
			cashes = mCrimeSerializer.loadCrimes();
		} catch (Exception e) {
			e.printStackTrace();
			cashes = new ArrayList<CaseBean>();
		} 
	}
	
	public static CashLab get(Context c){
		if(sCrimeLab == null){
			sCrimeLab = new CashLab(c.getApplicationContext());
		}
		return sCrimeLab;
	}
	
	
	public void addCash(CaseBean caseBean){
		cashes.add(caseBean);
	}
	
	public void removCash(CaseBean caseBean){
		cashes.remove(caseBean);
	}
	public boolean saveCash(){
		try {
			mCrimeSerializer.saveCrime(cashes);
			return true;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public List<CaseBean> getCrimes(){
		return cashes;
	}
	
	public CaseBean getCash(UUID uId){
		for(CaseBean c : cashes){
			if(uId.equals(c.getmUUid())){
				return c;
			}
		}
		return null;
	}
}
