package com.example.ideapad510.sherkatquestionear.Questions;

import android.app.Activity;
import android.content.Context;

import com.example.ideapad510.sherkatquestionear.Database.Tables.QuestionTable;
import com.example.ideapad510.sherkatquestionear.Params.Params;
import com.example.ideapad510.sherkatquestionear.Questions.Answer.AnswerDatabaseController;

import java.util.ArrayList;

/**
 * Created by Ideapad 510 on 2/7/2019.
 */

public class Lists {
//    private Activity activity;
//    private int pageNumber;
    private QuestionController questionController;
    private AnswerDatabaseController answerController;
    String TAG = "lists";
    private Params params = Params.getInstance();

    public Lists(Activity activity, int pageNumber, Context context) {
//        this.activity = activity;
//        this.pageNumber = pageNumber;
        questionController = new QuestionController(context);
        answerController = new AnswerDatabaseController(context);

    }
    //get list of question tables saved in the questionnaire
    public ArrayList<QuestionDemand> getListOfQuestionTables(){
        ArrayList<QuestionDemand> questionDemandArray = new ArrayList<>();

        String demand = params.getQT();
        String[] questionTables = demand.split("-");
        for(String s: questionTables) {
            String[] s1 = s.split("/");
            String startPosition = s1[1];

            String[] s2 = s1[0].split("\"");
            String questionTableName = s2[1];

            QuestionDemand questionDemand = new QuestionDemand(questionTableName, startPosition);
            questionDemandArray.add(questionDemand);
        }

        return questionDemandArray;
    }

    //get list of all questions based on which question tables we need for current questionnaire(porseshname)
    public ArrayList<QuestionObject> getQuestionArray(ArrayList<QuestionDemand> questionDemandArray){
        ArrayList<QuestionObject> questionArray = new ArrayList<>();

        for(QuestionDemand questionDemand : questionDemandArray)
            switch(questionDemand.getQuestionTableName()){
                case QuestionTable.TABLE_NAME:
                    questionArray.addAll(questionController.getQuestionsFromQuestionTable(questionDemand.getStartPosition()));
            }

        return questionArray;
    }

    //this method finds answers that belong to specific question based on
    // question id of question and answer's question id
    public ArrayList<String> findingAnswers(int pageNumber){
        ArrayList<String> answers = new ArrayList<>();

        int positionInArray = pageNumber;

        ArrayList<QuestionObject> questionObjectArray = getQuestionArray(getListOfQuestionTables());

        for(int i = 1; i <= answerController.getRowCount(); i++) {
            boolean answerIdEqualsQuestionId = (answerController.getRow(i).getQuestionID()).
                    equals(String.valueOf(questionObjectArray.get(positionInArray).getQuestionId()));
            if (answerIdEqualsQuestionId)
                answers.add(answerController.getRow(i).getAnswer());
        }

        return answers;
    }


}
