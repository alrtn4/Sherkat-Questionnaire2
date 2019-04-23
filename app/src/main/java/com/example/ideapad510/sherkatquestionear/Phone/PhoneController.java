package com.example.ideapad510.sherkatquestionear.Phone;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;

import com.example.ideapad510.sherkatquestionear.Controller.Controller;
import com.example.ideapad510.sherkatquestionear.Params.Params;
import com.example.ideapad510.sherkatquestionear.Questions.QuestionActivity;
import com.example.ideapad510.sherkatquestionear.Questions.QuestionActivity2;

/**
 * Created by Ideapad 510 on 2/12/2019.
 */

public class PhoneController extends Controller{
    public PhoneController(Context context){
        super(context);
    }


    public void insertPhoneNumber(String phoneNumber){
        databaseInsertMethods.insertRowPhone(phoneNumber);

    }

    public boolean searchInPhone(String number){
        if (databaseOtherMethods.searchInPhone(number))
            return true;
        return false;

    }

    public void registerClick(EditText mobileNumber, Context context){
        String number = mobileNumber.getText().toString();
        Params params = Params.getInstance();

        if(!searchInPhone(number))
            insertPhoneNumber(number);
        params.setPasokhgoo(number);


        Intent intent = new Intent(context, QuestionActivity.class);
        params.setStarterActivity("phone");
        params.setResultStarterActivity("phone");
        context.startActivity(intent);

    }
}
