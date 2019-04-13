package com.example.ideapad510.sherkatquestionear.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ideapad510.sherkatquestionear.Database.Tables.AnswerTable;
import com.example.ideapad510.sherkatquestionear.Database.Tables.LoginTable;
import com.example.ideapad510.sherkatquestionear.Database.Tables.QuestionTable;
import com.example.ideapad510.sherkatquestionear.Database.Tables.QuestionnaireTable;
import com.example.ideapad510.sherkatquestionear.Database.Tables.ResultTable;
import com.example.ideapad510.sherkatquestionear.Database.Tables.qlTable;

/**
 * Created by Ideapad 510 on 2/7/2019.
 */

public class DatabaseGetMethods {
    private Database database ;

    public DatabaseGetMethods(Context context){
        database = Database.getInstance(context);
    }

    public LoginTable getRowLogin(long id) {
        SQLiteDatabase db = database.getReadableDatabase();

        Cursor cursor = db.query(LoginTable.TABLE_NAME,
                new String[]{ LoginTable.jmr_user, LoginTable.jmr_pass, LoginTable.jmr_code,
                        LoginTable.COLUMN_ID},
                LoginTable.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        LoginTable tableRow = new LoginTable(
                cursor.getString(cursor.getColumnIndex(LoginTable.jmr_user)),
                cursor.getString(cursor.getColumnIndex(LoginTable.jmr_pass)),
                cursor.getString(cursor.getColumnIndex(LoginTable.jmr_code)),
                cursor.getInt(cursor.getColumnIndex(LoginTable.COLUMN_ID)));

        cursor.close();

        return tableRow;
    }

    public QuestionnaireTable getRowQuestionnaire(long id) {
        SQLiteDatabase db = database.getReadableDatabase();

        Cursor cursor = db.query(QuestionnaireTable.TABLE_NAME,
                new String[]{QuestionnaireTable.COLUMN_ID, QuestionnaireTable.COLUMN_NAME,
                        QuestionnaireTable.COLUMN_TEXT, QuestionnaireTable.COLUMN_QT,
                        QuestionnaireTable.COLUMN_AT },
                QuestionnaireTable.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        QuestionnaireTable tableRow = new QuestionnaireTable(
                cursor.getInt(cursor.getColumnIndex(QuestionnaireTable.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(QuestionnaireTable.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(QuestionnaireTable.COLUMN_TEXT)),
                cursor.getString(cursor.getColumnIndex(QuestionnaireTable.COLUMN_QT)),
                cursor.getString(cursor.getColumnIndex(QuestionnaireTable.COLUMN_AT)));


        cursor.close();

        return tableRow;
    }


    public QuestionTable getRowQuestion(long id) {
        SQLiteDatabase db = database.getReadableDatabase();

        Cursor cursor = db.query(QuestionTable.TABLE_NAME,
                new String[]{QuestionTable.COLUMN_QUESTION, QuestionTable.COLUMN_POSITION,
                        QuestionTable.COLUMN_PART, QuestionTable.COLUMN_ID,
                        QuestionTable.COLUMN_CODE, QuestionTable.COLUMN_FUNC},
                QuestionTable.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        QuestionTable tableRow = new QuestionTable(
                cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_QUESTION)),
                cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_POSITION)),
                cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_PART)),
                cursor.getInt(cursor.getColumnIndex(QuestionTable.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_CODE)),
                cursor.getString(cursor.getColumnIndex(QuestionTable.COLUMN_FUNC)));
        cursor.close();

        return tableRow;
    }


    public AnswerTable getRowAnswer(long id) {
        SQLiteDatabase db = database.getReadableDatabase();

        Cursor cursor = db.query(AnswerTable.TABLE_NAME,
                new String[]{ AnswerTable.COLUMN_ID, AnswerTable.COLUMN_QUESTION_ID, AnswerTable.COLUMN_ANSWER,
                        AnswerTable.COLUMN_MODE, AnswerTable.COLUMN_POSITION,
                        AnswerTable.COLUMN_GOTO, AnswerTable.COLUMN_SCOUR, AnswerTable.COLUMN_FUNCTION},
                AnswerTable.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        AnswerTable tableRow = new AnswerTable(
                cursor.getInt(cursor.getColumnIndex(AnswerTable.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(AnswerTable.COLUMN_QUESTION_ID)),
                cursor.getString(cursor.getColumnIndex(AnswerTable.COLUMN_ANSWER)),
                cursor.getString(cursor.getColumnIndex(AnswerTable.COLUMN_MODE)),
                cursor.getString(cursor.getColumnIndex(AnswerTable.COLUMN_POSITION)),
                cursor.getString(cursor.getColumnIndex(AnswerTable.COLUMN_GOTO)),
                cursor.getString(cursor.getColumnIndex(AnswerTable.COLUMN_SCOUR)),
                cursor.getString(cursor.getColumnIndex(AnswerTable.COLUMN_FUNCTION)));
        cursor.close();

        return tableRow;
    }

    public ResultTable getRowSave(long id) {
        SQLiteDatabase db = database.getReadableDatabase();

        Cursor cursor = db.query(ResultTable.TABLE_NAME,
                new String[]{ ResultTable.COLUMN_ID, ResultTable.COLUMN_QUESTION_ID, ResultTable.COLUMN_ANSWER_ID,
                        ResultTable.COLUMN_PORSESHNAME_ID, ResultTable.COLUMN_USER, ResultTable.PASOKHGOO},
                ResultTable.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        ResultTable tableRow = null;

        if (cursor != null)
            if(cursor.moveToFirst()) {

                tableRow = new ResultTable(
                        cursor.getInt(cursor.getColumnIndex(ResultTable.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(ResultTable.COLUMN_QUESTION_ID)),
                        cursor.getString(cursor.getColumnIndex(ResultTable.COLUMN_ANSWER_ID)),
                        cursor.getString(cursor.getColumnIndex(ResultTable.COLUMN_PORSESHNAME_ID)),
                        cursor.getString(cursor.getColumnIndex(ResultTable.COLUMN_USER)),
                        cursor.getString(cursor.getColumnIndex(ResultTable.PASOKHGOO)));

                cursor.close();
            }

        return tableRow;
    }




    public qlTable getqlTable(long id) {
        SQLiteDatabase db = database.getReadableDatabase();

        Cursor cursor = db.query(qlTable.TABLE_NAME,
                new String[]{ qlTable.COLUMN_ID, qlTable.qlfunction, qlTable.jmrcode},
        qlTable.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        qlTable tableRow = null;
        if (cursor != null)
          if ( cursor.moveToFirst()) {
               tableRow = new qlTable(
                      cursor.getString(cursor.getColumnIndex(qlTable.qlfunction)),
                      cursor.getString(cursor.getColumnIndex(qlTable.jmrcode)),
                      cursor.getInt(cursor.getColumnIndex(qlTable.COLUMN_ID)));
              cursor.close();
          }

        return tableRow;
    }

}
