package com.example.ideapad510.sherkatquestionear.Questions;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.ideapad510.sherkatquestionear.Database.Database;
import com.example.ideapad510.sherkatquestionear.Params.Params;
import com.example.ideapad510.sherkatquestionear.Questions.Answer.QuestionsAnswersArray;
import com.example.ideapad510.sherkatquestionear.R;
import com.example.ideapad510.sherkatquestionear.Result.AllResultsActivity;
import com.example.ideapad510.sherkatquestionear.Result.ResultActivity;
import com.example.ideapad510.sherkatquestionear.Result.ResultController;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {
    private ArrayList<QuestionObject> questionObjectArray = new ArrayList<>();
    private int pageNumber;
    private static final String TAG = "question";
    private String username;
    private String porseshnameId;
    private String pasokhgoo;

    private RadioButtons buttons;
    private CheckList checkList;
    private EditBox editBox;
    private Params params= Params.getInstance();
    private Lists lists ;
    private EditText editText;
    private ResultController resultController;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);


        username = params.getUsername();
        porseshnameId = params.getPorseshnameId();


        //this condition shows that result activity has started question activity
        //because if it has started , adapterpagenumber must have a valid value
//        if(getIntent().getStringExtra("starterActivity").equals("adapter")) {
        if(params.getStarterActivity().equals("adapter")){
            pageNumber = params.getAdapterPageNumber();
            porseshnameId = params.getAdapterPorseshnameId();
            pasokhgoo = params.getAdapterPasokhgoo();

            if(pasokhgoo == null)
                pasokhgoo = "";
        }

        Log.d(TAG, "onCreate: "+params.getAdapterPageNumber()+" "+pageNumber+" "+porseshnameId+" "+pasokhgoo);
//        Log.d(TAG, "onCreate: "+pasokhgoo.equals(""));

        lists = new Lists(this, pageNumber, this);


        QuestionsAnswersArray questionsAnswersArray = new QuestionsAnswersArray();
        String answerType = questionsAnswersArray.get(pageNumber).getAnswerType();

        buttons = new RadioButtons(QuestionActivity.this, this, username,
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


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


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

    public  void onResultClicked(View view){
//        Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
//        intent.putExtra("user",username);
        Intent intent;
        if(params.getResultStarterActivity().equals("new")) {
            intent = new Intent(QuestionActivity.this, AllResultsActivity.class);
            finish();
        }
        else{
            intent = new Intent(QuestionActivity.this, ResultActivity.class);
            finish();
        }
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
