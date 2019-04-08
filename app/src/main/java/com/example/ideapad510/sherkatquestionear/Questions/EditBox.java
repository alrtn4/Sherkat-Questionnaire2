package com.example.ideapad510.sherkatquestionear.Questions;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.ideapad510.sherkatquestionear.Database.Database;
import com.example.ideapad510.sherkatquestionear.Params.Params;
import com.example.ideapad510.sherkatquestionear.Questions.Answer.AnswerController;
import com.example.ideapad510.sherkatquestionear.R;
import com.example.ideapad510.sherkatquestionear.Result.ResultController;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class EditBox {

    private Activity activity;
    private Context context;
    private String username;
    private String porseshnameId;
    private int pageNumber;
    private ResultController resultController;
    private Database db;
    private QuestionController questionController;
    private AnswerController answerController;
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
        resultController = new ResultController(context);
        db = Database.getInstance(context);
        questionController = new QuestionController(context);
        answerController = new AnswerController(context);
        lists = new Lists(activity, pageNumber, context);
    }


    public void refreshPage(int positionInQuestionList ){
        pageNumber = positionInQuestionList;

        activity.setContentView(R.layout.question);

        TextView questionText = activity.findViewById(R.id.questionTitle);
        TextView partNumberText = activity.findViewById(R.id.part);

        ArrayList<QuestionObject> questionObjectArray = lists.getQuestionArray(lists.getListOfQuestionTables());

        partNumberText.setText("PART : " + questionObjectArray.get(positionInQuestionList).getQuestionPart());
        questionText.setText((questionObjectArray.get(positionInQuestionList)).getQuestionText());



        editText = activity.findViewById(R.id.editText);


//        Button registerBtn = activity.findViewById(R.id.registerBtn);

        ScrollView scrollView = activity.findViewById(R.id.editScroll);
        scrollView.setVisibility(View.VISIBLE);
        ScrollView scrollView1 = activity.findViewById(R.id.scrollView);
        scrollView1.setVisibility(View.GONE);
        ScrollView scrollView2 = activity.findViewById(R.id.scrollView2);
        scrollView2.setVisibility(View.GONE);

        //here we use answerId as a String that holds the answer phrase
        pasokhgoo = params.getPasokhgoo();
        String answer = resultController.getAnswerOfQuestion(username, pasokhgoo, (pageNumber+1)+"");

        editText.setText(answer);


    }

}
