package com.example.ideapad510.sherkatquestionear.Result;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.ideapad510.sherkatquestionear.Params.Params;
import com.example.ideapad510.sherkatquestionear.R;


public class ResultActivity extends AppCompatActivity {//implements ResultListAdapter.OnClickListener {
    ListView listView;
    ResultController resultController = new ResultController(this);
    String TAG = "REsult";
    Params params = Params.getInstance();
    int listPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        String user = params.getUsername();
        String pasokhgoo = params.getPasokhgoo();

        listView = findViewById(R.id.resultList);
        ResultListAdapter saveListAdapter = new ResultListAdapter(this, resultController.getAllResults(user, pasokhgoo));
        listView.setAdapter(saveListAdapter);

        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
/*        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                params.setListPosition(position);

            }
        });
*/
    }
/*
    @Override
    public void onClick(View v) {

    }
*/
}
