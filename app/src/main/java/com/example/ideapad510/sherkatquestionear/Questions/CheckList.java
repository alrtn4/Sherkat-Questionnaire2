package com.example.ideapad510.sherkatquestionear.Questions;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.ideapad510.sherkatquestionear.Database.DatabaseOtherMethods;
import com.example.ideapad510.sherkatquestionear.Params.Params;
import com.example.ideapad510.sherkatquestionear.R;
import com.example.ideapad510.sherkatquestionear.Answers.AnswerController;

import java.util.ArrayList;

public class CheckList {


    private Activity activity;
    private Context context;
    private String username;
    private String porseshnameId;
    private int pageNumber;
    private AnswerController answerController;
//    private Database db;
    private QuestionController questionController;
//    private AnswerDatabaseController answerController;
    private Lists lists;
    private Params params = Params.getInstance();
    private String TAG = "checklist";

    public CheckList(Activity activity, Context context, String username, String porseshnameId,
                        int pageNumber ){
        this.activity = activity;
        this.context = context;
        this.username = username;
        this.porseshnameId = porseshnameId;
        this.pageNumber = pageNumber;
        answerController = new AnswerController(context);
        questionController = new QuestionController(context);
        lists = new Lists(activity, pageNumber, context);
    }


    //draws the checkboxes
    private void addCheckBoxes(int number, ArrayList<String> answers, int pageNumber) {
        LinearLayout checkboxContainer = activity.findViewById(R.id.checkboxContainer);


        //draws checkBoxes equal to the given number
        for (int i = 1; i <= number; i++) {
            final CheckBox checkBox = new CheckBox(context);
            checkBox.setId(i);
            checkBox.setTextSize(15);


            setChecked(checkBox, pageNumber, i);
            setListener(checkBox);


            checkBox.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            checkBox.setText(answers.get(i - 1));
            checkboxContainer.addView(checkBox);


        }

        params.setPageNumber(pageNumber);
    }




    //refreshes the text views and redraws checkboxes based on the question position i.e question number
    //in this app question number depends on page number in question activity
    public void refreshPage(int positionInQuestionList ){
        pageNumber = positionInQuestionList;

        activity.setContentView(R.layout.question);

        TextView questionText = activity.findViewById(R.id.questionTitle);
        TextView partNumberText = activity.findViewById(R.id.part);

        ArrayList<QuestionObject> questionObjectArray = lists.getQuestionArray(lists.getListOfQuestionTables());

        partNumberText.setText("PART : " + questionObjectArray.get(positionInQuestionList).getQuestionPart());
        questionText.setText((questionObjectArray.get(positionInQuestionList)).getQuestionText());

        ArrayList <String> answers = lists.findingAnswers(positionInQuestionList);
        //refreshes the checkboxes by redrawing them
        addCheckBoxes(answers.size(), answers, positionInQuestionList);

        //sets other two views visibility to gone so that checklist can be seen
        ScrollView scrollView = activity.findViewById(R.id.editScroll);
        scrollView.setVisibility(View.GONE);
        ScrollView scrollView1 = activity.findViewById(R.id.scrollView);
        scrollView1.setVisibility(View.GONE);
        ScrollView scrollView2 = activity.findViewById(R.id.scrollView2);
        scrollView2.setVisibility(View.VISIBLE);


    }



    private void setListener(final CheckBox checkBox){
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //using pagenumber as questionId
                String questionId = String.valueOf(params.getPageNumber()+1);
                String answerId = String.valueOf( checkBox.getId());
                String pasokhgoo = params.getPasokhgoo();

                //if pasokhgoo doesn't have a value yet
                if(pasokhgoo == null)
                    pasokhgoo = "";

                DatabaseOtherMethods databaseOtherMethods = new DatabaseOtherMethods(context);


/*
                Log.d(TAG, "onCheckedChanged: "+porseshnameId+" "+username+" "+questionId+" "+
                        answerId+" "+pasokhgoo);
*/

                //if the answer is not registered gives the checkbox a different color and register it
                if(!questionController.searchInResult(porseshnameId, username, questionId, answerId, pasokhgoo)) {
                    answerController.insertToDatabase(questionId, answerId, porseshnameId, username, pasokhgoo);
                    checkBox.setBackgroundResource(R.drawable.rectangle2);
                }
                //if the answer is registered gives the checkbox regular color and delete it from registered answers
                else {
                    databaseOtherMethods.deletSavedResult(porseshnameId, username, questionId, answerId, pasokhgoo);
                    checkBox.setBackgroundResource(R.drawable.rectangle7);
                }

            }
        });

    }


    private void setChecked(CheckBox checkBox, int pageNumber, int i){
        String questionId = String.valueOf(pageNumber+1);
        String answerId = String.valueOf(i);

        //if resultlistadapter is the starter of questionactivity then we must get pasokhgoo from it not
        //from the regular way
        String pasokhgoo;
        if(params.getStarterActivity().equals("adapter"))
            pasokhgoo = params.getAdapterPasokhgoo();
        else pasokhgoo = params.getPasokhgoo();


        //if the answer is registered in results gives it a different background color
        if(questionController.searchInResult(porseshnameId, username, questionId, answerId, pasokhgoo)) {
            checkBox.setChecked(true);
            checkBox.setBackgroundResource(R.drawable.rectangle2);

        }
        else {
            checkBox.setChecked(false);
            checkBox.setBackgroundResource(R.drawable.rectangle7);
        }

    }


}
