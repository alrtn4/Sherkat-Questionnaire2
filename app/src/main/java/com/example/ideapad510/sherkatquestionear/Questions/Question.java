package com.example.ideapad510.sherkatquestionear.Questions;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.ideapad510.sherkatquestionear.Database.Database;
import com.example.ideapad510.sherkatquestionear.Params.Params;
import com.example.ideapad510.sherkatquestionear.Questions.Answer.QuestionsAnswersArray;
import com.example.ideapad510.sherkatquestionear.R;
import com.example.ideapad510.sherkatquestionear.Result.Result;
import com.example.ideapad510.sherkatquestionear.Result.ResultController;

import java.util.ArrayList;

public class Question extends AppCompatActivity {
    private ArrayList<QuestionObject> questionObjectArray = new ArrayList<>();
    private int pageNumber = 0;
    private static final String TAG = "question";
    private String username;
    private String porseshnameId;
    private String pasokhgoo;

    private RadioButtons buttons;
    private CheckList checkList;
    private EditBox editBox;
    private Params params= Params.getInstance();
    private Lists lists = new Lists(this, pageNumber, this);
    private EditText editText;
    private ResultController resultController;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);


        username = params.getUsername();
        porseshnameId = params.getPorseshnameId();

        QuestionsAnswersArray questionsAnswersArray = new QuestionsAnswersArray();
        String answerType = questionsAnswersArray.get(pageNumber).getAnswerType();

        buttons = new RadioButtons(Question.this, this, username,
                porseshnameId, pageNumber);
        buttons.checkedListener();
        checkList = new CheckList(this, this, username, porseshnameId, pageNumber);
        editBox = new EditBox(this, this, username, porseshnameId, pageNumber);


        switch (answerType){
            case "RADIO":
                buttons.refreshPage(pageNumber);
                break;
            case "CHECK":
                checkList.refreshPage(pageNumber);
                break;
            case "TEXT":
                editBox.refreshPage(pageNumber);
                break;
        }



        Database db = Database.getInstance(this);


        //we need this for size of questions in onforwardclicked method
        questionObjectArray = lists.getQuestionArray(lists.getListOfQuestionTables());

        resultController = new ResultController(this);
        editText = findViewById(R.id.editText);
        pasokhgoo = params.getPasokhgoo();

    }

    @Override
    public void onResume(){
        super.onResume();

        QuestionsAnswersArray questionsAnswersArray = new QuestionsAnswersArray();
        String answerType = questionsAnswersArray.get(pageNumber).getAnswerType();

        switch (answerType){
            case "RADIO":
                buttons.refreshPage(pageNumber);
                break;
            case "CHECK":
                checkList.refreshPage(pageNumber);
                break;
            case "TEXT":
                editBox.refreshPage(pageNumber);
                break;
        }

    }



    public void onBackClicked(View view){
        if(!(pageNumber == 0)) {
            pageNumber--;

            QuestionsAnswersArray questionsAnswersArray = new QuestionsAnswersArray();
            String answerType = questionsAnswersArray.get(pageNumber).getAnswerType();

            switch (answerType){
                case "RADIO":
                    buttons.refreshPage(pageNumber);
                    break;
                case "CHECK":
                    checkList.refreshPage(pageNumber);
                    break;
                case "TEXT":
                    editBox.refreshPage(pageNumber);
                    break;
            }

        }
    }

    public void onForwardClicked(View view){
        if(pageNumber != questionObjectArray.size() - 1) {
            pageNumber++;

            QuestionsAnswersArray questionsAnswersArray = new QuestionsAnswersArray();
            String answerType = questionsAnswersArray.get(pageNumber).getAnswerType();

            switch (answerType){
                case "RADIO":
                    buttons.refreshPage(pageNumber);
                    break;
                case "CHECK":
                    checkList.refreshPage(pageNumber);
                    break;
                case "TEXT":
                    editBox.refreshPage(pageNumber);
                    break;
            }

        }
    }

    public  void onDoneClicked(View view){
        Intent intent = new Intent(Question.this, Result.class);
        intent.putExtra("user",username);
        startActivity(intent);
    }

    public void onRegisterClick(View view){
        editText = findViewById(R.id.editText);
        String answer = editText.getText().toString();
        Log.d(TAG, "onRegisterClick: "+answer);

        if(resultController.searchInResultWithoutAnswer(porseshnameId, username,
                (pageNumber+1)+"", pasokhgoo))
            resultController.deletSavedResultWithoutAnswer(porseshnameId, username,
                    (pageNumber+1)+"", pasokhgoo);

        resultController.insertToDatabase((pageNumber+1)+"", answer, porseshnameId,
                username, pasokhgoo);
    }



}
