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

public class GetClassIDTask extends AsyncTask<String,String,String> {
	private ActionController action = ActionController.getSharedInstance();
	private String url = "";
	
	public GetClassIDTask() {
	}
	
	@Override
	protected void onPostExecute(String result) {
		WebSocket.getSharedInstance().onGetClassIDResult(result);
	}
	
	@Override
	protected String doInBackground(String... params) {
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
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	}