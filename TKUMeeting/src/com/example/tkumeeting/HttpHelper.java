package com.example.tkumeeting;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;



@SuppressLint("SimpleDateFormat")
public class HttpHelper {
	//private WebSocket webSocket = WebSocket.getSharedInstance();
	private static HttpHelper httpHelper;

	private Encoder encoder;
	
	private HttpHelper(){
		encoder = new Encoder();
	}
	
	public static HttpHelper getSharedInstance(){
		if(httpHelper == null)
			httpHelper = new HttpHelper();
		return httpHelper;
	}
	
	public void checkLogin(String stu_id, String password){
			LoginTask task = new LoginTask();
			task.setStu_id(stu_id);
			task.setPassword(password);
			task.execute();
	}


	public String[][] getClassList(){
		SimpleDateFormat simpledateformat = new SimpleDateFormat("HHmmss");
		Date date = new Date(System.currentTimeMillis());
		Encoder encoder = new Encoder(); 
		String stu_id = "400410238";
		String password = "248321";
		
		String s2 = stu_id;
        String s3 = simpledateformat.format(date).toString();
        String s4 = encoder.md5(s2, s3);
        String s5 = password;
        
		String url = "http://sinfo.ais.tku.edu.tw/eMisAppWs/AppWebservice.asmx/StuEleList2XML?pStuNo="+s2+"&pKey="+s3+"&pChksum="+s4+"&pPass="+s5;
		
		return null;
	}
	public void onClassListResult(String result){
		
	}
}