package com.example.timerpart1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BreakOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_break_over);

        TextView ex = findViewById(R.id.breakOverEx);
        ex.setText("Break time is over! You only have access to the games" +
                " for the set twenty minutes. You will be able to access the page" +
                " once you study either for the set twenty five minutes of the" +
                " pomodoro timer or what you set for the personal timer." +
                " Happy studying!!");

        Button one = findViewById(R.id.thisToPomo);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOne();
            }
        });

        Button two = findViewById(R.id.thisToTimer);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTwo();
            }
        });

        Button three = findViewById(R.id.thisToHome);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openThree();
            }
        });
    }

    public void openOne(){
        Intent intent = new Intent(getApplicationContext(), PomodoroActivity.class);
        startActivity(intent);
    }

    public void openTwo(){
        Intent intent = new Intent(getApplicationContext(), TimerActivity.class);
        startActivity(intent);
    }

    public void openThree(){
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(intent);
    }
}
