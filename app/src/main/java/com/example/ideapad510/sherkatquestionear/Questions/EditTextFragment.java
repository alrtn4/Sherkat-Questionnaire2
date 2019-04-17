package com.example.ideapad510.sherkatquestionear.Questions;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ideapad510.sherkatquestionear.Answers.AnswerController;
import com.example.ideapad510.sherkatquestionear.Params.Params;
import com.example.ideapad510.sherkatquestionear.R;

import static android.content.ContentValues.TAG;

public class EditTextFragment extends Fragment  {

    private View view;
    private EditText editText;
    private Button button;
    private Params params = Params.getInstance();
    private AnswerController answerController = new AnswerController(getContext());
    private String username;
    private String porseshnameId;
    private int pageNumber;
    private String pasokhgoo;
    private String TAG = "edittextfragment";


    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container,
                             Bundle savedInstanceState){
        view = layoutInflater.inflate(R.layout.edittextfragment, container, false);

        setParams();

        Log.d(TAG, "onCreateView: ");
        
        initViews();


        return view;

    }

    private void initViews(){
        editText = view.findViewById(R.id.editText);
        button = view.findViewById(R.id.registerBtn);

        String pasokhgoo;
        if(params.getStarterActivity().equals("adapter"))
            pasokhgoo = params.getAdapterPasokhgoo();
        else pasokhgoo = params.getPasokhgoo();

        String answer = answerController.getAnswerOfQuestion(username, pasokhgoo,
                (pageNumber+1)+"", porseshnameId);

//        Log.d(TAG, "initViews: "+answer+" 123");

        EditText editText = view.findViewById(R.id.editText);
        editText.setText(answer);

    }

    public void setParams(){
        username = params.getUsername();
        porseshnameId = params.getPorseshnameId();


        //this condition shows that result activity has started question activity
        //because if it has started , adapterpagenumber must have a valid value
        if(params.getStarterActivity().equals("adapter")){
            pageNumber = params.getAdapterPageNumber();
            porseshnameId = params.getAdapterPorseshnameId();
            pasokhgoo = params.getAdapterPasokhgoo();

            if(pasokhgoo == null)
                pasokhgoo = "";
        }


    }

    public void onRegisterClick(View view){
        editText = view.findViewById(R.id.editText);
        String answer = editText.getText().toString();

        if(answerController.searchInResultWithoutAnswer(porseshnameId, username,
                (pageNumber+1)+"", pasokhgoo))
            answerController.deletSavedResultWithoutAnswer(porseshnameId, username,
                    (pageNumber+1)+"", pasokhgoo);

        answerController.insertToDatabase((pageNumber+1)+"", answer, porseshnameId,
                username, pasokhgoo);
    }


    public void refresh(int pageNumber){
        this.pageNumber = pageNumber;

        String answer = answerController.getAnswerOfQuestion(username, pasokhgoo,
                (pageNumber+1)+"", porseshnameId);

        EditText editText = view.findViewById(R.id.editText);
        editText.setText(answer);

    }

}
