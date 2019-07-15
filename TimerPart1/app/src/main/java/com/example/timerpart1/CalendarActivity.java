package com.example.timerpart1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CalendarActivity extends AppCompatActivity {

    CustomCalendarView customCalendarView;
    private Button transitionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_first);

        customCalendarView = (CustomCalendarView) findViewById(R.id.custom_calendar_view);



        // setting up the transition through the button on timer page

        transitionButton = (Button) findViewById(R.id.backToFragment);
        transitionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Creates a new intent that will ultimately connect to the second activity
                goBack();
            }
        });


    }

    public void goBack() {
        Intent intent = new Intent(this, calendarFragment.class);
        startActivity(intent);
    }

}

