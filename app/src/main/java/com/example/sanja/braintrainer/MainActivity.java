package com.example.sanja.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    ArrayList<Integer> answers = new ArrayList<>();
    int locOfCorrectAnswer;
    int score=0;
    int numOfQuestions=0;
    TextView textViewResult;
    TextView textViewPoints;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView textViewQues;
    TextView timer;
    android.support.v7.widget.GridLayout gridLayout;

    public void generateQuestion(){
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        int realAns = a+b;
        Log.d("msg",""+realAns);

        locOfCorrectAnswer = random.nextInt(4);
        answers.clear();
        Log.d("loc of ans","> "+locOfCorrectAnswer);

        for (int i=0;i<4;i++){
            if(i==locOfCorrectAnswer){
                answers.add(realAns);
            }
            else{
                int incorrectAns = random.nextInt(41);
                while(incorrectAns==realAns){
                    incorrectAns = random.nextInt(41);
                }
                answers.add(incorrectAns);
            }
        }

        textViewQues.setText(a+" + "+b);

        button0.setText(""+answers.get(0));
        button1.setText(""+answers.get(1));
        button2.setText(""+answers.get(2));
        button3.setText(""+answers.get(3));

    }

    public void chooseAnswer(View view){
        //Log.d("Tag", ""+view.getTag());
        if(view.getTag().toString().equals(Integer.toString(locOfCorrectAnswer))){
            score++;
            textViewResult.setText("Correct!");
        }
        else{
            textViewResult.setText("Wrong!");
        }

        numOfQuestions++;
        textViewPoints.setText(score+"/"+numOfQuestions);
        generateQuestion();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.buttonStart);
        textViewQues = findViewById(R.id.textViewQuestion);
        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        textViewResult = (TextView) findViewById(R.id.textViewResult);
        textViewPoints = (TextView) findViewById(R.id.textViewPoints);
        timer = (TextView) findViewById(R.id.textViewTimer);
        gridLayout = (android.support.v7.widget.GridLayout) findViewById(R.id.gridLayout);

        textViewPoints.setText(score+"/"+numOfQuestions);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                textViewResult.setText("");
                textViewPoints.setText(score+"/"+numOfQuestions);
                generateQuestion();
                startButton.setVisibility(View.INVISIBLE);
                textViewQues.setVisibility(View.VISIBLE);
                gridLayout.setVisibility(View.VISIBLE);

                new CountDownTimer(30100, 1000){

                    @Override
                    public void onTick(long l) {
                        timer.setText(""+l/1000+"s");
                    }

                    @Override
                    public void onFinish() {
                        timer.setText("0s");
                        gridLayout.setVisibility(View.INVISIBLE);
                        startButton.setVisibility(View.VISIBLE);
                        double d = (double)score/numOfQuestions;
                        d = d*100;
                        Log.d("d", "result > "+(score/numOfQuestions)*100);
                        d = Math.round(d*100.0)/100.0;
                        textViewResult.setText("Your score is "+d+"%");
                        playAgain();
                    }
                }.start();
            }
        });

    }

    private void playAgain() {

        startButton.setText("Again!");
        score=0;
        numOfQuestions=0;

    }


}
