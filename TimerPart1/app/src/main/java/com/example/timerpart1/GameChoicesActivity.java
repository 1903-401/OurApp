package com.example.timerpart1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameChoicesActivity extends AppCompatActivity {

    private Button one;
    private Button two;
    private Button three;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_choices);

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

    }

    public void openOne() {
        Intent intent = new Intent(this, CatchStartActivity.class);
        startActivity(intent);
    }

    public void openTwo() {
        Intent intent = new Intent(this, StartMaze.class);
        startActivity(intent);
    }

}
