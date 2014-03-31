package com.example.tkumeeting;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

public class ChatRoomFragment extends Fragment {
	private String[] optionTitles;
    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private Button submitBtn;
    private EditText chatInput;
    private LinearLayout chatLl;
    private Switch target_sw;
    private ChatAdapter adapterRight;
    private ChatAdapter adapterLeft;
    
    private ActionController action = ActionController.getSharedInstance();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_chat_room, container,
				false);
		if(action.getLevel()==action.getLEVEL_ADMIN())
			optionTitles = getResources().getStringArray(R.array.option_menu_admin);
		else
			optionTitles = getResources().getStringArray(R.array.option_menu);
		adapterRight = new ChatAdapter(action.getMainActivity(),R.layout.chat_content_right,action.getChatContent());
		adapterRight.setResource(R.id.user_right, R.id.chat_content_right, R.id.imageView_right, R.id.view_right);
		adapterLeft = new ChatAdapter(action.getMainActivity(),R.layout.chat_content_left,action.getChatContent());
		adapterLeft.setResource(R.id.user_left, R.id.chat_content_left, R.id.imageView_left, R.id.view_left);
        drawerLayout = (DrawerLayout) rootView.findViewById(R.id.drawer_layout);
        drawerListView = (ListView) rootView.findViewById(R.id.left_drawer);
        submitBtn = (Button) rootView.findViewById(R.id.submit_btn);
        chatLl = (LinearLayout)rootView.findViewById(R.id.chat_ll);
        chatInput = (EditText)rootView.findViewById(R.id.chat_input);
        target_sw = (Switch)rootView.findViewById(R.id.target_switch);
        restoreChatContent();
        

		
        
        submitBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String message = chatInput.getText().toString();
				String target = "";
				if(target_sw.isChecked())
					target = "Teacher";
				else
					target = "All";
				action.sendMessage(target, message);
				action.addChatContent(message);
				chatLl.addView(adapterRight.getView(action.getChatContent().size()-1, null, null));
			}
        	
        });
        
		initDrawerLayout();
		
		
		return rootView;
	}
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
	    @Override
	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		    drawerListView.setItemChecked(position, true);
	        drawerLayout.closeDrawer(drawerListView);
	        action.selectItem(position);
	    }
	}

	private void initDrawerLayout(){
        ArrayList<DrawerItem> drawerList = new ArrayList<DrawerItem>();
        if(optionTitles.length==3){
        	drawerList.add(new DrawerItem(optionTitles[0],R.drawable.class_));
        	drawerList.add(new DrawerItem(optionTitles[1],R.drawable.chat));
        	drawerList.add(new DrawerItem(optionTitles[2],R.drawable.leave));
        }else{
        	drawerList.add(new DrawerItem(optionTitles[0],R.drawable.class_));
        	drawerList.add(new DrawerItem(optionTitles[1],R.drawable.chat));
        	drawerList.add(new DrawerItem(optionTitles[2],R.drawable.vote_start));
        	drawerList.add(new DrawerItem(optionTitles[3],R.drawable.vote));
        	drawerList.add(new DrawerItem(optionTitles[4],R.drawable.leave));
        }
        drawerListView.setAdapter(new CustomDrawerAdapter(action.getMainActivity(),
                R.layout.data_option_item, drawerList));
        drawerListView.setOnItemClickListener(new DrawerItemClickListener());
	}
	public void sendMsg(String stu_id , String msg){
		action.getChatContent().add(new ChatContent(stu_id,msg));
		chatLl.addView(adapterLeft.getView(action.getChatContent().size()-1, null, null));
	}
	
	public void restoreChatContent(){
		ArrayList<ChatContent> chat = action.getChatContent();
		for(int i=0;i<chat.size();i++){
			if(chat.get(i).getStu_id().equals(action.getStu_id()))
				chatLl.addView(adapterRight.getView(i, null, null));
			else
				chatLl.addView(adapterLeft.getView(i, null, null));
		}
	}
	


}
