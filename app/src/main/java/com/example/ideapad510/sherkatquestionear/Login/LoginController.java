package com.example.ideapad510.sherkatquestionear.Login;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.example.ideapad510.sherkatquestionear.Controller.Controller;
import com.example.ideapad510.sherkatquestionear.Params.Params;
import com.example.ideapad510.sherkatquestionear.Questionnaire.QuestionnaireActivity;

public class LoginController extends Controller {
    String TAG = "logincontroller";
    Params params = Params.getInstance();

    LoginController(Context context) {
        super(context);
    }

    public void insertToDatabase(String username, String password, String code) {
        databaseInsertMethods.insertLogin(username, password, code);
    }

    public boolean searchInDatabase(String username, String password) {
        if (!username.isEmpty() & !password.isEmpty())
            return databaseOtherMethods.searchLogin(username, password);
        return false;
    }

    public void searchAndGo(String username, String password, Context context) {
        if (searchInDatabase(username, password)) {
            Intent i = new Intent(context, QuestionnaireActivity.class);
            params.setUsername(username);
            context.startActivity(i);

        }
    }


    // Check Validation before login
    public boolean checkValidation(EditText username, EditText password) {
        // Get email id and password
        String getUsername = username.getText().toString();
        String getPassword = password.getText().toString();


        // Check for both field is empty or not
        if (getUsername.equals("") || getUsername.length() == 0
                || getPassword.equals("") || getPassword.length() == 0) {

            return true;
        }

        return false;


    }

}
