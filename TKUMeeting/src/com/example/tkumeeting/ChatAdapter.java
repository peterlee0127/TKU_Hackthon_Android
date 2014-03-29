package com.example.tkumeeting;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ChatAdapter extends ArrayAdapter<ChatContent> {
	private Context context;
	private int resource;
	private List<ChatContent> chat;
	private LayoutInflater inflater;
	private int[] resources;
	
	
	public ChatAdapter(Context context, int resource, List<ChatContent> chat) {
		super(context, resource, chat);
		inflater=LayoutInflater.from(context);
		this.context = context;
		this.resource = resource;
		this.chat = chat;
	}
	public void setResource(int a , int b, int c, int d){
		resources = new int[4];
		resources[0] = a;
		resources[1] = b;
		resources[2] = c;
		resources[3] = d;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		 if(convertView==null){
			 convertView=inflater.inflate(resource, null);
            viewHolder=new ViewHolder();
            viewHolder.stuIDTv=(TextView)convertView.findViewById(resources[0]);
            viewHolder.messageTv=(TextView)convertView.findViewById(resources[1]);
            viewHolder.image=(ImageView)convertView.findViewById(resources[2]);
            viewHolder.viewRight = (View)convertView.findViewById(resources[3]);
            viewHolder.stuIDTv.setText(chat.get(position).getStu_id());
            viewHolder.messageTv.setText(chat.get(position).getMessage());
            viewHolder.image.setImageResource(R.drawable.demo_avatar_jobs);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder)convertView.getTag();
        }

		return convertView;
	}

   private class ViewHolder{
       TextView stuIDTv;
       TextView messageTv;
       ImageView image;
       View viewRight;
   }
		
	
	

}
