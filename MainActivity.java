package com.example.takethetest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView totalquestions;
    TextView questions;
    Button ansA,ansB,ansC,ansD;
    Button Submit;
    int score=0;
    int totalQuestion = QNA.questions.length;
    int currentQuestionIndex = 0;
    String Answer = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalquestions= findViewById(R.id.totalQuestions);
        questions= findViewById(R.id.question);
        ansA=findViewById(R.id.button);
        ansB=findViewById(R.id.button2);
        ansC=findViewById(R.id.button3);
        ansD=findViewById(R.id.button4);
        Submit=findViewById(R.id.submit_btn);
        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        Submit.setOnClickListener(this);
        totalquestions.setText("Total questions : "+totalQuestion);

        loadNewQuestion();



    }

    @Override
    public void onClick(View view) {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);
        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.submit_btn){
            if(Answer.equals(QNA.answers[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();


        }else{
            //choices button clicked
              Answer= clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.green(-78));

        }

    }

    private void loadNewQuestion() {
        if(currentQuestionIndex == totalQuestion ){
            finishQuiz();
            return;
        }

        questions.setText(QNA.questions[currentQuestionIndex]);
        ansA.setText(QNA.options[currentQuestionIndex][0]);
        ansB.setText(QNA.options[currentQuestionIndex][1]);
        ansC.setText(QNA.options[currentQuestionIndex][2]);
        ansD.setText(QNA.options[currentQuestionIndex][3]);
    }

    private void finishQuiz() {
        String passStatus = "";
        if(score > totalQuestion*0.60){
            passStatus = "Qualified";
        }else{
            passStatus = "Try Again Later";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+ score+" out of "+ totalQuestion)
                .setPositiveButton("Restart",(dialogInterface, i) -> restart() )
                .setCancelable(false)
                .show();

    }
    void restart() {
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }
}