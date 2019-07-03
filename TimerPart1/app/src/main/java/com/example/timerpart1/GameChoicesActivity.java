package com.example.timerpart1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
        two = findViewById(R.id.games_one);
        three = findViewById(R.id.games_one);


    }
}
