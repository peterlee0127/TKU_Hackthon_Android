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

public class ClassListFragment extends Fragment {
	private String[] optionTitles;
    private DrawerLayout drawerLayout;
    private ListView drawerListView;
    private LinearLayout classListLayout;
    private ArrayList<Course> classList;
    private ArrayList<String> courseArray;
	private ArrayList<String> time_placeArray;
	private ArrayList<String> seat_noArray;
	private ArrayList<String> teach_nameArray;
    

    
    private ActionController action = ActionController.getSharedInstance();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_class_list, container,
				false);
		if(action.getLevel()==action.getLEVEL_ADMIN())
			optionTitles = getResources().getStringArray(R.array.option_menu_admin);
		else
			optionTitles = getResources().getStringArray(R.array.option_menu);
        drawerLayout = (DrawerLayout) rootView.findViewById(R.id.drawer_layout_class_list);
        drawerListView = (ListView) rootView.findViewById(R.id.left_drawer_class_list);
		initDrawerLayout();
		
		classListLayout = (LinearLayout)rootView.findViewById(R.id.class_list_ll);

		classList = new ArrayList<Course>();
		for(int i=0;i<courseArray.size();i++){
			classList.add(new Course(courseArray.get(i),time_placeArray.get(i),seat_noArray.get(i), teach_nameArray.get(i)));
		}
		ClassAdapter adapter = new ClassAdapter(action.getMainActivity(),R.layout.data_class_list,classList);
		for(int i=0;i<courseArray.size();i++){
			classListLayout.addView(adapter.getView(i, null, null));
		}
		
		
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

	public void setClassContent(ArrayList<String> courseArray,
			ArrayList<String> time_placeArray, ArrayList<String> seat_noArray,
			ArrayList<String> teach_nameArray) {
			this.courseArray = courseArray;
			this.time_placeArray = time_placeArray;
			this.seat_noArray = seat_noArray;
			this.teach_nameArray = teach_nameArray;
		

		
	}
	


}
