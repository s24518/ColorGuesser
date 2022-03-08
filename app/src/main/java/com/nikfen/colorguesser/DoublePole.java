package com.nikfen.colorguesser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class DoublePole extends AppCompatActivity {

    final String SAVED_SCROE = "SAVED_SCORE";

    Button[] buttons = new Button[4];
    TextView score, bestScore;
    boolean[] buttonContainer = new boolean[4];
    int scoreInt;

    SharedPreferences sPref;
    SharedPreferences.Editor editor;

    int randomNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_pole);
        buttons[0] = (Button) findViewById(R.id.button1);
        buttons[1] = (Button) findViewById(R.id.button2);
        buttons[2] = (Button) findViewById(R.id.button3);
        buttons[3] = (Button) findViewById(R.id.button4);
        score = (TextView) findViewById(R.id.score);
        bestScore = (TextView) findViewById(R.id.best_score);
        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        scoreInt = 0;

        makePole();

    }

    public void button1(View view) {
        checkWin(0);
    }

    public void button2(View view) {
        checkWin(1);
    }

    public void button3(View view) { checkWin(2); }

    public void button4(View view) { checkWin(3); }

    public void makePole() {
        score.setText(String.valueOf(scoreInt));
        bestScore.setText(String.valueOf(sPref.getInt(SAVED_SCROE, 0)));
        Random rand = new Random();
        int r = rand.nextInt(225);
        int g = rand.nextInt(225);
        int b = rand.nextInt(225);
        int randomColor = Color.rgb(r, g, b);
        int anotherRandomColor = Color.rgb(r + 20, g + 20, b + 20);
        for (int i = 0; i < 4; i++) {
            buttons[i].setBackgroundColor(randomColor);
            buttonContainer[i] = false;
        }

        randomNum = rand.nextInt(4);
        buttons[randomNum].setBackgroundColor(anotherRandomColor);
        buttonContainer[randomNum] = true;
    }

    public void checkWin(int buttonId) {
        if (buttonContainer[buttonId]) {
            Toast.makeText(this, "Good!", Toast.LENGTH_SHORT).show();
            scoreInt++;
            makePole();
        } else {
            Toast.makeText(this, "You failed!", Toast.LENGTH_SHORT).show();
            if (scoreInt > sPref.getInt(SAVED_SCROE, 0)) {
                SharedPreferences.Editor editor = sPref.edit();
                editor.putInt(SAVED_SCROE, scoreInt);
                editor.apply();
            }
            fail();
        }
    }


    public void fail() {
        startActivity(new Intent(this, MainActivity.class));
    }
}