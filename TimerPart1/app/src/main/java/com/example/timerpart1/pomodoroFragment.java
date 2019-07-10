package com.example.timerpart1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class pomodoroFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pomodoro, container, false);

        TextView ex = view.findViewById(R.id.pomoEx);
        ex.setText("Welcome! This Timer utilizes the Pomodoro Technique." +
                " It allots 25 minutes to completing a task, followed by a 5 minute break period." +
                " Would you like me to redirect you to the Pomodoro Timer?");


        Button btnOpen = view.findViewById(R.id.pomodoroBtn);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), PomoTransitionActivity.class);
                startActivity(in);
            }
        });


        return view;
    }
}
