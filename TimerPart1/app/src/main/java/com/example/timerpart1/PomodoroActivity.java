package com.example.timerpart1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;

public class PomodoroActivity extends AppCompatActivity {

    // initializing the features we have on the page
    private TextView countdownText;
    private Button countdownButton;
    private Button buttonReset;
    private Button transitionButton;

    // setting the timer, giving it a starting point
    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private TextView explanation_text;

    private long SET_TIME = 1500000;
    private long timeLeftInMilliseconds = SET_TIME; // 1,500,000 = 25 minutes

    // for the progress bar
    private ProgressBar progressCircle;

    // not sure
    private enum TimerStatus {
        STARTED,
        STOPPED
    }

    private TimerStatus timerStatus = TimerStatus.STOPPED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomo);

        // initializing all the instance variables

        countdownText = findViewById(R.id.countdown_text);
        countdownButton = findViewById(R.id.start_button);
        buttonReset = findViewById(R.id.button_reset);
        explanation_text = findViewById(R.id.explanation);


        // setting up what the buttons should do
        countdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStop();
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });

        progressCircle = findViewById(R.id.progress);
        updateCountDownText();


    }


    public void openBreak() {
        Intent intent = new Intent(this, BreakActivity.class);
        startActivity(intent);
    }

    public void startStop() {
        if (timerRunning) {
            stopTimer();
        } else {
            startTimer();
            setProgressBarValues();
        }

    }

    public void startTimer() {

        // creating the timer
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds = l;
                updateCountDownText();
                progressCircle.setProgress((int) (timeLeftInMilliseconds / 1000));

            }

            @Override
            public void onFinish() {

                // conditions for when the timer is finished
                timerRunning = false;

                // initializing the progress bar values
                setProgressBarValues();
                openBreak();
            }
        }.start();

        timerRunning = true;
        updateButtons();

    }

    public void stopTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        updateButtons();
    }

    private void resetTimer() {
        timeLeftInMilliseconds = SET_TIME;
        updateCountDownText();
        updateButtons();
    }

    public void updateCountDownText() {

        int minutes = (int) (timeLeftInMilliseconds / 1000) / 60;
        int seconds = (int) (timeLeftInMilliseconds / 1000) % 60;

        String timeLeftText;

        // format for the time display
        timeLeftText = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        countdownText.setText(timeLeftText);
    }

    private void updateButtons() {

        if (timerRunning) {
            buttonReset.setVisibility(View.INVISIBLE);
            countdownButton.setText("Pause");

        } else {
            countdownButton.setText("Start");
            buttonReset.setVisibility(View.INVISIBLE);

            // if the timer is finished

            if (timeLeftInMilliseconds < 1000) {
                countdownButton.setVisibility(View.INVISIBLE);
            } else {
                countdownButton.setVisibility(View.VISIBLE);
            }

            // if the time we have left is less than the start time, means we want the reset button
            // to be available during the countdown

            if (timeLeftInMilliseconds < SET_TIME){
                buttonReset.setVisibility(View.VISIBLE);
            } else {
                buttonReset.setVisibility(View.INVISIBLE);
            }

        }
    }

    private void setProgressBarValues() {

        progressCircle.setMax((int) SET_TIME / 1000);
        progressCircle.setProgress((int) SET_TIME / 1000);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // saving the important variables we want to keep
        outState.putLong("startTime", timeLeftInMilliseconds);
        outState.putBoolean("timerRunning", timerRunning);

        // this is to stop the app from crashing
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // recalling the saved variables
        timeLeftInMilliseconds = savedInstanceState.getLong("startTime");
        timerRunning = savedInstanceState.getBoolean("timerRunning");

        updateCountDownText();
        updateButtons();

        if(timerRunning) {

            timeLeftInMilliseconds = SET_TIME - System.currentTimeMillis();

            if(timeLeftInMilliseconds < 0) {
                timeLeftInMilliseconds = 0;
                timerRunning = false;
                updateCountDownText();
                updateButtons();
            } else {
                startTimer();
            }
        }

    }


}
