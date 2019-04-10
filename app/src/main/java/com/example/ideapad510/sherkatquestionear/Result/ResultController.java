package com.example.ideapad510.sherkatquestionear.Result;

//this class connects result classes to database

import android.content.Context;

import com.example.ideapad510.sherkatquestionear.Database.Tables.ResultTable;
import com.example.ideapad510.sherkatquestionear.Controller.Controller;

import java.util.ArrayList;

public class ResultController extends Controller{

    public ResultController(Context context){
        super(context);
    }

    public void insertToDatabase(String questionId, String answerId, String porseshnameId,
                                 String user, String pasokhgoo){
        databaseInsertMethods.insertRowSave(questionId, answerId, porseshnameId, user, pasokhgoo);
    }

    public ResultTable getRowSave(int id){
        return databaseGetMethods.getRowSave(id);
    }

    public ArrayList<ResultObject> getAllResults(String user, String pasokhgoo){
        return databaseOtherMethods.getAllResults(user, pasokhgoo);
    }

    public ArrayList<ResultObject> getAllAllResults(String user){
        return databaseOtherMethods.getAllAllResults(user);
    }

    //this method returns the result that belongs to specific question
    public String getAnswerOfQuestion(String user, String pasokhgoo, String questionId){
        return databaseOtherMethods.getAnswerOfQuestion(user, pasokhgoo, questionId);
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
