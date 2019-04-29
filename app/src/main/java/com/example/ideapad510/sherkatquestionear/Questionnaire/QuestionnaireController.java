package com.example.ideapad510.sherkatquestionear.Questionnaire;


import android.content.Context;

import com.example.ideapad510.sherkatquestionear.Database.Tables.QuestionnaireTable;
import com.example.ideapad510.sherkatquestionear.Controller.Controller;
import com.example.ideapad510.sherkatquestionear.Params.Params;
import com.example.ideapad510.sherkatquestionear.ql.qlController;

import java.util.ArrayList;

public class QuestionnaireController extends Controller{
    //questionnaireIdd returns the list of questionnaires which
    // are in function recieved from the code of user
    private ArrayList<String> questionnaireIdd =new ArrayList<>();
    private Params params = Params.getInstance();

    public QuestionnaireController(Context context){
        super(context);
    }

    //returns list of porseshnameha
    public ArrayList<String> getQuestionnaires(){return databaseOtherMethods.getQuestionnaires();
    }

    //inserts a questionnaire with details to database
    public void insertToDatabase(String name, String text, String qt, String at){
        databaseInsertMethods.insertRowQuestionnaire(name, text, qt, at);
    }

    //returns a questionnaire from database depending on database id
    public QuestionnaireTable getQuestionnaire(int id){
        return databaseGetMethods.getRowQuestionnaire(id);
    }


    //this parses the function of qltable (which holds a list of questionnaires  that every
    //user must ask)
    public ArrayList functionParse(String function){
        String[] questionnaireId = function.split("-");

        ArrayList<String> questionnaires = getQuestionnaires();

        //qtArray returns questionnaire list which are in function string
        ArrayList<String> qtArray = new ArrayList<>();


        for(String id : questionnaireId){
            questionnaireIdd.add(id);

            int idd = Integer.valueOf(id);
            qtArray.add(questionnaires.get(idd-1));
        }


        return qtArray;
    }



    public void paramsSet(int position){
        //because start index of database and list are different
        position++;



        //sending porseshnameId to next activity (question)
        params.setQT(getQuestionnaire(position).getQT());
        params.setAT(getQuestionnaire(position).getAT());

        //questionnaireIdd returns the list of questionnaires which
        // are in function recieved from the code of user
        params.setPorseshnameId(questionnaireIdd.get(position-1));


    }



    public ArrayList<String> getQuestionnaireList(){
        qlController qlControllerr = new qlController(context);

        //get List of porseshnameha
        String username = params.getUsername();
        String jmrCode = qlControllerr.getJmrcode(username);
        String function = qlControllerr.getFunction(jmrCode);

        return functionParse(function);


    }

}
