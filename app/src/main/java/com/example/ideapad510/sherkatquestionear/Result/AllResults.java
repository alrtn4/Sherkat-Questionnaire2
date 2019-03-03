package com.example.ideapad510.sherkatquestionear.Result;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.ideapad510.sherkatquestionear.Params.Params;
import com.example.ideapad510.sherkatquestionear.R;
import com.example.ideapad510.sherkatquestionear.Result.ResultController;
import com.example.ideapad510.sherkatquestionear.Result.ResultListAdapter;


public class AllResults extends Activity{
    ListView listView;
    ResultController resultController = new ResultController(this);
    String TAG = "AllResult";
    Params params = Params.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        String user = params.getUsername();

        listView = findViewById(R.id.resultList);
        ResultListAdapter saveListAdapter = new ResultListAdapter(this, resultController.getAllAllResults(user));
        listView.setAdapter(saveListAdapter);

    }


}
