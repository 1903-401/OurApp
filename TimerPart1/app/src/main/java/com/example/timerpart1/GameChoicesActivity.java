package com.example.timerpart1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class GameChoicesActivity extends AppCompatActivity {

    private Button one;
    private Button two;
    private Button three;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_choices);

        // timer for break
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(GameChoicesActivity.this,
                        BreakOverActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);


        one = findViewById(R.id.games_one);
        two = findViewById(R.id.games_two);
        three = findViewById(R.id.games_three);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOne();
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTwo();
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openThree();
            }
        });

    }

    public void openOne() {
        Intent intent = new Intent(this, CatchStartActivity.class);
        startActivity(intent);
    }

    public void openTwo() {
        Intent intent = new Intent(this, StartMaze.class);
        startActivity(intent);
    }

    public void openThree() {
        Intent intent = new Intent(this, Version2GameActivity.class);
        startActivity(intent);
    }

}
