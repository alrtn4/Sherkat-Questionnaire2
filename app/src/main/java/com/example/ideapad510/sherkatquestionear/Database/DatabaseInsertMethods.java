package com.example.ideapad510.sherkatquestionear.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.ideapad510.sherkatquestionear.Database.Tables.AnswerTable;
import com.example.ideapad510.sherkatquestionear.Database.Tables.LoginTable;
import com.example.ideapad510.sherkatquestionear.Database.Tables.QuestionTable;
import com.example.ideapad510.sherkatquestionear.Database.Tables.QuestionnaireTable;
import com.example.ideapad510.sherkatquestionear.Database.Tables.ResultTable;
import com.example.ideapad510.sherkatquestionear.Database.Tables.phone;
import com.example.ideapad510.sherkatquestionear.Database.Tables.qlTable;

/**
 * Created by Ideapad 510 on 2/7/2019.
 */

public class DatabaseInsertMethods {
    Context context;
    Database database;
    DatabaseOtherMethods databaseSearchMethods;

    public DatabaseInsertMethods(Context context){
        database = Database.getInstance(context);
        databaseSearchMethods = new DatabaseOtherMethods(context);
    }

    public void insertRowLogin(String username, String password, String code) {
        SQLiteDatabase db = database.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(LoginTable.jmr_user, username);
        values.put(LoginTable.jmr_pass, password);
        values.put(LoginTable.jmr_code, code);
        db.insert(LoginTable.TABLE_NAME, null, values);

        db.close();
    }

    public void insertRowQuestionnaire(String name, String text, String qt, String at){
        SQLiteDatabase db = database.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(QuestionnaireTable.COLUMN_NAME, name);
        values.put(QuestionnaireTable.COLUMN_TEXT, text);
        values.put(QuestionnaireTable.COLUMN_QT, qt);
        values.put(QuestionnaireTable.COLUMN_AT, at);

        db.insert(QuestionnaireTable.TABLE_NAME, null, values);

        db.close();
    }

    public void insertRowQuestion(String question, String position, String part) {
        SQLiteDatabase db = database.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(QuestionTable.COLUMN_QUESTION, question);
        values.put(QuestionTable.COLUMN_POSITION, position);
        values.put(QuestionTable.COLUMN_PART, part);
        db.insert(QuestionTable.TABLE_NAME, null, values);

        db.close();

//        System.out.println("something added to db");
    }


    public void insertRowAnswer(String questionID, String answer, String mode, String position) {
        SQLiteDatabase db = database.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(AnswerTable.COLUMN_QUESTION_ID, questionID);
        values.put(AnswerTable.COLUMN_ANSWER, answer);
        values.put(AnswerTable.COLUMN_MODE, mode);
        values.put(AnswerTable.COLUMN_POSITION, position);
        db.insert(AnswerTable.TABLE_NAME, null, values);

        db.close();
    }

    public void insertRowSave(String questionID, String answerId, String porseshnameId, String user, String pasokhgoo) {
        SQLiteDatabase db = database.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ResultTable.COLUMN_QUESTION_ID, questionID);
        values.put(ResultTable.COLUMN_ANSWER_ID, answerId);
        values.put(ResultTable.COLUMN_PORSESHNAME_ID, porseshnameId);
        values.put(ResultTable.COLUMN_USER, user);
        values.put(ResultTable.PASOKHGOO, pasokhgoo);
        if(!databaseSearchMethods.searchInResult(porseshnameId, user, questionID, answerId, pasokhgoo))
            db.insert(ResultTable.TABLE_NAME, null, values);

        db.close();
    }

    public void insertRowPhone(String phoneNumber) {
        SQLiteDatabase db = database.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(phone.phoneNumber, phoneNumber);
        db.insert(phone.TABLE_NAME, null, values);

        db.close();
    }



    public void insertqlTable(String qlfunction, String jmrcode) {
        SQLiteDatabase db = database.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(qlTable.qlfunction, qlfunction);
        values.put(qlTable.jmrcode, jmrcode);
        db.insert(qlTable.TABLE_NAME, null, values);

        db.close();
    }


    public void insertTextAnswer(String questionID, String answerId, String porseshnameId,
                                 String user, String pasokhgoo) {
        SQLiteDatabase db = database.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(ResultTable.COLUMN_QUESTION_ID, questionID);
        values.put(ResultTable.COLUMN_ANSWER_ID, answerId);
        values.put(ResultTable.COLUMN_PORSESHNAME_ID, porseshnameId);
        values.put(ResultTable.COLUMN_USER, user);
        values.put(ResultTable.PASOKHGOO, pasokhgoo);

        db.insert(ResultTable.TABLE_NAME, null, values);

        db.close();
    }

}
