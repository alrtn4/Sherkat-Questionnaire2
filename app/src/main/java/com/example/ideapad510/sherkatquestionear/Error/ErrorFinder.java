package com.example.ideapad510.sherkatquestionear.Error;

import android.content.Context;

import com.example.ideapad510.sherkatquestionear.Login.LoginController;

public class ErrorFinder {


    public boolean checkValidation1(String username, String password){
        if(username.equals("") || username.length() == 0
                || password.equals("") || password.length() == 0) {

            return true;
        }

        return false;
    }


    public boolean checkValidation2(String username, String password, Context context){
        LoginController loginController = new LoginController(context);

        if(!loginController.searchInDatabase(username, password) &
                !checkValidation1(username, password)) {
            return true;
        }

        return false;
    }


    public boolean firstQuestion(int pageNumber){
        if(pageNumber == 0)
            return true;

        return false;
    }


/*
    public boolean lastQuestion(int pageNumber, int questionArraySize){
        if(pageNumber > questionArraySize )
            return true;

        return false;
    }
*/
}
