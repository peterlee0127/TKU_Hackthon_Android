package com.example.tkumeeting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class GetClassListTask extends AsyncTask<String,String,String> {
	private ActionController action = ActionController.getSharedInstance();
	private String stu_id ="";
	private String password = "";
	
	public GetClassListTask() {
	}
	
	@Override
	protected void onPostExecute(String result) {
		action.onGetClassListResult(result);
	}
	
	@Override
	protected String doInBackground(String... params) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat("HHmmss");
		Date date = new Date(System.currentTimeMillis());
		Encoder encoder = new Encoder(); 
		
		String s2 = stu_id;
        String s3 = simpledateformat.format(date).toString();
        String s4 = encoder.md5(s2, s3);
        String s5 = encoder.encode(password);
        
		String url = "http://sinfo.ais.tku.edu.tw/eMisAppWs/AppWebservice.asmx/StuEleList2XML?pStuNo="+s2+"&pKey="+s3+"&pChksum="+s4+"&pPass="+s5;

		
		
		HttpClient httpclient = new DefaultHttpClient();
	    HttpGet httpget = new HttpGet(url);
	    
	    try {
	        HttpResponse response = httpclient.execute(httpget);
	        if(response != null) {
	            InputStream inputstream = response.getEntity().getContent();
	            return convertStreamToString(inputstream);
	        } else {
	            return "Unable to complete your request";
	        }
	    } catch (ClientProtocolException e) {
	        return "Caught ClientProtocolException";
	    } catch (IOException e) {
	        return "Caught IOException";
	    }
	}
	
	private String convertStreamToString(InputStream is) {
	    String line = "";
	    StringBuilder total = new StringBuilder();
	    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	    try {
	        while ((line = rd.readLine()) != null) {
	            total.append(line);
	        }
	    } catch (Exception e) {
	        return "Stream Exception";
	    }
	    return total.toString();
	}
	
	public String getStu_id() {
		return stu_id;
	}
	
	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	}