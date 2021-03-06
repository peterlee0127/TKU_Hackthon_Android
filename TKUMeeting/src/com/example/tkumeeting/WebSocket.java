package com.example.tkumeeting;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.media.AudioManager;
import android.os.Handler;
import android.util.Log;

import com.koushikdutta.async.http.socketio.Acknowledge;
import com.koushikdutta.async.http.socketio.ConnectCallback;
import com.koushikdutta.async.http.socketio.DisconnectCallback;
import com.koushikdutta.async.http.socketio.ErrorCallback;
import com.koushikdutta.async.http.socketio.EventCallback;
import com.koushikdutta.async.http.socketio.JSONCallback;
import com.koushikdutta.async.http.socketio.SocketIOClient;
import com.koushikdutta.async.http.socketio.StringCallback;

public class WebSocket implements DisconnectCallback, ErrorCallback, JSONCallback, StringCallback, EventCallback{

	private static WebSocket sharedInstance = null;
	private SocketIOClient client = null;
	private ActionController action = ActionController.getSharedInstance();
	private String host = "";
	private String port = "";
	private final String TEG= "socket error"; 
	private final int RECONNECT_TIMES_LIMIT = 10;
	private int reconnectTimes = 0;
	private boolean added =false;
	private String stu_id;
	private int loginLevel = -1;
	private final int COME = 0;
	private final int BACK = 1;
	private String classID = "";
	
	private final String START_VOTE = "start_vote";
	private final String VOTE_RESULT = "vote_result";
	private final String ADDME_RES = "addme_res";
	private final String LISTEN_CHAT = "listen_chat";
	private final String VOTE_RES = "vote_res";
	
	
	
	private WebSocket (){
		host = "http://192.168.41.69";
		port = "3000";
		reconnectTimes = 0;
	}
	
	public static WebSocket getSharedInstance(){
		if (sharedInstance==null)
			sharedInstance = new WebSocket();
		return sharedInstance;
	}
	
	public void connectToServer(){
		if(client == null || !client.isConnected())
			SocketIOClient.connect(host+":"+port, connectCallback, new Handler());
	}
	
	private ConnectCallback connectCallback = new ConnectCallback() {

        @Override
        public void onConnectCompleted(Exception ex, SocketIOClient client) {
            if (ex != null) {
                ex.printStackTrace();
                Log.e(TEG,ex.toString());
                return;
            }
            //This client instance will be using the same websocket as the original client, 
            //but will point to the indicated endpoint
            client.setDisconnectCallback(sharedInstance);
            client.setErrorCallback(sharedInstance);
            client.setJSONCallback(sharedInstance);
            client.setStringCallback(sharedInstance);
            client.addListener(START_VOTE, sharedInstance);  
            client.addListener(VOTE_RESULT, sharedInstance);  
            client.addListener(ADDME_RES, sharedInstance); 
            client.addListener(LISTEN_CHAT, sharedInstance);
            client.addListener(VOTE_RES, sharedInstance);
            GetClassIDTask task = new GetClassIDTask();
            task.setUrl(host+":"+port+"/api/list");
            task.execute();
            sharedInstance.client = client;
            reconnectTimes = 0;

        }
    };
    
    public void sendMessage(String message, String target, String stu_id){
    	if( client!=null && client.isConnected() ){
	        JSONObject jsonObj = new JSONObject();
	        JSONArray json = new JSONArray();
	        try {
				jsonObj.put("message", message);
				jsonObj.put("target", target);
				jsonObj.put("stu_id", stu_id);
				json.put(jsonObj);
			} catch (JSONException e) {
				Log.e(TEG,e.toString());
				e.printStackTrace();
			}
	        client.emit("chat", json);
    	}		
    }
    
    public void sendVote(String stu_id, String select){
    	if( client!=null && client.isConnected() ){
	        JSONObject jsonObj = new JSONObject();
	        JSONArray json = new JSONArray();
	        try {
				jsonObj.put("stu_id", stu_id);
				jsonObj.put("answer", select);
				jsonObj.put("class_id", classID);
				json.put(jsonObj);
			} catch (JSONException e) {
				Log.e(TEG,e.toString());
				e.printStackTrace();
			}
	        client.emit("voting", json);
    	}		
    }
    
    public void startVote(){
    	if( client!=null && client.isConnected() ){
	        JSONObject jsonObj = new JSONObject();
	        JSONArray json = new JSONArray();
	        try {
				jsonObj.put("class_id", classID);
				json.put(jsonObj);
			} catch (JSONException e) {
				Log.e(TEG,e.toString());
				e.printStackTrace();
			}
	        client.emit("vote_req", json);
    	}		
    }
    
    public void endVote(){
    	if( client!=null && client.isConnected() ){
	        JSONObject jsonObj = new JSONObject();
	        JSONArray json = new JSONArray();
	        try {
				jsonObj.put("class_id", classID);
				json.put(jsonObj);
			} catch (JSONException e) {
				Log.e(TEG,e.toString());
				e.printStackTrace();
			}
	        client.emit("end_vote", json);
    	}		
    }
    
    public void addMe(String stu_id){
    	if( client!=null && client.isConnected() && !added && !classID.equals("")){
	        JSONObject jsonObj = new JSONObject();
	        JSONArray json = new JSONArray();
	        try {
				jsonObj.put("stu_id", stu_id);
				jsonObj.put("class_id", classID);
				json.put(jsonObj);
			} catch (JSONException e) {
				Log.e(TEG,e.toString());
				e.printStackTrace();
			}
	        client.emit("addme", json);
    	}		
    }
	
	@Override
	public void onEvent(String event, JSONArray argument, Acknowledge acknowledge) {
		if(!action.isLogin())
			action.logout();
		else{
			
		    switch(event){
		    	case START_VOTE:
		    		if(action.getLevel()!=action.getLEVEL_ADMIN())
		    			action.startVote();
		    		break;
		    	case VOTE_RESULT:
		    		action.endVote();
		    		break;
		    	case ADDME_RES:
		    		added = true;
		    		action.changeSoundModeVibrate();
		    		break;
		    	case LISTEN_CHAT:
				try {
					String target = argument.getJSONObject(0).getString("target");
					String message = argument.getJSONObject(0).getString("message");
					String stu_id =  argument.getJSONObject(0).getString("stu_id");
					if(target.equals("All"))
						action.getChatRoomFragment().sendMsg(stu_id,message);
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    		break;
		    	case VOTE_RES:
					try {
						if(argument.getJSONObject(0).get("result").toString().equals("ok"))
							action.endVote();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		break;
		    }

		}
	}
	
	@Override
	public void onString(String string, Acknowledge acknowledge) {
	    Log.d(TEG, string);
	
	}
	
	@Override
	public void onJSON(JSONObject json, Acknowledge acknowledge) {
	    try {
	        Log.d(TEG, "json:" + json.toString(2));
	    } catch (JSONException e) {
	        e.printStackTrace();
	    }
	
	}
	
	@Override
	public void onError(String error) {
	    Log.d(TEG, error);
	    if(reconnectTimes < RECONNECT_TIMES_LIMIT){
	    	reconnectTimes++;
			Handler handler = new Handler();
			handler.postDelayed(new Runnable() 
				{
				    @Override
				    public void run() {
				        connectToServer();
				    }
				}, 2000);
	    }
	}
	
	@Override
	public void onDisconnect(Exception e) {
			Log.d(TEG, "Disconnected");
			Handler handler = new Handler();
			handler.postDelayed(new Runnable() 
				{
				    @Override
				    public void run() {
				        connectToServer();
				    }
				}, 2000);
	}
	
	public void setAdded(boolean added){
		this.added = added;
	}
	public boolean isAdded(){
		return added;
	}
	public String getClassID(){
		return classID;
	}
	public void setStu_id(String stu_id){
		this.stu_id = stu_id;
	}
	public String getStu_id(){
		return stu_id;
	}
	public int getLoginLevel() {
		return loginLevel;
	}
	public void setLoginLevel(int loginLevel) {
		this.loginLevel = loginLevel;
	}

	public void onGetClassIDResult(String result)  {
		// TODO Auto-generated method stub
		try {
			JSONArray obj = new JSONArray(result);
			classID = obj.getJSONObject(0).get("_id").toString();
			action.switchFragment(action.getChatRoomFragment(), 0);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
