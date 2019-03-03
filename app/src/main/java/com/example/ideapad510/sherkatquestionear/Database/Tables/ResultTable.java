package com.example.ideapad510.sherkatquestionear.Database.Tables;


public class ResultTable {
    public static final String TABLE_NAME = "Result";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_QUESTION_ID = "questionId";
    public static final String COLUMN_ANSWER_ID = "answerId";
    public static final String COLUMN_PORSESHNAME_ID = "porseshnameId";
    public static final String PASOKHGOO = "pasokhgoo";
    public static final String COLUMN_USER = "user";


    private int id;
    private String question_id;
    private String answer_id;
    private String porseshname_id;
    private String user;
    private String pasokhgoo;

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ( "
                    + COLUMN_QUESTION_ID + " TEXT,"
                    + COLUMN_ANSWER_ID + " TEXT,"
                    + COLUMN_PORSESHNAME_ID + " TEXT,"
                    + COLUMN_USER + " TEXT,"
                    + PASOKHGOO + " TEXT,"
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT "
                    + ")";

    public ResultTable(int id, String question_id, String answer_id, String porseshname_id, String user, String pasokhgoo){
        this.id = id;
        this.question_id = question_id;
        this.answer_id = answer_id;
        this.porseshname_id = porseshname_id;
        this.user = user;
        this.pasokhgoo = pasokhgoo;
    }

    public String getColumnPorseshnameId(){return porseshname_id;}

    public String getColumnQuestionId(){return question_id;}

    public String getColumnAnswerId(){return answer_id;}

    public String getColumnUser(){return user;}

    public String getPasokhgoo(){return pasokhgoo;}
}
