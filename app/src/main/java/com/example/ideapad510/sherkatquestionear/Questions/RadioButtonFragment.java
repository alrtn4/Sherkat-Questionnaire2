package com.example.ideapad510.sherkatquestionear.Questions;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.ideapad510.sherkatquestionear.Answers.AnswerController;
import com.example.ideapad510.sherkatquestionear.Database.DatabaseInsertMethods;
import com.example.ideapad510.sherkatquestionear.Database.DatabaseOtherMethods;
import com.example.ideapad510.sherkatquestionear.Params.Params;
import com.example.ideapad510.sherkatquestionear.R;

import java.util.ArrayList;

public class RadioButtonFragment extends Fragment {

    public static final String USERNAME = "username";
    public static final String PORSESHNAME_ID = "porseshname-id";
    public static final String PAGE_NUMBER = "page-number";

    View view;
    private String username;
    private String porseshnameId;
    private int pageNumber;
    private AnswerController answerController;
    private QuestionController questionController;
    private Lists lists;
    private Params params = Params.getInstance();
    private int radioButtonsNumber;
    private String TAG = "RadioButtons";


    public static RadioButtonFragment getInstance(Bundle args){
        RadioButtonFragment radioButtonFragment = new RadioButtonFragment();

        radioButtonFragment.setArguments(args);
        return radioButtonFragment;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        view = layoutInflater.inflate(R.layout.radiobuttonfragment, container, false);
//        Log.d(TAG, "onCreateView: ");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        Bundle args = getArguments();
        this.username = args.getString(USERNAME);
        this.porseshnameId = args.getString(PORSESHNAME_ID);
        this.pageNumber = args.getInt(PAGE_NUMBER);

        answerController = new AnswerController(getContext());
        questionController = new QuestionController(getContext());
        lists = new Lists(getActivity(), pageNumber, getContext());

        refreshPage(pageNumber);
    }


    private void addRadioButtons(int number, ArrayList<String> answers, int pageNumber) {
        RadioGroup radioGroup = getActivity().findViewById(R.id.radioGroup);


        //this variable transfers number to another method
        radioButtonsNumber = number;

        //adds radioButtons equal to the given number
        for (int i = 1; i <= number; i++) {
            RadioButton rdbtn = new RadioButton(getContext());
            rdbtn.setId(i);
            rdbtn.setTextSize(15);

            RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT,
                    RadioGroup.LayoutParams.WRAP_CONTENT);
            rdbtn.setLayoutParams(lp);

            String questionId = String.valueOf(pageNumber+1);
            String answerId = String.valueOf(i);


            //if resultlistadapter(originally resultactivity) is the starter of questionactivity
            // then we must get pasokhgoo from it not
            //from the regular way
            String pasokhgoo;
            if(params.getStarterActivity().equals("adapter"))
                pasokhgoo = params.getAdapterPasokhgoo();
            else pasokhgoo = params.getPasokhgoo();

            //if the answer is registered in results gives it a different background color
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
        RadioGroup radioGroup = getActivity().findViewById(R.id.radioGroup);
        Log.d(TAG, "checkedListener: "+(radioGroup == null)+
                " "+(getActivity()== null));
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                cleaner();

                //using pagenumber as questionId
                String questionId = String.valueOf(pageNumber+1);
                String answerId = String.valueOf( checkedId);
                String pasokhgoo = params.getPasokhgoo();

                DatabaseOtherMethods databaseOtherMethods = new DatabaseOtherMethods(getContext());
                DatabaseInsertMethods databaseInsertMethods = new DatabaseInsertMethods(getContext());

                RadioButton radioButton = getActivity().findViewById(checkedId);
                //if the answer is not registered gives the radio button a different color and register it
                if(!questionController.searchInResult(porseshnameId, username, questionId, answerId, pasokhgoo)) {
                    answerController.insertToDatabase(questionId, answerId, porseshnameId, username, pasokhgoo);
                    radioButton.setBackgroundResource(R.drawable.rectangle2);
                }
                //if the answer is registered gives the radio button regular color and delete it from registered answers
                else {
                    databaseOtherMethods.deletSavedResult(porseshnameId, username, questionId, answerId, pasokhgoo);
                    radioButton.setBackgroundResource(R.drawable.rectangle7);
                }

            }
        });

    }



    public void refreshPage(int positionInQuestionList ){
        pageNumber = positionInQuestionList;

        getActivity().setContentView(R.layout.question2);
//        Log.d(TAG, "refreshPage: ");
        checkedListener();

        TextView questionText = getActivity().findViewById(R.id.questionTitle);
        TextView partNumberText = getActivity().findViewById(R.id.part);

        ArrayList<QuestionObject> questionObjectArray = lists.getQuestionArray(lists.getListOfQuestionTables());

        partNumberText.setText("PART : " + questionObjectArray.get(positionInQuestionList).getQuestionPart());
        questionText.setText((questionObjectArray.get(positionInQuestionList)).getQuestionText());

        ArrayList <String> answers = lists.findingAnswers(positionInQuestionList);
        addRadioButtons(answers.size(), answers, positionInQuestionList);


    }



    //this cleans the background of button before the new pressed button gets it background
    private void cleaner(){
        for(int i = 1; i <= radioButtonsNumber; i++){
            RadioButton radioButton = getActivity().findViewById(i);

            //using pagenumber as questionId
            String questionId = String.valueOf(pageNumber+1);
            String answerId = String.valueOf( i);
            String pasokhgoo = params.getPasokhgoo();

            DatabaseOtherMethods databaseOtherMethods = new DatabaseOtherMethods(getContext());

            if(questionController.searchInResult(porseshnameId, username, questionId, answerId, pasokhgoo)) {
                radioButton.setBackgroundResource(R.drawable.rectangle7);
                databaseOtherMethods.deletSavedResult(porseshnameId, username, questionId, answerId, pasokhgoo);
            }
        }
    }


}
