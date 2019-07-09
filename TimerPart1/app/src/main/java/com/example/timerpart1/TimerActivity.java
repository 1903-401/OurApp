package com.example.timerpart1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class TimerActivity extends AppCompatActivity {

    // all the instance variables

    private EditText mEditTextInput;
    private TextView mTextViewCountdown;
    private Button mButtonStartPause;
    private Button mButtonReset;
    private Button mButtonSet;
    private Button transitionButton;

    private CountDownTimer mCountDownTimer;
    private boolean mTimeRunning;

    private long mStartTimeInMillis; // the time that will be from input
    private long mTimeLeftInMillis;

    /* Problem: Every time we rotate, our timer lags behand regular timers because it pauses for a
    little bit

    Potential solutions - 1) put timer on a service so that it runs independently from the activity
    2) call onTick method more often (interval less than 1000) so the mTimeLeftInMillis would
    update more often and have a more up-to-date value when we create the activity

    3 - the one we chose: a new variable - we save the time where our timer is supposed to end  */

    private long mEndTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        // initializing all the instance variables

        mEditTextInput = findViewById(R.id.edit_text_input);
        mTextViewCountdown = findViewById(R.id.text_view_countdown);
        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonReset = findViewById(R.id.button_reset);
        mButtonSet = findViewById(R.id.button_set);

        // the button for user input on time limit
        mButtonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = mEditTextInput.getText().toString();

                // error for when the user does not put in time (leaves it blank)
                if (input.length() == 0) {
                    Toast.makeText(TimerActivity.this, "Field can't be empty", Toast.LENGTH_SHORT);
                    return;
                }

                // translating the information into a format to be used
                long millisInput = Long.parseLong(input) * 60000;

                // error if the user actually puts in 0 as the value
                if (millisInput == 0) {
                    Toast.makeText(TimerActivity.this, "Please enter a positive number", Toast.LENGTH_SHORT);
                    return;
                }

                setTime(millisInput);
                mEditTextInput.setText("");
            }
        });


        // the two options for the start/pause button:
        // if timer is already started, it means it should pause
        // otherwise just start

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTimeRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        // reset button
        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });

        // setting up the transition through the button on timer page


    }

    // will start the time using the input data from the user
    private void setTime(long milliseconds) {
        mStartTimeInMillis = milliseconds;
        resetTimer();
        closeKeyBoard();
    }

    // where it all starts: countdown, pause and reset buttons show up
    private void startTimer() {

        // we can use the mEndTime variable to exactly recreate the timer, no matter how our up-to-date
        // our countdown interval may be, we know when our timer is supposed to end

        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;

        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {

                // conditions for when the timer is finished
                mTimeRunning = false;
                openBreak();
                updateButtons();


            }
        }.start(); // can do this because the whole thing in the bracket
        // above is for the countdowntimer, which is a thing


        // conditions for when the start button is clicked
        mTimeRunning = true;
        updateButtons();

    }

    public void openBreak() {
        Intent intent = new Intent(this, BreakActivity.class);
        startActivity(intent);
    }

    // called when someone clicks on start/pause button and the timer is
    // already running

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimeRunning = false;
        updateButtons();
    }

    // conditions for when we click reset button, includes the button going away
    // and the timer text going back to where it started
    private void resetTimer() {
        mTimeLeftInMillis = mStartTimeInMillis;
        updateCountDownText();
        updateButtons();
    }

    // most important part: keeps track of the different components and conversions
    // formatting changed to include hours

    private void updateCountDownText() {
        int hours = (int) (mTimeLeftInMillis / 1000) / 3600;
        int minutes = (int) ((mTimeLeftInMillis / 1000) % 3600) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted;
        if (hours > 0) {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%d:%02d:%02d", hours, minutes, seconds);
        } else {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%02d:%02d", minutes, seconds);
        }


        mTextViewCountdown.setText(timeLeftFormatted);
    }

    // simpler way to update the buttons instead of doing it repeatedly all over the place
    private void updateButtons() {

        // easy when the timer is already on, because we only need to do two things
        // Because this whole condition is based on whether or not the boolean variable is true,
        // we HAVE to set the value before calling this updateButtons method

        if (mTimeRunning) {
            mEditTextInput.setVisibility(View.INVISIBLE);
            mButtonSet.setVisibility(View.INVISIBLE);
            mButtonReset.setVisibility(View.INVISIBLE);
            mButtonStartPause.setText("Pause");
        }

        /* this is harder because if the boolean is false, it could mean the timer is paused, or
        hasn't been started, or has finished - all of which call for different conditions */

        else {
            mEditTextInput.setVisibility(View.VISIBLE);
            mButtonSet.setVisibility(View.VISIBLE);
            mButtonStartPause.setText("Start");

            /* to check if the timer is finished, we have to do this if-statement - not checking
            against zero because our onTick method is only called every one second,
            our mTimeLeftInMillis won't exactly be zero - we want the pause button to be invisible
            if the timer is already done */

            if (mTimeLeftInMillis < 1000) {
                mButtonStartPause.setVisibility(View.INVISIBLE);

            } else {
                mButtonStartPause.setVisibility(View.VISIBLE);
            }

            /* other condition is if the time we have left is less than the start time, which would
            mean the timer is currently running, we want our reset button to be visible  */

            if (mTimeLeftInMillis < mStartTimeInMillis) {
                mButtonReset.setVisibility(View.VISIBLE);

            } else {
                mButtonReset.setVisibility(View.INVISIBLE);
            }
        }

    }


    // closing the keyboard when they are done entering values, instead of having to do it manually

    private void closeKeyBoard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /*     Problem: Our timer resets every time we change the orientation (vertical vs horizontal),
    because our activity gets completely restarted every time we move it - the runtime configuration
    changes

    Note: some of the objects on Android Studio (like EditText) automatically update when we
    switch the orientation

    Solution: we have to save and recall our variables, using a combination of two methods

    onSaveInstanceState - used to store data before pausing the activity / or restarting by changing
    something - allowing a view to generate a representation of its internal state that can later be
    used to create a new instance with that same state

    onRestoreInstanceState - called to restore any state that had previously been frozen by
    onSaveInstanceState - called after onStart() when the activity is being re-initialized from a
    previously saved state    */


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // format: saving it using the outState bundle, using key striing we will call in the
        // restoring process

        outState.putLong("startTimeInMillis", mStartTimeInMillis);
        outState.putLong("millisLeft", mTimeLeftInMillis);
        outState.putBoolean("timerRunning", mTimeRunning);
        outState.putLong("endTime", mEndTime);


        // this is to stop the app from crashing
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // recalling saved variables using the key string

        mStartTimeInMillis = savedInstanceState.getLong("startTimeInMillis");
        mTimeLeftInMillis = savedInstanceState.getLong("millisLeft");
        mTimeRunning = savedInstanceState.getBoolean("timerRunning");

        updateCountDownText();
        updateButtons();

        if (mTimeRunning) {

            // only need to save the mEndTime variable if the time is running
            mEndTime = savedInstanceState.getLong("endTime");
            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();

            if (mTimeLeftInMillis < 0) {
                mTimeLeftInMillis = 0;
                mTimeRunning = false;
                updateCountDownText();
                updateButtons();
            } else {
                startTimer();
            }
        }


    }
}
