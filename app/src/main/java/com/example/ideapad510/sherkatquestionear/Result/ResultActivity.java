package com.example.ideapad510.sherkatquestionear.Result;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.ideapad510.sherkatquestionear.Params.Params;
import com.example.ideapad510.sherkatquestionear.R;


public class ResultActivity extends AppCompatActivity implements ResultListAdapter.Refresh {
    static public ListView listView;
    ResultController resultController = new ResultController(this);
    String TAG = "Result";
    Params params = Params.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        String user = params.getUsername();
        String pasokhgoo = params.getPasokhgoo();

        listView = findViewById(R.id.resultList);
        ResultListAdapter saveListAdapter = new ResultListAdapter(this, resultController.getAllResults(user, pasokhgoo));
        listView.setAdapter(saveListAdapter);

    }


    @Override
    public void onRefresh() {
        String user = params.getUsername();
        String pasokhgoo = params.getPasokhgoo();

        listView = findViewById(R.id.resultList);
        ResultListAdapter saveListAdapter = new ResultListAdapter(this, resultController.getAllResults(user, pasokhgoo));
        listView.setAdapter(saveListAdapter);

    }
}
