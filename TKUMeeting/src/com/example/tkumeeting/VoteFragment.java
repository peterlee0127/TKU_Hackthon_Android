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
import android.widget.ListView;
import android.widget.TextView;

public class VoteFragment extends Fragment {
	private String[] optionTitles;
    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private Button aBtn;
    private Button bBtn;
    private Button cBtn;
    private Button dBtn;
    private Button vBtn;
    private String option = "";
    private TextView problemNoTv;
    
    private ActionController action = ActionController.getSharedInstance();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_vote, container,
				false);
		if(action.getLevel()==action.getLEVEL_ADMIN()){
			optionTitles = getResources().getStringArray(R.array.option_menu_admin);
	        drawerLayout = (DrawerLayout) rootView.findViewById(R.id.drawer_layout_vote);
	        drawerListView = (ListView) rootView.findViewById(R.id.left_drawer_vote);
			initDrawerLayout();
		}
		problemNoTv = (TextView)rootView.findViewById(R.id.problem_no_tv);
		aBtn = (Button)rootView.findViewById((R.id.a_btn));
		bBtn = (Button)rootView.findViewById((R.id.b_btn));
		cBtn = (Button)rootView.findViewById((R.id.c_btn));
		dBtn = (Button)rootView.findViewById((R.id.d_btn));
		vBtn = (Button)rootView.findViewById(R.id.vote_button);
		vBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				if(!option.equals(""))
					action.vote(option);
			}
		});
		aBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				option = "A";
			}
		});
		bBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				option = "B";
			}
		});
		cBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				option = "C";
			}
		});
		dBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				option = "D";
			}
		});
	
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
	
	public void setProblemNo(String problem){
		problemNoTv.setText(problem);
	}

}
