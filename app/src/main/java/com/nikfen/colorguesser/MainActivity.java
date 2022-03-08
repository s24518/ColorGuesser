package com.nikfen.colorguesser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void twoXtwoPole(View view) {
        startActivity(new Intent(this, DoublePole.class));
    }


    public void threeXthreePole(View view) {

        startActivity(new Intent(this, TriplePole.class));
    }
}
