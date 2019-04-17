package com.example.ideapad510.sherkatquestionear.Answers;

//this class connects result classes to database

import android.content.Context;

import com.example.ideapad510.sherkatquestionear.Database.Tables.ResultTable;
import com.example.ideapad510.sherkatquestionear.Controller.Controller;

import java.util.ArrayList;

public class AnswerController extends Controller{

    public AnswerController(Context context){
        super(context);
    }

    public void insertToDatabase(String questionId, String answerId, String porseshnameId,
                                 String user, String pasokhgoo){
        databaseInsertMethods.insertRowSave(questionId, answerId, porseshnameId, user, pasokhgoo);
    }

    public ResultTable getRowSave(int id){
        return databaseGetMethods.getRowSave(id);
    }

    public ArrayList<AnswerObject> getAllResults(String user, String pasokhgoo){
        return databaseOtherMethods.getAllResults(user, pasokhgoo);
    }

    public ArrayList<AnswerObject> getAllAllResults(String user){
        return databaseOtherMethods.getAllAllResults(user);
    }

    //this method returns the result that belongs to a specific question
    public String getAnswerOfQuestion(String user, String pasokhgoo, String questionId, String poreshnameId){
        return databaseOtherMethods.getAnswerOfQuestion(user, pasokhgoo, questionId, poreshnameId);
    }

    public boolean searchInResultWithoutAnswer(String porseshnameId, String username,
                                               String questionId, String pasokhgoo){
        return databaseOtherMethods.searchInResultWithoutAnswer(porseshnameId, username, questionId, pasokhgoo);
    }

    public void deletSavedResultWithoutAnswer(String porseshnameId, String username,
                                              String questionId, String pasokhgoo){
        databaseOtherMethods.deletSavedResultWithoutAnswer(porseshnameId, username, questionId, pasokhgoo);
    }

    public void deletSavedResult(int position){ databaseOtherMethods.deletSavedResult(position); }
}
