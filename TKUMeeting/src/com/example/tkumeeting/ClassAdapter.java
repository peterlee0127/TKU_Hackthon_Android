package com.example.tkumeeting;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ClassAdapter extends ArrayAdapter<Course> {
	private List<Course> courses;
	private Context context;
	private int resourseID;
	private LayoutInflater inflater;

	
	public ClassAdapter(Context context, int resource, List<Course> courses) {
		super(context, resource, courses);
		inflater=LayoutInflater.from(context);
		this.context = context;
		this.resourseID = resource;
		this.courses = courses;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		 if(convertView==null){
			 convertView=inflater.inflate(resourseID, null);
             viewHolder=new ViewHolder();
             viewHolder.nameTv=(TextView)convertView.findViewById(R.id.class_name_tv);
             viewHolder.timeTv=(TextView)convertView.findViewById(R.id.time_tv);
             viewHolder.placeTv=(TextView)convertView.findViewById(R.id.class_room_tv);
             viewHolder.teacherTv=(TextView)convertView.findViewById(R.id.teacher_tv);
             viewHolder.seatTv=(TextView)convertView.findViewById(R.id.seat_tv);
             viewHolder.nameTv.setText(courses.get(position).getName());
             viewHolder.timeTv.setText(courses.get(position).getTime());
             viewHolder.placeTv.setText(courses.get(position).getPlace());
             viewHolder.teacherTv.setText(courses.get(position).getTeacher());
             viewHolder.seatTv.setText(courses.get(position).getSeat_no());
             convertView.setTag(viewHolder);
         }else{
             viewHolder=(ViewHolder)convertView.getTag();
         }

		return convertView;
	}

    private class ViewHolder{
        TextView nameTv;
        TextView timeTv;
        TextView placeTv;
        TextView teacherTv;
        TextView seatTv;
    }



}
