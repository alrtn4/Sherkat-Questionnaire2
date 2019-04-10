package com.example.ideapad510.sherkatquestionear.Result;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

//import com.example.ideapad510.sherkatquestionear.Questions.Chosens;
import com.example.ideapad510.sherkatquestionear.Params.Params;
import com.example.ideapad510.sherkatquestionear.R;

import java.util.ArrayList;
import java.util.Arrays;


public class ResultListAdapter extends ArrayAdapter {
    private final Activity context;
    private final ArrayList<ResultObject> resultArray;
    private boolean[] faz;
    private ResultController resultController;
    Params params = Params.getInstance();
    String TAG = "ResultListAdapter";
    private Refresh refresh;


    public ResultListAdapter(Activity context, ArrayList<ResultObject> resultArray){
        super(context, R.layout.resultlist_row, resultArray);


        this.context=context;
        this.resultArray = resultArray;

        resultController = new ResultController(context);

        faz = new boolean[resultArray.size()];
        Arrays.fill(faz, true);

        setListener();

        if(context instanceof Refresh)
            refresh = (Refresh) context;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.resultlist_row, null,true);

        final TextView porseshnameTextView = rowView.findViewById(R.id.porseshnameId);
        final TextView questionTextView = rowView.findViewById(R.id.questionId);
        final TextView answerTextView = rowView.findViewById(R.id.answerId);
        final TextView pasokhgooTextView = rowView.findViewById(R.id.pasokhgoo);


        porseshnameTextView.setText(resultArray.get(position).getPorseshnameId());
        questionTextView.setText(resultArray.get(position).getQuestionId());
        answerTextView.setText(resultArray.get(position).getAnswerId());
        pasokhgooTextView.setText(resultArray.get(position).getPasokhgoo());


        rowView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v){
                if(faz[position]) {
                    porseshnameTextView.setBackgroundResource(R.drawable.rectangle8);
                    porseshnameTextView.setTextColor(ContextCompat.getColor(context, R.color.black));
                    questionTextView.setBackgroundResource(R.drawable.rectangle8);
                    questionTextView.setTextColor(ContextCompat.getColor(context, R.color.black));
                    answerTextView.setBackgroundResource(R.drawable.rectangle8);
                    answerTextView.setTextColor(ContextCompat.getColor(context, R.color.black));
                    pasokhgooTextView.setBackgroundResource(R.drawable.rectangle8);
                    pasokhgooTextView.setTextColor(ContextCompat.getColor(context, R.color.black));


                    faz[position] = !faz[position];
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

                    faz[position] = !faz[position];
                }

                return false;
            }
        });


        return rowView;
    }


    private void setListener(){
        Button deletButton = context.findViewById(R.id.eliminate);

        deletButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = 0;
                for(ResultObject r:resultArray) {
                    if (!faz[position])
                        resultController.deletSavedResult(position);

                    position++;
                }

                refresh.onRefresh();

//                Intent intent = new Intent(context, ResultActivity.class);
//                context.startActivity(intent);
/*                int i = 0;
                for(ResultObject r:resultArray){
                    getView(i,null,null);
                    i++;
                }
*/
//                getView(0,null,null);

//                ((AllResultsActivity)context).super.
//                context.setContentView(R.layout.result);
//                String user = params.getUsername();
//                String pasokhgoo = params.getPasokhgoo();
//              ((AllResultsActivity)context).listView = context.findViewById(R.id.resultList);
//                ResultListAdapter saveListAdapter = new ResultListAdapter(context, resultController.getAllAllResults(user));
//                ((AllResultsActivity)context).listView.setAdapter(saveListAdapter);

            }
        });
    }

    public interface Refresh {
        void onRefresh();
    }
}
