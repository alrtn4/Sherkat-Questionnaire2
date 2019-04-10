package com.example.ideapad510.sherkatquestionear.Result;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.ideapad510.sherkatquestionear.Params.Params;
import com.example.ideapad510.sherkatquestionear.R;


public class AllResultsActivity extends AppCompatActivity  implements ResultListAdapter.Refresh {
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


        public void onRefresh() {
        String user = params.getUsername();

        listView = findViewById(R.id.resultList);
        ResultListAdapter saveListAdapter = new ResultListAdapter(this, resultController.getAllAllResults(user));
        listView.setAdapter(saveListAdapter);

    }
}
