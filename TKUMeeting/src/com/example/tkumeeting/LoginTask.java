package com.example.tkumeeting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class LoginTask extends AsyncTask<String,String,String> {
	private ActionController action = ActionController.getSharedInstance();
	private String stu_id ="";
	private String password = "";
	
	public LoginTask() {
	}
	
	@Override
	protected void onPostExecute(String result) {
		action.onLoginResult(result.split("<Error_Msg>")[1].split("</Error_Msg>")[0]);
	}
	
	@Override
	protected String doInBackground(String... params) {
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpGet httpget = new HttpGet((new StringBuilder("http://163.13.121.60/emisappws/AppWebService.asmx/Check_Portal?pStuNo=")).append(stu_id).append("&pPass=").append(password).toString());
	    
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