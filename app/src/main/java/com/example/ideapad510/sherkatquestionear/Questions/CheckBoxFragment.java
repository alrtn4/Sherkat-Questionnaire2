package com.example.ideapad510.sherkatquestionear.Questions;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ideapad510.sherkatquestionear.R;

public class CheckBoxFragment extends Fragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        view = layoutInflater.inflate(R.layout.checkboxfragment, container, false);

        return view;
    }
}
