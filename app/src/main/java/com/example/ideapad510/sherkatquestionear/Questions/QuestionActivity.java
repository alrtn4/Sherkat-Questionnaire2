package com.example.ideapad510.sherkatquestionear.Questions;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.ideapad510.sherkatquestionear.Answers.AllAnswersActivity;
import com.example.ideapad510.sherkatquestionear.Answers.AnswerController;
import com.example.ideapad510.sherkatquestionear.Error.ErrorFinder;
import com.example.ideapad510.sherkatquestionear.Login.CustomToast;
import com.example.ideapad510.sherkatquestionear.Params.Params;
import com.example.ideapad510.sherkatquestionear.Questions.Answer.QuestionsAnswersArray;
import com.example.ideapad510.sherkatquestionear.R;
import com.example.ideapad510.sherkatquestionear.Answers.AnswerActivity;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {
    private ArrayList<QuestionObject> questionObjectArray = new ArrayList<>();
    private int pageNumber;
    private static final String TAG = "question";
    private String username;
    private String porseshnameId;
    private String pasokhgoo;
    private RadioButtons radioButtons;
    private CheckList checkList;
    private EditBox editBox;
    private Params params= Params.getInstance();
    private Lists lists ;
    private EditText editText;
    private AnswerController answerController;
    private boolean lastPageReached = false;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);

        //this is for backbuttonpressed in answeractivity
        params.setBundle(savedInstanceState);


        setParams();


        initializeViews();


    }

    @Override
    public void onResume(){
        super.onResume();

        QuestionsAnswersArray questionsAnswersArray = new QuestionsAnswersArray();
        String answerType = questionsAnswersArray.get(pageNumber).getAnswerType();

        switch (answerType){
            case "RADIO":
                radioButtons.refreshPage(pageNumber);
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

//            QuestionsAnswersArray questionsAnswersArray = new QuestionsAnswersArray();
            String answerType = (new QuestionsAnswersArray()).get(pageNumber).getAnswerType();

            switch (answerType){
                case "RADIO":
                    radioButtons.refreshPage(pageNumber);
                    break;
                case "CHECK":
                    checkList.refreshPage(pageNumber);
                    break;
                case "TEXT":
                    editBox.refreshPage(pageNumber);
                    break;
            }

        }

        ErrorFinder ef = new ErrorFinder();

        if(ef.firstQuestion(pageNumber))
            new CustomToast().Show_Toast(this, findViewById(android.R.id.content), "این اولین سوال است");


    }

    public void onForwardClicked(View view){


        //we use lastPageReached variable for ensuring that we are pressing the forward button
        //when we have reached the last page before and can't go further
        if(lastPageReached & (pageNumber == questionObjectArray.size() -1))
            new CustomToast().Show_Toast(this, findViewById(android.R.id.content),
                    "این آخرین سوال است");


        if(pageNumber != questionObjectArray.size() - 1) {

            lastPageReached = false;

            pageNumber++;

            if(pageNumber == questionObjectArray.size() -1)
                lastPageReached = true;


            QuestionsAnswersArray questionsAnswersArray = new QuestionsAnswersArray();
            String answerType = questionsAnswersArray.get(pageNumber).getAnswerType();

            switch (answerType){
                case "RADIO":
                    radioButtons.refreshPage(pageNumber);
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
        Intent intent;
        if(params.getResultStarterActivity().equals("new")) {
            intent = new Intent(QuestionActivity.this, AllAnswersActivity.class);
            finish();
        }
        else{
            intent = new Intent(QuestionActivity.this, AnswerActivity.class);
            finish();
        }
        startActivity(intent);
    }

    public void onRegisterClick(View view){
        editText = findViewById(R.id.editText);
        String answer = editText.getText().toString();

        if(answerController.searchInResultWithoutAnswer(porseshnameId, username,
                (pageNumber+1)+"", pasokhgoo))
            answerController.deletSavedResultWithoutAnswer(porseshnameId, username,
                    (pageNumber+1)+"", pasokhgoo);

        answerController.insertToDatabase((pageNumber+1)+"", answer, porseshnameId,
                username, pasokhgoo);
    }


    private void setParams(){
        username = params.getUsername();
        porseshnameId = params.getPorseshnameId();


        //this condition shows that result activity has started question activity
        //because if it has started , adapterpagenumber must have a valid value
        if(params.getStarterActivity().equals("adapter")){
            pageNumber = params.getAdapterPageNumber();
            porseshnameId = params.getAdapterPorseshnameId();
            pasokhgoo = params.getAdapterPasokhgoo();

            if(pasokhgoo == null)
                pasokhgoo = "";
        }

    }



    private void initializeViews(){
        lists = new Lists(this, pageNumber, this);


        QuestionsAnswersArray questionsAnswersArray = new QuestionsAnswersArray();
        String answerType = questionsAnswersArray.get(pageNumber).getAnswerType();

        radioButtons = new RadioButtons(QuestionActivity.this, this, username,
                porseshnameId, pageNumber);
        radioButtons.checkedListener();
        checkList = new CheckList(this, this, username, porseshnameId, pageNumber);
        editBox = new EditBox(this, this, username, porseshnameId, pageNumber);


        switch (answerType){
            case "RADIO":
                radioButtons.refreshPage(pageNumber);
                break;
            case "CHECK":
                checkList.refreshPage(pageNumber);
                break;
            case "TEXT":
                editBox.refreshPage(pageNumber);
                break;
        }


        //for not showing onscreen keybord for edittext in start of activity
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);



        //we need this for size of questions in onforwardclicked method
        questionObjectArray = lists.getQuestionArray(lists.getListOfQuestionTables());

        answerController = new AnswerController(this);
        editText = findViewById(R.id.editText);
        pasokhgoo = params.getPasokhgoo();

    }

}
