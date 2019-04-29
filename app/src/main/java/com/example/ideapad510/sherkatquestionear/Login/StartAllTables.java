package com.example.ideapad510.sherkatquestionear.Login;


import android.content.Context;
import android.util.Log;

import com.example.ideapad510.sherkatquestionear.Database.Database;
import com.example.ideapad510.sherkatquestionear.Database.DatabaseInsertMethods;
import com.example.ideapad510.sherkatquestionear.Questionnaire.QuestionnaireController;
import com.example.ideapad510.sherkatquestionear.Questions.Answer.AnswerDatabaseController;
import com.example.ideapad510.sherkatquestionear.Questions.Answer.QuestionsAnswersArray;
import com.example.ideapad510.sherkatquestionear.Questions.QuestionController;
import com.example.ideapad510.sherkatquestionear.Answers.AnswerController;

import static android.content.ContentValues.TAG;

public class StartAllTables {
    private Context context;
    private LoginController loginController = new LoginController(context);
    private QuestionnaireController questionnaireController = new QuestionnaireController(context);
    private AnswerController answerController = new AnswerController(context);
    private DatabaseInsertMethods databaseInsertMethods = new DatabaseInsertMethods(context);
    private String TAG = "StartAllTables";

    StartAllTables(Context context){
        this.context = context;


            sampleUserPass();
            sampleQuestionnaires();
            new QuestionController(context).insertQuestionArray(new QuestionsAnswersArray());
            new AnswerDatabaseController(context).insertAnswerArray(new QuestionsAnswersArray());
            sampleqltable();

    }





    private void sampleUserPass(){
        loginController.insertToDatabase("ali","123" , "code1");
        loginController.insertToDatabase("hamid","1234" , "code2");
        loginController.insertToDatabase("1","1" , "code3");

    }

    private void sampleQuestionnaires(){
        questionnaireController.insertToDatabase("پرسشنامه 1","درباره آب و هوا",
                "1:\"question1\"/1", "answer1");
        questionnaireController.insertToDatabase("پرسشنامه 2","درباره جغرافی",
                "1:\"question1\"/1", "answer1");
        questionnaireController.insertToDatabase("پرسشنامه 3","درباره سلامت",
                "1:\"question1\"/1", "answer1");
    }

    private void sampleqltable(){
        databaseInsertMethods.insertqlTable("1-2-3","code1");
        databaseInsertMethods.insertqlTable("1","code2");
        databaseInsertMethods.insertqlTable("2-3","code3");
    }

}
