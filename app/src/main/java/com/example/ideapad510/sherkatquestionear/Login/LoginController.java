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
    private String TAG = "logincontroller";
    private Params params = Params.getInstance();
    private Context context;

    LoginController(Context context) {
        super(context);
        this.context = context;
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

        }else{
            new CustomToast().Show_Toast();
        }
    }


    // Check Validation before login
    public boolean checkValidation(String username, String password) {

        // Check for both field is empty or not
        if (username.equals("") || username.length() == 0
                || password.equals("") || password.length() == 0) {

            return true;
        }

        return false;


    }


}
