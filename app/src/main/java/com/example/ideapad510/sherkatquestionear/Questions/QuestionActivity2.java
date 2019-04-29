package com.example.ideapad510.sherkatquestionear.Questions;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ideapad510.sherkatquestionear.Answers.AllAnswersActivity;
import com.example.ideapad510.sherkatquestionear.Answers.AnswerActivity;
import com.example.ideapad510.sherkatquestionear.Answers.AnswerController;
import com.example.ideapad510.sherkatquestionear.Params.Params;
import com.example.ideapad510.sherkatquestionear.Questions.Answer.QuestionsAnswersArray;
import com.example.ideapad510.sherkatquestionear.R;

import java.util.ArrayList;

public class QuestionActivity2 extends AppCompatActivity {
    private ArrayList<QuestionObject> questionObjectArray = new ArrayList<>();
    private int pageNumber;
    private static final String TAG = "question2";
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
    private FragmentManager fragmentManager;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question2);

        //this is for backbuttonpressed in answeractivity
        params.setBundle(savedInstanceState);


        fragmentManager = getSupportFragmentManager();


        Log.d(TAG, "onCreate: ");
        System.out.println("hiiiiiiiiiiii");
/*
        if(savedInstanceState == null){
            fragmentManager.beginTransaction().replace(R.id.frameContainer,
                    new EditTextFragment(), "EditTextFragment").commit();
        }
*/

//        if(savedInstanceState == null){
/*
            fragmentManager.beginTransaction().add(R.id.frameContainer,
                    new EditTextFragment()).commit();
*/
//        }

        setParams();


        initializeViews();

        refreshViews(pageNumber);

    }

    @Override
    public void onResume(){
        super.onResume();

        QuestionsAnswersArray questionsAnswersArray = new QuestionsAnswersArray();
        String answerType = questionsAnswersArray.get(pageNumber).getAnswerType();

/*
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
*/

        refreshViews(pageNumber);

    }



    public void onBackClicked(View view){
        if(!(pageNumber == 0)) {
            pageNumber--;

//            QuestionsAnswersArray questionsAnswersArray = new QuestionsAnswersArray();
            String answerType = (new QuestionsAnswersArray()).get(pageNumber).getAnswerType();

/*
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
*/

        refreshViews(pageNumber);

        }
    }

    public void onForwardClicked(View view){
        if(pageNumber != questionObjectArray.size() - 1) {

            pageNumber++;

            QuestionsAnswersArray questionsAnswersArray = new QuestionsAnswersArray();
            String answerType = questionsAnswersArray.get(pageNumber).getAnswerType();

/*
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
*/

        refreshViews(pageNumber);

        }
    }

    public  void onResultClicked(View view){
        Intent intent;
        if(params.getResultStarterActivity().equals("new")) {
            intent = new Intent(QuestionActivity2.this, AllAnswersActivity.class);
            finish();
        }
        else{
            intent = new Intent(QuestionActivity2.this, AnswerActivity.class);
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

        radioButtons = new RadioButtons(QuestionActivity2.this, this, username,
                porseshnameId, pageNumber);
/*
        radioButtons.checkedListener();
        checkList = new CheckList(this, this, username, porseshnameId, pageNumber);
        editBox = new EditBox(this, this, username, porseshnameId, pageNumber);
*/


/*
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
*/


        //for not showing onscreen keybord for edittext in start of activity
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);



        //we need this for size of questions in onforwardclicked method
        questionObjectArray = lists.getQuestionArray(lists.getListOfQuestionTables());

        answerController = new AnswerController(this);
        editText = findViewById(R.id.editText);
        pasokhgoo = params.getPasokhgoo();

    }



    private void refreshViews(int pageNumber){

        setContentView(R.layout.question2);

        TextView questionText = findViewById(R.id.questionTitle);
        TextView partNumberText = findViewById(R.id.part);

        ArrayList<QuestionObject> questionObjectArray = lists.getQuestionArray(lists.getListOfQuestionTables());

        partNumberText.setText("PART : " + questionObjectArray.get(pageNumber).getQuestionPart());
        questionText.setText((questionObjectArray.get(pageNumber)).getQuestionText());


        String answerType = (new QuestionsAnswersArray()).get(pageNumber).getAnswerType();

        Log.d(TAG, "refreshViews: ");

        refreshFragment(answerType, pageNumber);

    }


    private void refreshFragment(String answerType, int pageNumber){
        Log.d(TAG, "refreshFragment: ");
        Bundle args;

        switch (answerType){
            case "RADIO":
                args = new Bundle();
                args.putInt(RadioButtonFragment.PAGE_NUMBER, pageNumber);
                args.putString(RadioButtonFragment.PORSESHNAME_ID, porseshnameId);
                args.putString(RadioButtonFragment.USERNAME, username);

                fragmentManager.beginTransaction()
//                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer, RadioButtonFragment.getInstance(args)).commit();

                break;
            case "CHECK":
                args = new Bundle();
                args.putInt(CheckBoxFragment.PAGE_NUMBER, pageNumber);
                args.putString(CheckBoxFragment.PORSESHNAME_ID, porseshnameId);
                args.putString(CheckBoxFragment.USERNAME, username);

                fragmentManager.beginTransaction()
//                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer, CheckBoxFragment.getInstance(args)).commit();
                break;
            case "TEXT":
                args = new Bundle();
                args.putInt(EditTextFragment.PAGE_NUMBER, pageNumber);


                fragmentManager.beginTransaction()
//                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
                        .replace(R.id.frameContainer, EditTextFragment.getInstance(args)).commit();
                break;
        }

    }

}
