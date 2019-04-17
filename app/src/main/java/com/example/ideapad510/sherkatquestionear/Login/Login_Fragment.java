package com.example.ideapad510.sherkatquestionear.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ideapad510.sherkatquestionear.Params.Params;
import com.example.ideapad510.sherkatquestionear.Questionnaire.QuestionnaireActivity;
import com.example.ideapad510.sherkatquestionear.R;

public class Login_Fragment extends Fragment implements OnClickListener {
	private static View view;

	private static EditText username, password;
	private static Button loginButton;
	private static CheckBox show_hide_password;
	private static LinearLayout loginLayout;
	private static Animation shakeAnimation;
	private static FragmentManager fragmentManager;


	Params params = Params.getInstance();
	private LoginController loginController = new LoginController(params.getContext());
	String TAG = "login_fragment";

	public Login_Fragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//		Log.d(TAG, "onCreateView: getActivity "+(getActivity().getBaseContext() == null));
		view = inflater.inflate(R.layout.login_fragment, container, false);
		initViews();
		setListeners();
		return view;
	}

	// Initiate Views
	private void initViews() {
		fragmentManager = getActivity().getSupportFragmentManager();

		username = (EditText) view.findViewById(R.id.login_username);
		password = (EditText) view.findViewById(R.id.login_password);
		loginButton = (Button) view.findViewById(R.id.loginBtn);
		show_hide_password = (CheckBox) view
				.findViewById(R.id.show_hide_password);
		loginLayout = (LinearLayout) view.findViewById(R.id.login_layout);

		// Load ShakeAnimation
		shakeAnimation = AnimationUtils.loadAnimation(getActivity(),
				R.anim.shake);

		username.setText("1");
		password.setText("1");

	}

	// Set Listeners
	private void setListeners() {
		loginButton.setOnClickListener(this);

		// Set check listener over checkbox for showing and hiding password
		show_hide_password
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton button,
                                                 boolean isChecked) {

						// If it is checked then show password else hide
						// password
						if (isChecked) {

							show_hide_password.setText(R.string.hide_pwd);// change
																			// checkbox
																			// text

							password.setInputType(InputType.TYPE_CLASS_TEXT);
							password.setTransformationMethod(HideReturnsTransformationMethod
									.getInstance());// show password
						} else {
							show_hide_password.setText(R.string.show_pwd);// change
																			// checkbox
																			// text

							password.setInputType(InputType.TYPE_CLASS_TEXT
									| InputType.TYPE_TEXT_VARIATION_PASSWORD);
							password.setTransformationMethod(PasswordTransformationMethod
									.getInstance());// hide password

						}

					}
				});

		password.setOnEditorActionListener( new EditText.OnEditorActionListener(){
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
				if(actionId == EditorInfo.IME_ACTION_DONE){
					onClick(v);
					return true;
				}

				return false;
			}
		});
	}

	@Override
	public void onClick(View v) {
		checkValidation();
	}

/*
	// Check Validation before login
	private void checkValidation() {
		// Get email id and password
		String getUsername = username.getText().toString();
		String getPassword = password.getText().toString();


		// Check for both field is empty or not
		if (getUsername.equals("") || getUsername.length() == 0
				|| getPassword.equals("") || getPassword.length() == 0) {
			loginLayout.startAnimation(shakeAnimation);
			new CustomToast().Show_Toast(getActivity(), view,
					"هر دو مورد را وارد کنید!");

		}
*/


	// Check Validation before login
	private void checkValidation() {

        // Get username and password
        String getUsername = username.getText().toString();
        String getPassword = password.getText().toString();



        if (loginController.checkValidation(getUsername, getPassword)) {
			loginLayout.startAnimation(shakeAnimation);
			new CustomToast().Show_Toast(getActivity(), view,
					"هر دو مورد را وارد کنید!");

		}

        loginController.searchAndGo(getUsername, getPassword, params.getContext());


/*
        if (loginController.searchInDatabase(getUsername,getPassword)) {
            Intent i = new Intent(getActivity(), QuestionnaireActivity.class);
            params.setUsername(getUsername);
            startActivity(i);
        }
*/


	}

}
