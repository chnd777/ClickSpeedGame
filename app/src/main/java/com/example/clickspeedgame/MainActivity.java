package com.example.clickspeedgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.view.View.*;

public class MainActivity extends AppCompatActivity {
    RelativeLayout field;
    TextView score,time;
    Button play;
    public static int count =0;
    public int counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play=findViewById(R.id.play);
        field=findViewById(R.id.field);
        score=findViewById(R.id.score);
        time=findViewById(R.id.time);
        score.setText("0");
        time.setText("10 seconds");
        field.setEnabled(false);
        play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                play();
            }
        });
        field.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                count++;
                score.setText(""+count);
            }
        });
    }
    public void play(){
        play.setVisibility(View.GONE);
        count=0;
        field.setEnabled(true);
        startTime();

    }
    public void startTime(){
        new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                counter++;
                time.setText(""+(10-counter)+" sec");
            }
            @Override
            public void onFinish() {
                field.setEnabled(false);
                Intent intent=new Intent(MainActivity.this, Activity2.class);
                intent.putExtra("score", count);
                startActivity(intent);
            }
        }.start();
    }
    public static int getValue(){
        return count;
    }
}