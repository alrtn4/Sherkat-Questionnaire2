package com.example.ideapad510.sherkatquestionear.Questionnaire;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.ideapad510.sherkatquestionear.New.NewActivity;
import com.example.ideapad510.sherkatquestionear.Params.Params;
import com.example.ideapad510.sherkatquestionear.R;
import com.example.ideapad510.sherkatquestionear.ql.qlController;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

public class QuestionnaireActivity extends AppCompatActivity {

    private ListView listView;
    private static ArrayList<String> questionnaires  = new ArrayList<>();
    private QuestionnaireController questionnaireController = new QuestionnaireController(this);
    private qlController qlControllerr = new qlController(this);
    String TAG = "porseshname";
    private Params params = Params.getInstance();
//    ArrayList<String> questionnaireIdd =new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionnaire);


        questionnaires = questionnaireController.getQuestionnaireList();
/*
        //get List of porseshnameha
        String username = params.getUsername();
        String jmrCode = qlControllerr.getJmrcode(username);
        String function = qlControllerr.getFunction(jmrCode);

        questionnaires = questionnaireController.functionParse(function);

*/
        listView = findViewById(R.id.questionnaireListView2);
        QuestionnaireListAdapter adapter = new QuestionnaireListAdapter(this, questionnaires);
        listView.setAdapter(adapter);


        onClickListView();
    }

    private void onClickListView(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                //to send parameters to next activities
                questionnaireController.paramsSet(position);

                Intent intent = new Intent(QuestionnaireActivity.this, NewActivity.class);
                startActivity(intent);
            }
        });
    }



/*
    private ArrayList functionParse(String function){
        String[] questionnaireId = function.split("-");

        ArrayList<String> questionnaires = questionnaireController.getQuestionnaires();
        //qtArray returns questionnaire list which are in function string
        ArrayList<String> qtArray = new ArrayList<>();


        for(String id : questionnaireId){
            questionnaireIdd.add(id);

            int idd = Integer.valueOf(id);
            qtArray.add(questionnaires.get(idd-1));
        }


        return qtArray;
    }
*/

}
