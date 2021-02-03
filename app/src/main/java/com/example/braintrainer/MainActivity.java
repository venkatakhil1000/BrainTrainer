package com.example.braintrainer;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    TextView sumTextView;
    int locationOfCorrectAnswer;
    int wrongAnswer;
    TextView decisionTextView;
    int score=0;
    int numberOfQuestions=0;
    TextView scoreTextView;
    TextView timerTextView;
    Button playAgainButton;
    ConstraintLayout gameLayout;

    Button button0;
    Button button1;
    Button button2;
    Button button3;

    ArrayList<Integer> answers=new ArrayList<Integer>();

    public void playAgain(View view){
        score=0;
        numberOfQuestions=0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        decisionTextView.setText("");

        new CountDownTimer(30100,1000){
            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                decisionTextView.setText("Time's Up!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void chooseAnswer(View view){
       if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
           decisionTextView.setText("Correct!");
           decisionTextView.setTextColor(Color.parseColor("#43A047"));
           score++;
       }else{
           decisionTextView.setText("Wrong :(");
           decisionTextView.setTextColor(Color.RED);
       }
       numberOfQuestions++;
       scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
       newQuestion();
    }

    public void newQuestion(){
        Random random=new Random();

        int a=random.nextInt(30);
        int b=random.nextInt(30);

        sumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));

        locationOfCorrectAnswer=random.nextInt(4);

        answers.clear();

        for(int i=0;i<4;i++){
            if(i==locationOfCorrectAnswer){
                answers.add(a+b);
            }else{
                wrongAnswer=random.nextInt(50);

                while(wrongAnswer==a+b){
                    wrongAnswer=random.nextInt(50);
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton=findViewById(R.id.goButton);

        sumTextView=findViewById(R.id.questionTextView);

        decisionTextView=findViewById(R.id.descisionTextView);

        scoreTextView=findViewById(R.id.scoreTextView);

        timerTextView=findViewById(R.id.timerTextView);

        playAgainButton=findViewById(R.id.playAgainButton);

        gameLayout=findViewById(R.id.gameLayout);

        button0=findViewById(R.id.button0);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);

        goButton.setVisibility(View.VISIBLE);

        gameLayout.setVisibility(View.INVISIBLE);
    }
}