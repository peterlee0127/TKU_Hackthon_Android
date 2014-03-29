package com.example.tkumeeting;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
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
		
        drawerLayout = (DrawerLayout) rootView.findViewById(R.id.drawer_layout);
        drawerListView = (ListView) rootView.findViewById(R.id.left_drawer);
        submitBtn = (Button) rootView.findViewById(R.id.submit_btn);
        chatLl = (LinearLayout)rootView.findViewById(R.id.chat_ll);
        chatInput = (EditText)rootView.findViewById(R.id.chat_input);
        target_sw = (Switch)rootView.findViewById(R.id.target_switch);
        
        submitBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String message = chatInput.getText().toString();
				String target = "";
				TextView tv = new TextView(action.getMainActivity());
				if(target_sw.isChecked())
					target = "tea";
				else
					target = "all";
				action.sendMessage(target, message);
				tv.setText(message);
				chatLl.addView(tv);
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
        for(int i=0;i<optionTitles.length;i++)
        	drawerList.add(new DrawerItem(optionTitles[i],0));
        drawerListView.setAdapter(new CustomDrawerAdapter(action.getMainActivity(),
                R.layout.data_option_item, drawerList));
        drawerListView.setOnItemClickListener(new DrawerItemClickListener());
	}

}
