package com.example.ideapad510.sherkatquestionear.Questions;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.ideapad510.sherkatquestionear.R;

public class EditTextFragment extends Fragment  {

    private View view;
    private EditText editText;
    private Button button;


    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container,
                             Bundle savedInstanceState){
        view = layoutInflater.inflate(R.layout.edittextfragment, container, false);

//        initViews();


        return view;

    }

    private void initViews(){
        editText = view.findViewById(R.id.editText);
        button = view.findViewById(R.id.registerBtn);
    }
}
