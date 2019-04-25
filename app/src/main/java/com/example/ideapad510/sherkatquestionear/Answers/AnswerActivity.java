package com.example.ideapad510.sherkatquestionear.Answers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;

import com.example.ideapad510.sherkatquestionear.Params.Params;
import com.example.ideapad510.sherkatquestionear.Phone.PhoneController;
import com.example.ideapad510.sherkatquestionear.Questions.QuestionActivity;
import com.example.ideapad510.sherkatquestionear.Questions.QuestionActivity2;
import com.example.ideapad510.sherkatquestionear.R;


public class AnswerActivity extends AppCompatActivity implements AnswerListAdapter.Refresh {
    private ListView listView;
    private AnswerController answerController = new AnswerController(this);
    private String TAG = "Result";
    private Params params = Params.getInstance();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        String user = params.getUsername();
        String pasokhgoo = params.getPasokhgoo();

        listView = findViewById(R.id.resultList);
        AnswerListAdapter saveListAdapter = new AnswerListAdapter(this, answerController.getAllResults(user, pasokhgoo));
        listView.setAdapter(saveListAdapter);

    }


    @Override
    public void onRefresh() {
        String user = params.getUsername();
        String pasokhgoo = params.getPasokhgoo();

        listView = findViewById(R.id.resultList);
        AnswerListAdapter saveListAdapter = new AnswerListAdapter(this, answerController.getAllResults(user, pasokhgoo));
        listView.setAdapter(saveListAdapter);

    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();

//        PhoneController phoneController = new PhoneController(this);
//        phoneController.registerClick( params.getMobileNumber(), this);
        Intent intent = new Intent(this, QuestionActivity2.class);
        intent.putExtra("bundle", params.getBundle());
        startActivity(intent);
    }

}
