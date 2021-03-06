package com.example.ideapad510.sherkatquestionear.Answers;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

//import com.example.ideapad510.sherkatquestionear.Questions.Chosens;
import com.example.ideapad510.sherkatquestionear.Params.Params;
import com.example.ideapad510.sherkatquestionear.Questions.QuestionActivity;
import com.example.ideapad510.sherkatquestionear.Questions.QuestionActivity2;
import com.example.ideapad510.sherkatquestionear.R;

import java.util.ArrayList;
import java.util.Arrays;


public class AnswerListAdapter extends ArrayAdapter {
    private final Activity context;
    private final ArrayList<AnswerObject> resultArray;
    private boolean[] faz;
    private AnswerController answerController;
    Params params = Params.getInstance();
    String TAG = "AnswerListAdapter";
    private Refresh refresh;


    public AnswerListAdapter(Activity context, ArrayList<AnswerObject> resultArray){
        super(context, R.layout.resultlist_row, resultArray);


        this.context=context;
        this.resultArray = resultArray;

        answerController = new AnswerController(context);

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

                return true;
            }
        });



        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //-1 because pagenumber starts with 0
                params.setAdapterPageNumber(Integer.parseInt(questionTextView.getText().toString()) - 1);
                params.setAdapterPorseshnameId(porseshnameTextView.getText().toString());
                params.setAdapterPasokhgoo(pasokhgooTextView.getText().toString());

                Log.d(TAG, "onClick: "+params.getAdapterPasokhgoo());

                Intent intent = new Intent(context, QuestionActivity2.class);
//                intent.putExtra("starterActivity", "adapter");
                params.setStarterActivity("adapter");
                context.startActivity(intent);
                context.finish();

//                params.setAdapterPageNumber(-1);
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
                for(AnswerObject r:resultArray) {
                    if (!faz[position])
                        answerController.deletSavedResult(position);

                    position++;
                }

                refresh.onRefresh();

//                Intent intent = new Intent(context, AnswerActivity.class);
//                context.startActivity(intent);
/*                int i = 0;
                for(AnswerObject r:resultArray){
                    getView(i,null,null);
                    i++;
                }
*/
//                getView(0,null,null);

//                ((AllAnswersActivity)context).super.
//                context.setContentView(R.layout.result);
//                String user = params.getUsername();
//                String pasokhgoo = params.getPasokhgoo();
//              ((AllAnswersActivity)context).listView = context.findViewById(R.id.resultList);
//                AnswerListAdapter saveListAdapter = new AnswerListAdapter(context, answerController.getAllAllResults(user));
//                ((AllAnswersActivity)context).listView.setAdapter(saveListAdapter);

            }
        });
    }



    public interface Refresh {
        void onRefresh();
    }
}
