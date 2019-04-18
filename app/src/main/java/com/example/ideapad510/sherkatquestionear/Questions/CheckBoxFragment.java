package com.example.ideapad510.sherkatquestionear.Questions;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.example.ideapad510.sherkatquestionear.R;

import java.util.ArrayList;

public class CheckBoxFragment extends Fragment {

    private View view;

    public static CheckBoxFragment getInstance(Bundle args){
        CheckBoxFragment checkBoxFragment = new CheckBoxFragment();

        checkBoxFragment.setArguments(args);

        return checkBoxFragment;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState){
        view = layoutInflater.inflate(R.layout.checkboxfragment, container, false);

        return view;
    }

    //draws the checkboxes
    private void addCheckBoxes(int number, ArrayList<String> answers, int pageNumber) {
        LinearLayout checkboxContainer = view.findViewById(R.id.checkboxContainer);


        //draws checkBoxes equal to the given number
        for (int i = 1; i <= number; i++) {
            final CheckBox checkBox = new CheckBox(context);
            checkBox.setId(i);
            checkBox.setTextSize(15);


            setChecked(checkBox, pageNumber, i);
            setListener(checkBox);


            checkBox.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
            checkBox.setText(answers.get(i - 1));
            checkboxContainer.addView(checkBox);


        }

        params.setPageNumber(pageNumber);
    }

}
