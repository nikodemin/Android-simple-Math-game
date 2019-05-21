package com.mathgame;

import android.app.Activity;
import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

public class GameActivity extends Activity implements View.OnClickListener
{
    Button btn1;
    Button btn3;
    Button btn2;
    TextView firstMulText;
    TextView secondMulText;
    TextView scoreText;
    TextView levelText;
    int correctAnswer = 0;
    int currentLevel = 1;
    int score = 0;
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        btn1 = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        firstMulText = (TextView) findViewById(R.id.firstMultiplierText);
        secondMulText = (TextView) findViewById(R.id.secondMultiplierText);
        scoreText = (TextView) findViewById(R.id.scoreText);
        levelText = (TextView) findViewById(R.id.levelText);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

        createQuestion();
    }

    @Override
    public void onClick(View v)
    {
        int answerGiven = 0;
        switch (v.getId())
        {
            case R.id.button:
                answerGiven = Integer.parseInt(btn1.getText().toString());
                break;
            case R.id.button2:
                answerGiven = Integer.parseInt(btn2.getText().toString());
                break;
            case R.id.button3:
                answerGiven = Integer.parseInt(btn3.getText().toString());
                break;
        }

        Log.i("info",""+answerGiven);

        if(answerGiven==correctAnswer)
        {
            Toast.makeText(getApplicationContext(),
                    "Rigth answer!", Toast.LENGTH_SHORT).show();
            for (int i = 1; i <= currentLevel; i++)
            {
                score+=i;
            }
            currentLevel++;
        }
        else
        {
            Toast.makeText(getApplicationContext(),
                    "You failed!", Toast.LENGTH_SHORT).show();
            currentLevel = 1;
            score = 0;
        }
        createQuestion();
    }

    private void createQuestion()
    {
        scoreText.setText("Score: "+score);
        levelText.setText("Level: "+currentLevel);

        int first = 1+rand.nextInt(currentLevel);
        int second = 1+rand.nextInt(currentLevel);
        correctAnswer = first*second;

        firstMulText.setText(""+first);
        secondMulText.setText(""+second);

        first = correctAnswer;

        second = correctAnswer+rand.nextInt(6)-3;
        while (second==correctAnswer)
            second = correctAnswer+rand.nextInt(6)-3;

        int third = correctAnswer+rand.nextInt(6)-3;
        while (third==correctAnswer || third==second)
            third = correctAnswer+rand.nextInt(6)-3;

        switch (rand.nextInt(3))
        {
            case 0:
                btn1.setText(""+first);
                btn2.setText(""+second);
                btn3.setText(""+third);
                break;
            case 1:
                btn2.setText(""+first);
                btn3.setText(""+second);
                btn1.setText(""+third);
                break;
            case 2:
                btn3.setText(""+first);
                btn2.setText(""+second);
                btn1.setText(""+third);
                break;
        }
    }
}
