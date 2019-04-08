package com.example.ideapad510.sherkatquestionear.Questions;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.ideapad510.sherkatquestionear.Database.Database;
import com.example.ideapad510.sherkatquestionear.Database.DatabaseInsertMethods;
import com.example.ideapad510.sherkatquestionear.Database.DatabaseOtherMethods;
import com.example.ideapad510.sherkatquestionear.Params.Params;
import com.example.ideapad510.sherkatquestionear.Questions.Answer.AnswerController;
import com.example.ideapad510.sherkatquestionear.R;
import com.example.ideapad510.sherkatquestionear.Result.ResultController;
//import com.example.ideapad510.sherkatquestionear.Save.SaveResult;

import java.util.ArrayList;

/**
 * Created by Ideapad 510 on 2/7/2019.
 */

public class RadioButtons {
    Activity activity;
    Context context;
    String username;
    String porseshnameId;
    int pageNumber;
    ResultController resultController;
    Database db;
    QuestionController questionController;
    AnswerController answerController;
    Lists lists;
    Params params = Params.getInstance();
    int radioButtonsNumber;
    String TAG = "RadioButtons";

    public RadioButtons(Activity activity, Context context, String username, String porseshnameId,
                        int pageNumber ){
        this.activity = activity;
        this.context = context;
        this.username = username;
        this.porseshnameId = porseshnameId;
        this.pageNumber = pageNumber;
        resultController = new ResultController(context);
        db = Database.getInstance(context);
        questionController = new QuestionController(context);
        answerController = new AnswerController(context);
        lists = new Lists(activity, pageNumber, context);
    }



    public void addRadioButtons(int number, ArrayList<String> answers, int pageNumber) {
        RadioGroup radioGroup = activity.findViewById(R.id.radioGroup);

        radioGroup.setVisibility(View.VISIBLE);

        radioButtonsNumber = number;

        //adds radioButtons equal to the given number
        for (int i = 1; i <= number; i++) {
            RadioButton rdbtn = new RadioButton(context);
            rdbtn.setId(i);
            rdbtn.setTextSize(15);

            RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT);
            rdbtn.setLayoutParams(lp);

            String questionId = String.valueOf(pageNumber+1);
            String answerId = String.valueOf(i);
            String pasokhgoo = params.getPasokhgoo();

            //if the answer is registered in results give it a different background color
            if(questionController.searchInResult(porseshnameId, username, questionId, answerId, pasokhgoo)) {
                rdbtn.setBackgroundResource(R.drawable.rectangle2);
            }
            else
                rdbtn.setBackgroundResource(R.drawable.rectangle7);

            rdbtn.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            rdbtn.setText(answers.get(i - 1));
            radioGroup.addView(rdbtn);
        }
    }


    public void checkedListener(){
        final RadioGroup radioGroup = activity.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            cleaner();

            //using pagenumber as questionId
            String questionId = String.valueOf(pageNumber+1);
            String answerId = String.valueOf( checkedId);
            String pasokhgoo = params.getPasokhgoo();

            DatabaseOtherMethods databaseSearchMethods = new DatabaseOtherMethods(context);
            DatabaseInsertMethods databaseInsertMethods = new DatabaseInsertMethods(context);

            RadioButton radioButton = activity.findViewById(checkedId);
            //if the answer is not registered gives the radio button a different color and register it
            if(!questionController.searchInResult(porseshnameId, username, questionId, answerId, pasokhgoo)) {
                resultController.insertToDatabase(questionId, answerId, porseshnameId, username, pasokhgoo);
                radioButton.setBackgroundResource(R.drawable.rectangle2);
            }
            //if the answer is registered gives the radio button regular color and delete it from registered answers
            else {
                databaseSearchMethods.deletSavedResult(porseshnameId, username, questionId, answerId, pasokhgoo);
                radioButton.setBackgroundResource(R.drawable.rectangle7);
            }

            }
        });

    }

    //refreshes the text views and redraws radio buttons based on the question position i.e question number
    //in this app question number depends on page number in question activity
    public void refreshPage(int positionInQuestionList ){
        pageNumber = positionInQuestionList;

        activity.setContentView(R.layout.question);
        checkedListener();

        TextView questionText = activity.findViewById(R.id.questionTitle);
        TextView partNumberText = activity.findViewById(R.id.part);

        ArrayList<QuestionObject> questionObjectArray = lists.getQuestionArray(lists.getListOfQuestionTables());

        partNumberText.setText("PART : " + questionObjectArray.get(positionInQuestionList).getQuestionPart());
        questionText.setText((questionObjectArray.get(positionInQuestionList)).getQuestionText());

        ArrayList <String> answers = lists.findingAnswers(positionInQuestionList);
        addRadioButtons(answers.size(), answers, positionInQuestionList);

        ScrollView scrollView = activity.findViewById(R.id.editScroll);
        scrollView.setVisibility(View.GONE);
        ScrollView scrollView1 = activity.findViewById(R.id.scrollView);
        scrollView1.setVisibility(View.VISIBLE);
        ScrollView scrollView2 = activity.findViewById(R.id.scrollView2);
        scrollView2.setVisibility(View.GONE);

    }



    private void cleaner(){
        for(int i = 1; i <= radioButtonsNumber; i++){
            RadioButton radioButton = activity.findViewById(i);

            //using pagenumber as questionId
            String questionId = String.valueOf(pageNumber+1);
            String answerId = String.valueOf( i);
            String pasokhgoo = params.getPasokhgoo();

            DatabaseOtherMethods databaseOtherMethods = new DatabaseOtherMethods(context);

            if(questionController.searchInResult(porseshnameId, username, questionId, answerId, pasokhgoo)) {
                radioButton.setBackgroundResource(R.drawable.rectangle7);
                databaseOtherMethods.deletSavedResult(porseshnameId, username, questionId, answerId, pasokhgoo);
            }
        }
    }



}
