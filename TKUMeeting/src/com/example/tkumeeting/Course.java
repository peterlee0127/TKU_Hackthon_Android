package com.example.tkumeeting;


public class Course {
	private String name;
	private String time;
	private String week;
	private String teacher;
	private String place;
	private String seat_no;
	public Course(String name,String time_place,String seat_no ,String teacher){
		this.name = name;
		String[] str = time_place.split("/");
		this.week = str[0]+"/"+str[1];
		this.time = str[0]+"/"+str[1];
		this.place = str[2];
		this.seat_no = seat_no;
		this.teacher = teacher;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getSeat_no() {
		return seat_no;
	}
	public void setSeat_no(String seat_no) {
		this.seat_no = seat_no;
	}
}
