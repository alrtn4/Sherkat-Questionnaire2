package com.example.ideapad510.sherkatquestionear.Params;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Ideapad 510 on 2/12/2019.
 */

public class Params {
    private static Params instance;

    private String username;
    private String QT;
    private String AT;
    private String porseshnameId;
    private String pasokhgoo;
    private Context context;
    private ArrayList questionnairesIds;
    private int pageNumber;
    private int listPosition;
    private int adapterPageNumber = -1;
    private String adapterPorseshnameId;
    private String adapterPasokhgoo;
    private String starterActivity;
    private String resultStarterActivity;


    private Params(){};

    public static Params getInstance(){
        if(instance == null)
            instance = new Params();
        return instance;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setQT(String QT){
        this.QT = QT;
    }

    public void setAT(String AT){
        this.AT = AT;
    }

    public void setPorseshnameId(String porseshnameId){
        this.porseshnameId = porseshnameId;
    }

    public void setPasokhgoo(String pasokhgoo){
        this.pasokhgoo = pasokhgoo;
    }

    public void setContext(Context context){ this.context = context;}

    public void setQuestionnairesIds(ArrayList<String> questionnairesIds){
        this.questionnairesIds = questionnairesIds;}

    public void setPageNumber(int pageNumber){ this.pageNumber = pageNumber;}

    public void setAdapterPageNumber(int questionPageNumber) {
        this.adapterPageNumber = questionPageNumber; }

    public void setAdapterPorseshnameId(String adapterPorseshnameId) {
        this.adapterPorseshnameId = adapterPorseshnameId; }

    public void setAdapterPasokhgoo(String adapterPasokhgoo) {
        this.adapterPasokhgoo = adapterPasokhgoo; }

    public void setStarterActivity(String starterActivity) { this.starterActivity = starterActivity; }

    public void setResultStarterActivity(String resultStarterActivity) {
        this.resultStarterActivity = resultStarterActivity;
    }

    public String getResultStarterActivity() { return resultStarterActivity; }

    public String getAdapterPasokhgoo() { return adapterPasokhgoo; }

    public String getStarterActivity() { return starterActivity; }

    public String getAdapterPorseshnameId() { return adapterPorseshnameId; }

    public int getAdapterPageNumber() { return adapterPageNumber; }

    public String getUsername(){
        return username;
    }

    public String getQT(){
        return QT;
    }

    public String getAT(){
        return AT;
    }

    public String getPorseshnameId(){
        return porseshnameId;
    }

    public String getPasokhgoo(){ return pasokhgoo;}

    public Context getContext(){ return context;}

    public ArrayList<String> getQuestionnairesIds(){ return questionnairesIds;}

    public int getPageNumber(){ return pageNumber;}

    public int getListPosition() { return listPosition; }

    public void setListPosition(int listPosition) { this.listPosition = listPosition; }

}
