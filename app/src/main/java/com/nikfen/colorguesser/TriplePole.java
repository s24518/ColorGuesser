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

public class TriplePole extends AppCompatActivity {

    final String SAVED_SCROE = "SAVED_SCORE_TRIPLE";

    Button[] buttons = new Button[9];
    TextView score, bestScore;
    boolean[] buttonContainer = new boolean[9];
    int scoreInt;

    SharedPreferences sPref;
    SharedPreferences.Editor editor;

    int randomNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triple_pole);
        buttons[0] = (Button) findViewById(R.id.button1Triple);
        buttons[1] = (Button) findViewById(R.id.button2Triple);
        buttons[2] = (Button) findViewById(R.id.button3Triple);
        buttons[3] = (Button) findViewById(R.id.button4Triple);
        buttons[4] = (Button) findViewById(R.id.button5Triple);
        buttons[5] = (Button) findViewById(R.id.button6Triple);
        buttons[6] = (Button) findViewById(R.id.button7Triple);
        buttons[7] = (Button) findViewById(R.id.button8Triple);
        buttons[8] = (Button) findViewById(R.id.button9Triple);

        score = (TextView) findViewById(R.id.scoreTriple);
        bestScore = (TextView) findViewById(R.id.best_scoreTriple);

        sPref = getSharedPreferences("MyPref", MODE_PRIVATE);
        scoreInt = 0;

        makePole();

    }

    public void button1Triple(View view){ checkWin(0); }
    public void button2Triple(View view){ checkWin(1); }
    public void button3Triple(View view){ checkWin(2); }
    public void button4Triple(View view){ checkWin(3); }
    public void button5Triple(View view){ checkWin(4); }
    public void button6Triple(View view){ checkWin(5); }
    public void button7Triple(View view){ checkWin(6); }
    public void button8Triple(View view){ checkWin(7); }
    public void button9Triple(View view){ checkWin(8); }


    public void makePole() {
        score.setText(String.valueOf(scoreInt));
        bestScore.setText(String.valueOf(sPref.getInt(SAVED_SCROE, 0)));
        Random rand = new Random();
        int r = rand.nextInt(225);
        int g = rand.nextInt(225);
        int b = rand.nextInt(225);
        int randomColor = Color.rgb(r, g, b);
        int anotherRandomColor = Color.rgb(r + 20, g + 20, b + 20);
        for (int i = 0; i < 9; i++) {
            buttons[i].setBackgroundColor(randomColor);
            buttonContainer[i] = false;
        }

        randomNum = rand.nextInt(9);
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
            checkRecord();
            fail();
        }
    }

    public void fail() {
        checkRecord();
        startActivity(new Intent(this, MainActivity.class));
    }
    public void checkRecord(){
        if (scoreInt > sPref.getInt(SAVED_SCROE, 0)) {
            SharedPreferences.Editor editor = sPref.edit();
            editor.putInt(SAVED_SCROE, scoreInt);
            editor.apply();
        }
    }
    @Override
    public void onBackPressed(){
        checkRecord();
        fail();
    }
}