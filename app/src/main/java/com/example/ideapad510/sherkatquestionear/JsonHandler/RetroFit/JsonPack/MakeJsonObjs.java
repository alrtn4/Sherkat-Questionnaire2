package com.example.ideapad510.sherkatquestionear.JsonHandler.RetroFit.JsonPack;

import android.support.annotation.NonNull;

import com.example.ideapad510.sherkatquestionear.JsonHandler.RetroFit.JsonPack.DataPack;
import com.example.ideapad510.sherkatquestionear.JsonHandler.RetroFit.JsonPack.EndPack;
import com.example.ideapad510.sherkatquestionear.JsonHandler.RetroFit.JsonPack.Pack;
import com.example.ideapad510.sherkatquestionear.JsonHandler.RetroFit.JsonPack.StartPack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MakeJsonObjs {

    DataPack dataPack = new DataPack(1, "test","09128272027","test","test",
            "test","test", "test","test","test","test",
            "test","test","test");

    List<DataPack> dataPackList =  new ArrayList<DataPack>();

    StartPack startPack = new StartPack(1551080332,1000);

    EndPack endPack = new EndPack(1551080332,1000);


    public Pack getJsonObjs(){
        dataPackList.add(dataPack);

        Pack pack = new Pack(startPack, 1000, "testqrtotal", "req-result", dataPackList, endPack);

        return pack;
    }
}
