package com.example.ideapad510.sherkatquestionear.New;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.ideapad510.sherkatquestionear.Result.AllResults;
import com.example.ideapad510.sherkatquestionear.Phone.PhoneActivity;
import com.example.ideapad510.sherkatquestionear.R;

public class NewActivity extends AppCompatActivity {
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
        Intent intent = new Intent(NewActivity.this, AllResults.class);
        startActivity(intent);
    }
}
