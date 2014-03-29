package com.example.tkumeeting;

import android.app.Fragment;
import android.app.FragmentTransaction;



public class ActionController {
	private static ActionController sharedInstance;
	private LoginFragment loginFragment;
	private VoteFragment voteFragment;
	private ClassListFragment classListFragment;
	private ChatRoomFragment chatRoomFragment;
	private MainActivity mainActivity;
	private final String ADMIN_ID = "";//"admin@admin";
	private final String ADMIN_PW = "";//"admin";
	
	private ActionController(){
		
	}
	
	public static ActionController getSharedInstance(){
		if(sharedInstance == null)
			sharedInstance = new ActionController();
		return sharedInstance;
	}
	
	public void login(String id, String pw, boolean rm, boolean al){
		if(id.equals(ADMIN_ID)&&pw.equals(ADMIN_PW)){
			switchFragment(chatRoomFragment,0);
		}else{
			
		}
	}
	
	public void checkAutoLogin(){
		
	}
	
	private void switchFragment(Fragment fragment, int type){
		FragmentTransaction ft = mainActivity.getFragmentManager().beginTransaction();
		ft.replace(R.id.container, fragment).commit();
	}

	public void sendMessage(String target, String message){
		
	}
	
	public LoginFragment getLoginFragment() {
		return loginFragment;
	}

	public void setLoginFragment(LoginFragment loginFragment) {
		this.loginFragment = loginFragment;
	}

	public VoteFragment getVoteFragment() {
		return voteFragment;
	}

	public void setVoteFragment(VoteFragment voteFragment) {
		this.voteFragment = voteFragment;
	}

	public ClassListFragment getClassListFragment() {
		return classListFragment;
	}

	public void setClassListFragment(ClassListFragment classListFragment) {
		this.classListFragment = classListFragment;
	}

	public ChatRoomFragment getChatRoomFragment() {
		return chatRoomFragment;
	}

	public void setChatRoomFragment(ChatRoomFragment chatRoomFragment) {
		this.chatRoomFragment = chatRoomFragment;
	}

	public MainActivity getMainActivity() {
		return mainActivity;
	}

	public void setMainActivity(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
	}
}
