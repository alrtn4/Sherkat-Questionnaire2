package com.example.ideapad510.sherkatquestionear.Phone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ideapad510.sherkatquestionear.Params.Params;
import com.example.ideapad510.sherkatquestionear.Questions.Question;
import com.example.ideapad510.sherkatquestionear.R;


public class Phone extends Activity {
    PhoneController phoneController = new PhoneController(this);
    Params params = Params.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phone);


        ((EditText) findViewById(R.id.mobileNumber)).setOnEditorActionListener(new EditText.OnEditorActionListener(){
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    onRegisterClick(v);
                    return true;
                }
                return false;
            }
        });
    }

    public void onRegisterClick(View view){
        EditText mobileNumber = findViewById(R.id.mobileNumber);
        String number = mobileNumber.getText().toString();
        if(!phoneController.searchInPhone(number))
            phoneController.insertPhoneNumber(number);
        params.setPasokhgoo(number);

        Intent intent = new Intent(Phone.this, Question.class);
        startActivity(intent);
    }


}
