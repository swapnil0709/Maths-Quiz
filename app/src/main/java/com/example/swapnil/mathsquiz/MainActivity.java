package com.example.swapnil.mathsquiz;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button playButton;
    Button playAgainButton;
    TextView questionTextView;
    TextView timerTextView;
    TextView scoreTextView;
    TextView resultTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    android.support.v7.widget.GridLayout gridLayout;
    CountDownTimer countDownTimer;
    int correctOptionScore = 0;
    int totalQuestions = 0;
    ArrayList<Integer> answerOptions = new ArrayList<Integer>();
    int correctAnswer;

    public void questions(View view) {
        Random rand = new Random();
        int num_1 = rand.nextInt(101);
        int num_2 = rand.nextInt(101);
        questionTextView.setText(Integer.toString(num_1) + " + " + Integer.toString(num_2));
        correctAnswer = rand.nextInt(4);
        for (int i = 0; i < 4; i++) {
            if (correctAnswer == i) {
                answerOptions.add(num_1 + num_2);
            } else {
                int wrongAnswer = rand.nextInt(201);
                while (wrongAnswer == (num_1 + num_2)) {
                    wrongAnswer = rand.nextInt(201);

                }
                answerOptions.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answerOptions.get(0)));
        button1.setText(Integer.toString(answerOptions.get(1)));
        button2.setText(Integer.toString(answerOptions.get(2)));
        button3.setText(Integer.toString(answerOptions.get(3)));
        answerOptions.clear();


    }

    public void invisible() {
        timerTextView.setVisibility(View.INVISIBLE);
        scoreTextView.setVisibility(View.INVISIBLE);
        questionTextView.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.INVISIBLE);
        resultTextView.setVisibility(View.INVISIBLE);
    }

    public void resetGame(View view) {
        questions(findViewById(R.id.questionTextView));
        playButton(findViewById(R.id.playButton));
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText(" ");
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        correctOptionScore=0;
        totalQuestions=0;
        scoreTextView.setText(Integer.toString(correctOptionScore) + "/" + Integer.toString(totalQuestions));

    }

    public void playButton(View view) {
        playButton.setVisibility(View.INVISIBLE);
        timerTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        questionTextView.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        resultTextView.setVisibility(View.VISIBLE);
        countDownTimer = new CountDownTimer(30000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                int timeLeft = (int) (millisUntilFinished / 1000);
                if (timeLeft <= 9) {
                    timerTextView.setText(String.valueOf("0" + timeLeft + "s"));
                } else {
                    timerTextView.setText(String.valueOf(timeLeft + "s"));
                }

            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                resultTextView.setText(" ");
                countDownTimer.cancel();
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();
    }

    public void chhosenAnswer(View view) {
        if (view.getTag().toString().equals(Integer.toString(correctAnswer))) {
            resultTextView.setText("Correct :)");
            correctOptionScore++;
        } else {
            resultTextView.setText("Wrong :(");

        }
        totalQuestions++;
        scoreTextView.setText(Integer.toString(correctOptionScore) + "/" + Integer.toString(totalQuestions));
        questions(findViewById(R.id.timerTextView));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playButton = (Button) findViewById(R.id.playButton);
        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        questionTextView = (TextView) findViewById(R.id.questionTextView);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        gridLayout = findViewById(R.id.gridLayout);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        scoreTextView = (TextView) findViewById(R.id.scoreTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextView.setText(" ");
        invisible();
        playAgainButton.setVisibility(View.INVISIBLE);
        playButton.setVisibility(View.VISIBLE);
        questions(findViewById(R.id.timerTextView));


    }
}
