package com.example.tkumeeting;

import android.app.Fragment;
import android.app.FragmentTransaction;



public class ActionController {
	private HttpHelper httpHelper = HttpHelper.getSharedInstance();
	private static ActionController sharedInstance;
	private LoginFragment loginFragment;
	private VoteFragment voteFragment;
	private ClassListFragment classListFragment;
	private ChatRoomFragment chatRoomFragment;
	private MainActivity mainActivity;
	private final String ADMIN_ID = "";//"admin@admin";
	private final String ADMIN_PW = "";//"admin";
	private final int LEVEL_ADMIN = 99;
	private final int LEVEL_STUDENT = 0;
	private String stu_id = "";
	private String pw = "";
	private boolean remember = false;
	private boolean autoLogin = false;
	private boolean login = false;
	private int level = 0;
    private final int ADMIN_CLASS_LIST = 90;
    private final int ADMIN_CHAT = 91;
    private final int ADMIN_START_VOTE = 92;
    private final int ADMIN_END_VOTE = 93;
    private final int ADMIN_LOGOUT = 94;
    private final int STUDENT_CLASS_LIST = 0;
    private final int STUDENT_CHAT = 1;
    private final int STUDENT_LOGOUT = 2;
	
	private ActionController(){
		
	}
	
	public static ActionController getSharedInstance(){
		if(sharedInstance == null)
			sharedInstance = new ActionController();
		return sharedInstance;
	}
	
	public void login(String id, String pw, boolean rm, boolean al){
		if(id.equals(ADMIN_ID)&&pw.equals(ADMIN_PW)){
			this.stu_id = ADMIN_ID;
			this.pw = ADMIN_PW;
			this.level = LEVEL_ADMIN;
			this.login = true;
			if(rm)
				saveStu(stu_id, pw, al ,LEVEL_STUDENT);
			chatRoomFragment = new ChatRoomFragment();
			switchFragment(chatRoomFragment,0);
		}else{
			this.stu_id = id;
			this.pw = pw;
			httpHelper.checkLogin(id, pw);
		}
	}
	
	public void onLoginResult(String result){
		if(result.equals("õ{Âjñß·˘ê≥äm")){
			if(remember)
				saveStu(stu_id, pw, autoLogin ,LEVEL_STUDENT);
			this.level = LEVEL_STUDENT;
			this.login = true;
			chatRoomFragment = new ChatRoomFragment();
			switchFragment(chatRoomFragment,0);
		}else
			getLoginFragment().sendMsg(result);
	}
	
	private void saveStu(String stu_id, String pw, boolean al, int level){
		
	}
	
	public void checkAutoLogin(){
		
	}
	
	private void switchFragment(Fragment fragment, int type){
		FragmentTransaction ft = mainActivity.getFragmentManager().beginTransaction();
		ft.replace(R.id.container, fragment).commit();
	}

	public void sendMessage(String target, String message){
		
	}
	
	public void startVote() {
		switchFragment(voteFragment,0);
		
	}

	public void endVote() {
		// TODO Auto-generated method stub
		switchFragment(chatRoomFragment,0);
	}
	
	public void vote(String option){
		switchFragment(chatRoomFragment,0);
	}

	public void logout() {
		// TODO Auto-generated method stub
		
	}
	
	public void selectItem(int position) {
	    
	    if(level==LEVEL_ADMIN)
	    	position+=90;
		    switch(position){
		    	case ADMIN_CLASS_LIST:
		    		switchFragment(getClassListFragment(),0);
		    		break;
		    	case ADMIN_CHAT:
		    		switchFragment(getChatRoomFragment(),0);
		    		break;
		    	case ADMIN_START_VOTE:
		    		startVote();
		    		break;
		    	case ADMIN_END_VOTE:
		    		endVote();
		    		break;
		    	case ADMIN_LOGOUT:
		    		logout();
		    		break;
		    	case STUDENT_CLASS_LIST:
		    		switchFragment(getClassListFragment(),0);
		    		break;
		    	case STUDENT_CHAT:
		    		switchFragment(getChatRoomFragment(),0);
		    		break;
		    	case STUDENT_LOGOUT:
		    		logout();
		    		break;
		    	
		    }
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

	public int getLEVEL_ADMIN() {
		return LEVEL_ADMIN;
	}

	public int getLEVEL_STUDENT() {
		return LEVEL_STUDENT;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}
}
