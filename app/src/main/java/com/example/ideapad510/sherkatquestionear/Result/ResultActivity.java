package com.example.ideapad510.sherkatquestionear.Result;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.ideapad510.sherkatquestionear.Params.Params;
import com.example.ideapad510.sherkatquestionear.R;


public class ResultActivity extends AppCompatActivity {
    ListView listView;
    ResultController resultController = new ResultController(this);
    String TAG = "REsult";
    Params params = Params.getInstance();
    int listPosition;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

       String user = params.getUsername();
        String pasokhgoo = params.getPasokhgoo();

        listView = findViewById(R.id.resultList);
//        ResultListAdapter saveListAdapter = new ResultListAdapter(this, resultController.getAllResults(user, pasokhgoo));
//        listView.setAdapter(saveListAdapter);

        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listPosition = position;
            }
        });

        listView.setAdapter(new ListAdapter() {
            super(this , R.layout.resultlist_row, resultArray);

            @Override
            public boolean areAllItemsEnabled() {
                return false;
            }

            @Override
            public boolean isEnabled(int position) {
                return false;
            }

            @Override
            public void registerDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return null;
            }

            @Override
            public int getItemViewType(int position) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        });

    }


}
