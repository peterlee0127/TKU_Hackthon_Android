package com.example.tkumeeting;

import java.util.ArrayList;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

public class ClassListFragment extends Fragment {
	private String[] optionTitles;
    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private LinearLayout classListLayout;
    private ScrollView classListSv;
    private ArrayList<Course> classList;

    
    private ActionController action = ActionController.getSharedInstance();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_class_list, container,
				false);
		optionTitles = getResources().getStringArray(R.array.option_menu_admin);
        drawerLayout = (DrawerLayout) rootView.findViewById(R.id.drawer_layout_vote);
        drawerListView = (ListView) rootView.findViewById(R.id.left_drawer_vote);
		initDrawerLayout();
		
		classListSv = (ScrollView)rootView.findViewById(R.id.class_list_sv);
		classListLayout = (LinearLayout)rootView.findViewById(R.id.class_list_ll);

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

	public void setClassContent(ArrayList<String> courseArray,
			ArrayList<String> time_placeArray, ArrayList<String> seat_noArray,
			ArrayList<String> teach_nameArray) {
		classList = new ArrayList<Course>();
		for(int i=0;i<courseArray.size();i++){
			classList.add(new Course(courseArray.get(i),time_placeArray.get(i),seat_noArray.get(i), teach_nameArray.get(i)));
		}
		ClassAdapter adapter = new ClassAdapter(action.getMainActivity(),R.layout.data_class_list,classList);
		for(int i=0;i<courseArray.size();i++){
			classListSv.addView(adapter.getView(i, null, null));
		}
		
		
	}
	


}
