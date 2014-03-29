package com.example.tkumeeting;

public class ChatContent {
	private String stu_id;
	private String message;
	public ChatContent(String stu_id,String message){
		this.stu_id = stu_id;
		this.message = message;
	}
	
	public String getStu_id() {
		return stu_id;
	}
	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
