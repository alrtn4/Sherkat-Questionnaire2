package com.example.ideapad510.sherkatquestionear.New;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.ideapad510.sherkatquestionear.Params.Params;
import com.example.ideapad510.sherkatquestionear.Result.AllResultsActivity;
import com.example.ideapad510.sherkatquestionear.Phone.PhoneActivity;
import com.example.ideapad510.sherkatquestionear.R;

public class NewActivity extends AppCompatActivity {

    Params params = Params.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newbuttonlayout);
    }

    public void onNewClicked(View view){
        Intent intent = new Intent(NewActivity.this, PhoneActivity.class);
        startActivity(intent);
    }

    public void onResultClicked(View view){
        Intent intent = new Intent(NewActivity.this, AllResultsActivity.class);
        startActivity(intent);

        params.setResultStarterActivity("new");
    }
}
