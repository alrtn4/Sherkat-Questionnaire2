package com.example.ideapad510.sherkatquestionear.New;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.ideapad510.sherkatquestionear.Result.AllResults;
import com.example.ideapad510.sherkatquestionear.Phone.Phone;
import com.example.ideapad510.sherkatquestionear.R;

public class New extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newbuttonlayout);
    }

    public void onNewClicked(View view){
        Intent intent = new Intent(New.this, Phone.class);
        startActivity(intent);
    }

    public void onResultClicked(View view){
        Intent intent = new Intent(New.this, AllResults.class);
        startActivity(intent);
    }
}
