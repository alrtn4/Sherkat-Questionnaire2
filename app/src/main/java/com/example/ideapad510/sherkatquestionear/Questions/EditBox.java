package com.example.ideapad510.sherkatquestionear.Questions;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.ideapad510.sherkatquestionear.Params.Params;
import com.example.ideapad510.sherkatquestionear.R;
import com.example.ideapad510.sherkatquestionear.Answers.AnswerController;

import java.util.ArrayList;

public class EditBox {

    private Activity activity;
    private Context context;
    private String username;
    private String porseshnameId;
    private int pageNumber;
    private AnswerController answerController;
//    private Database db;
//    private QuestionController questionController;
//    private AnswerDatabaseController answerController;
    private Lists lists;
    private Params params = Params.getInstance();
    private String pasokhgoo;
    private EditText editText;


    public EditBox(Activity activity, Context context, String username, String porseshnameId,
                        int pageNumber ){
        this.activity = activity;
        this.context = context;
        this.username = username;
        this.porseshnameId = porseshnameId;
        this.pageNumber = pageNumber;
        answerController = new AnswerController(context);
//        db = Database.getInstance(context);
//        questionController = new QuestionController(context);
//        answerController = new AnswerDatabaseController(context);
        lists = new Lists(activity, pageNumber, context);
    }


    public void refreshPage(int positionInQuestionList ){
        pageNumber = positionInQuestionList;

        activity.setContentView(R.layout.question);

        TextView questionText = activity.findViewById(R.id.questionTitle);
        TextView partNumberText = activity.findViewById(R.id.part);

        ArrayList<QuestionObject> questionObjectArray = lists.getQuestionArray(lists.getListOfQuestionTables());

        partNumberText.setText("PART : " + questionObjectArray.get(positionInQuestionList).getQuestionPart());
        questionText.setText((positionInQuestionList+1)+": "+
                (questionObjectArray.get(positionInQuestionList)).getQuestionText());





        ScrollView scrollView = activity.findViewById(R.id.editScroll);
        scrollView.setVisibility(View.VISIBLE);
        ScrollView scrollView1 = activity.findViewById(R.id.scrollView);
        scrollView1.setVisibility(View.GONE);
        ScrollView scrollView2 = activity.findViewById(R.id.scrollView2);
        scrollView2.setVisibility(View.GONE);

        //here we use answerId as a String that holds the answer phrase

        String pasokhgoo;
        if(params.getStarterActivity().equals("adapter"))
            pasokhgoo = params.getAdapterPasokhgoo();
        else pasokhgoo = params.getPasokhgoo();

        String answer = answerController.getAnswerOfQuestion(username, pasokhgoo,
                (pageNumber+1)+"", porseshnameId);

        EditText editText = activity.findViewById(R.id.editText);
        editText.setText(answer);


    }

}
