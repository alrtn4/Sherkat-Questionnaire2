package com.example.ideapad510.sherkatquestionear.Answers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.ideapad510.sherkatquestionear.Params.Params;
import com.example.ideapad510.sherkatquestionear.R;


public class AllAnswersActivity extends AppCompatActivity  implements AnswerListAdapter.Refresh {
    ListView listView;
    AnswerController answerController = new AnswerController(this);
    String TAG = "AllResult";
    Params params = Params.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        String user = params.getUsername();

        listView = findViewById(R.id.resultList);
        AnswerListAdapter saveListAdapter = new AnswerListAdapter(this, answerController.getAllAllResults(user));
        listView.setAdapter(saveListAdapter);

    }


        public void onRefresh() {
        String user = params.getUsername();

        listView = findViewById(R.id.resultList);
        AnswerListAdapter saveListAdapter = new AnswerListAdapter(this, answerController.getAllAllResults(user));
        listView.setAdapter(saveListAdapter);

    }
}
