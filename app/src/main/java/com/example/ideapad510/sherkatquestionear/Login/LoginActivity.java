package com.example.ideapad510.sherkatquestionear.Login;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import com.example.ideapad510.sherkatquestionear.JsonHandler.RetroFit.Controller2;
import com.example.ideapad510.sherkatquestionear.Params.Params;
import com.example.ideapad510.sherkatquestionear.R;

public class LoginActivity extends AppCompatActivity {

	private static FragmentManager fragmentManager;
	private Params params = Params.getInstance();
	private String TAG = "login";
//	private SharedPreferences prefs = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
		setContentView(R.layout.login);
		fragmentManager = getSupportFragmentManager();

		params.setContext(this);

		// If savedinstnacestate is null then replace login fragment
		if (savedInstanceState == null) {
			fragmentManager
					.beginTransaction()
					.replace(R.id.frameContainer, new Login_Fragment(),
							Utils.Login_Fragment).commit();
		}


		//this lines are for making sure startnewtables runs only on first run of program and we don't add
		//useless data to database
		SharedPreferences prefs = getSharedPreferences("com.example.ideapad510.sherkatquestionear", MODE_PRIVATE);

		if(prefs.getBoolean("firstRun",true)){
			new StartAllTables(this);

			prefs.edit().putBoolean("firstRun", false).commit();
		}


//        jsonExaminer();

	}




	private void jsonExaminer(){
        try {
//			JSONObject jsonObject = new JSONObject(new JSONText().getText());

//			HttpHandler httpHandler = new HttpHandler();
            Controller2 controller = new Controller2();
            controller.start();

//			httpHandler.start("http://jmr.samim.org/JMR-Q/req-app/send-results.php?req=1", jsonObject.toString());
//			new HttpHandler().start("http://jmr.samim.org/JMR-Q/req-app/send-results.php?req=1", new JSONText2().getText());
        }
        catch(Exception e) {
            Log.d(TAG, "onCreate: hiiiiiiiiiiiiiiiii    "+e);
        }

    }


}
