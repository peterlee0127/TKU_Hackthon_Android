package com.example.tkumeeting;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

public class ChatRoomFragment extends Fragment {
	private String[] optionTitles;
    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private Button submitBtn;
    private EditText chatInput;
    private LinearLayout charLl;
    
    private ActionController action = ActionController.getSharedInstance();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_chat_room, container,
				false);
		optionTitles = getResources().getStringArray(R.array.option_menu);
        drawerLayout = (DrawerLayout) rootView.findViewById(R.id.drawer_layout);
        drawerListView = (ListView) rootView.findViewById(R.id.left_drawer);
        submitBtn = (Button) rootView.findViewById(R.id.submit_btn);
        charLl = (LinearLayout)rootView.findViewById(R.id.chat_ll);
        chatInput = (EditText)rootView.findViewById(R.id.chat_input);
		initDrawerLayout();
		
		
		return rootView;
	}
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
	    @Override
	    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	        selectItem(position);
	    }
	}

	private void selectItem(int position) {
	    
	    drawerListView.setItemChecked(position, true);
	    drawerLayout.closeDrawer(drawerListView);
	}

	private void initDrawerLayout(){
        ArrayList<DrawerItem> drawerList = new ArrayList<DrawerItem>();
        for(int i=0;i<optionTitles.length;i++)
        	drawerList.add(new DrawerItem(optionTitles[i],0));
        drawerListView.setAdapter(new CustomDrawerAdapter(action.getMainActivity(),
                R.layout.drawer_item, drawerList));
        drawerListView.setOnItemClickListener(new DrawerItemClickListener());
	}
}
