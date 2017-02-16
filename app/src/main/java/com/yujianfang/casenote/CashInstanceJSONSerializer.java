package com.yujianfang.casenote;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import android.content.Context;
import android.util.Log;

public class CashInstanceJSONSerializer {
	private Context mContext;
	private String mFileName;
	
	public CashInstanceJSONSerializer(Context c, String fileName){
		this.mContext = c;
		this.mFileName = fileName;
	}
	public List<CaseBean> loadCrimes() throws IOException, JSONException{
		List<CaseBean> cashes = new ArrayList<CaseBean>();
		BufferedReader reader = null;
		try{
		InputStream in = mContext.openFileInput(mFileName);
		reader = new BufferedReader(new InputStreamReader(in));
		StringBuffer jsonString = new StringBuffer();
		String line = null;
		while((line = reader.readLine())!=null){
			jsonString.append(line);
		}
		JSONArray jsonArray = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
		
		for(int i = 0;i < jsonArray.length();i++){
			cashes.add(new CaseBean(jsonArray.getJSONObject(i)));
		}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}finally{
			if(reader!=null){
				reader.close();
			}
		}
		
		return cashes;
		
	}
	public void saveCrime(List<CaseBean> cashes) throws JSONException{
		JSONArray jsonArray = new JSONArray();
		
		for(CaseBean cash :cashes ){
				jsonArray.put(cash.toJson());
		}
		//写入文件
		Writer writer = null;
		
		try {
			OutputStream out = mContext.openFileOutput(mFileName, Context.MODE_PRIVATE);
			writer =  new OutputStreamWriter(out);
			writer.write(jsonArray.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(writer != null){
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
