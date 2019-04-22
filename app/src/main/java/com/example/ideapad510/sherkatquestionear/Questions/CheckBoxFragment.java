package com.example.ideapad510.sherkatquestionear.Questions;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.ideapad510.sherkatquestionear.Answers.AnswerController;
import com.example.ideapad510.sherkatquestionear.Database.DatabaseOtherMethods;
import com.example.ideapad510.sherkatquestionear.Params.Params;
import com.example.ideapad510.sherkatquestionear.R;

import java.util.ArrayList;

public class CheckBoxFragment extends Fragment {

    public static final String PAGE_NUMBER = "page-number";
    public static final String USERNAME = "username";
    public static final String PORSESHNAME_ID = "porseshname-id";

    private View view;
    private Params params = Params.getInstance();
    private QuestionController questionController = new QuestionController(getContext());
    private AnswerController answerController = new AnswerController(getContext());
    private String porseshnameId;
    private String username;
    private int pageNumber;
    private Lists lists;

    public static CheckBoxFragment getInstance(Bundle args){
        CheckBoxFragment checkBoxFragment = new CheckBoxFragment();

        checkBoxFragment.setArguments(args);


        return checkBoxFragment;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        view = layoutInflater.inflate(R.layout.checkboxfragment, container, false);

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

    //draws the checkboxes
    private void addCheckBoxes(int number, ArrayList<String> answers, int pageNumber) {
        LinearLayout checkboxContainer = view.findViewById(R.id.checkboxContainer);


        //draws checkBoxes equal to the given number
        for (int i = 1; i <= number; i++) {
            final CheckBox checkBox = new CheckBox(getContext());
            checkBox.setId(i);
            checkBox.setTextSize(15);

            //set checkboxes which are in database to check and then sets the listener of each checkbox
            setChecked(checkBox, pageNumber, i);
            setListener(checkBox);


            checkBox.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            checkBox.setText(answers.get(i - 1));
            checkboxContainer.addView(checkBox);


        }

        params.setPageNumber(pageNumber);
    }



    //checks which checkboxes are checked by looking in database
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

                DatabaseOtherMethods databaseOtherMethods = new DatabaseOtherMethods(getContext());


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



    //refreshes the text views and redraws checkboxes based on the question position i.e question number
    //in this app question number depends on page number in question activity
    public void refreshPage(int positionInQuestionList ){
        pageNumber = positionInQuestionList;

        getActivity().setContentView(R.layout.question2);

        TextView questionText = getActivity().findViewById(R.id.questionTitle);
        TextView partNumberText = getActivity().findViewById(R.id.part);

        ArrayList<QuestionObject> questionObjectArray = lists.getQuestionArray(lists.getListOfQuestionTables());

        partNumberText.setText("PART : " + questionObjectArray.get(positionInQuestionList).getQuestionPart());
        questionText.setText((questionObjectArray.get(positionInQuestionList)).getQuestionText());

        ArrayList <String> answers = lists.findingAnswers(positionInQuestionList);
        //refreshes the checkboxes by redrawing them
        addCheckBoxes(answers.size(), answers, positionInQuestionList);

/*
        //sets other two views visibility to gone so that checklist can be seen
        ScrollView scrollView = getActivity().findViewById(R.id.editScroll);
        scrollView.setVisibility(View.GONE);
        ScrollView scrollView1 = getActivity().findViewById(R.id.scrollView);
        scrollView1.setVisibility(View.GONE);
        ScrollView scrollView2 = getActivity().findViewById(R.id.scrollView2);
        scrollView2.setVisibility(View.VISIBLE);
*/


    }



}
