package com.example.tkumeeting;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class LoginFragment extends Fragment{
	private ActionController action = ActionController.getSharedInstance();
	private CheckBox remember;
	private CheckBox autoLogin;
	private EditText idInput;
	private EditText pwInput;
	private Button loginBtn;
	private TextView errorText;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_login, container,
				false);
		remember = (CheckBox)rootView.findViewById(R.id.remember_check);
		autoLogin = (CheckBox)rootView.findViewById(R.id.auto_login_check);
		loginBtn = (Button)rootView.findViewById(R.id.login_btn);
		idInput = (EditText)rootView.findViewById(R.id.id_input);
		pwInput = (EditText)rootView.findViewById(R.id.pw_input);
		errorText = (TextView)rootView.findViewById(R.id.error_text);
		
		loginBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				String id = idInput.getText().toString();
				String pw = pwInput.getText().toString();
				boolean rm = remember.isChecked();
				boolean al = autoLogin.isChecked();
				if(id.equals("")||pw.equals("")){
					errorText.setText(R.string.input_error);
					action.login(id, pw, rm, al);
				}else{
					action.login(id, pw, rm, al);
				}
			}
			
		});
		action.checkAutoLogin();
		
		return rootView;
	}
}
