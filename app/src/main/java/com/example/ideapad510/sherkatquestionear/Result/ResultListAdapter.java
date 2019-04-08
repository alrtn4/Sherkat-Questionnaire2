package com.example.ideapad510.sherkatquestionear.Result;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

//import com.example.ideapad510.sherkatquestionear.Questions.Chosens;
import com.example.ideapad510.sherkatquestionear.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class ResultListAdapter extends ArrayAdapter {
    private final Activity context;
    private final ArrayList<ResultObject> resultArray;
    public boolean[] faz;
//    private OnClickListener listener;
//    Chosens chosens;

/*    public interface OnClickListener {
        void onClick(View v);
    }
*/
    public ResultListAdapter(Activity context, ArrayList<ResultObject> resultArray){
        super(context, R.layout.resultlist_row, resultArray);
/*
        if (context instanceof OnClickListener){
            listener = (OnClickListener) context;
        }
*/
        this.context=context;
        this.resultArray = resultArray;

        faz = new boolean[resultArray.size()];
        Arrays.fill(faz, true);
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.resultlist_row, null,true);

        final TextView porseshnameTextView = rowView.findViewById(R.id.porseshnameId);
        final TextView questionTextView = rowView.findViewById(R.id.questionId);
        final TextView answerTextView = rowView.findViewById(R.id.answerId);
        final TextView pasokhgooTextView = rowView.findViewById(R.id.pasokhgoo);
/*
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v);
            }
        });
*/
        rowView.setOnClickListener(new View.OnClickListener(){
            boolean faz = true;

            @Override
            public void onClick(View v){
                if(faz) {
                    porseshnameTextView.setBackgroundResource(R.drawable.rectangle8);
                    porseshnameTextView.setTextColor(ContextCompat.getColor(context, R.color.black));
                    questionTextView.setBackgroundResource(R.drawable.rectangle8);
                    questionTextView.setTextColor(ContextCompat.getColor(context, R.color.black));
                    answerTextView.setBackgroundResource(R.drawable.rectangle8);
                    answerTextView.setTextColor(ContextCompat.getColor(context, R.color.black));
                    pasokhgooTextView.setBackgroundResource(R.drawable.rectangle8);
                    pasokhgooTextView.setTextColor(ContextCompat.getColor(context, R.color.black));

                    faz = !(faz);
                }
                else {
                    porseshnameTextView.setBackgroundResource(R.drawable.rectangle6);
                    porseshnameTextView.setTextColor(ContextCompat.getColor(context, R.color.white));
                    questionTextView.setBackgroundResource(R.drawable.rectangle6);
                    questionTextView.setTextColor(ContextCompat.getColor(context, R.color.white));
                    answerTextView.setBackgroundResource(R.drawable.rectangle6);
                    answerTextView.setTextColor(ContextCompat.getColor(context, R.color.white));
                    pasokhgooTextView.setBackgroundResource(R.drawable.rectangle6);
                    pasokhgooTextView.setTextColor(ContextCompat.getColor(context, R.color.white));

                    faz = !(faz);
                }
            }
        });

        porseshnameTextView.setText(resultArray.get(position).getPorseshnameId());
        questionTextView.setText(resultArray.get(position).getQuestionId());
        answerTextView.setText(resultArray.get(position).getAnswerId());
        pasokhgooTextView.setText(resultArray.get(position).getPasokhgoo());

        //porseshnameTextView.setBackgroundResource(R.drawable.);

        return rowView;
    }

}
