package com.example.ideapad510.sherkatquestionear.Phone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ideapad510.sherkatquestionear.Params.Params;
import com.example.ideapad510.sherkatquestionear.Questions.QuestionActivity;
import com.example.ideapad510.sherkatquestionear.R;

import java.util.regex.Pattern;


public class PhoneActivity extends AppCompatActivity {
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



        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; ++i)
                {
                    if (!Pattern.compile("[1234567890]*").matcher(String.valueOf(source.charAt(i))).matches())
                    {
                        return "";
                    }
                }

                return null;
            }
        };

        EditText mobileNumber = findViewById(R.id.mobileNumber);
        mobileNumber.setFilters(new InputFilter[]{filter, new InputFilter.LengthFilter(11)});



    }

    public void onRegisterClick(View view){
        EditText mobileNumber = findViewById(R.id.mobileNumber);
        String number = mobileNumber.getText().toString();
//        System.out.println(number);
        if(!phoneController.searchInPhone(number))
            phoneController.insertPhoneNumber(number);
        params.setPasokhgoo(number);

//        params.setAdapterPageNumber(0);

        Intent intent = new Intent(PhoneActivity.this, QuestionActivity.class);
//        intent.putExtra("starterActivity", "phone");
        params.setStarterActivity("phone");
        params.setResultStarterActivity("phone");
        startActivity(intent);
    }


}
