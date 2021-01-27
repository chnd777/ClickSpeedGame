package com.example.clickspeedgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

public class Activity2 extends AppCompatActivity {
    Button playagain,button1;
    TextView best;
    TextView pscore;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        playagain=findViewById(R.id.playagain);
        button1=findViewById(R.id.button1);
        pscore=findViewById(R.id.pscore);
        best=findViewById(R.id.best);
        score=MainActivity.getValue();
        pscore.setText("Score:"+score);
        SharedPreferences preferences=getSharedPreferences("PREFS",MODE_PRIVATE);
        int bestt= preferences.getInt("bestt",0);
        if(score > bestt){
            bestt=score;
            SharedPreferences.Editor editor=preferences.edit();
            editor.putInt("bestt",score);
            editor.apply();
        }
        best.setText("High score: "+bestt);
        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Activity2.this, MainActivity.class));
                finish();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor=preferences.edit();
                editor.putInt("bestt",0);
                editor.apply();
                best.setText("High score: "+0);
            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(Activity2.this, MainActivity.class));
        finish();
        moveTaskToBack(false);
    }
}