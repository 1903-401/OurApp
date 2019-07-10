package com.example.timerpart1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class timerFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        TextView ex = view.findViewById(R.id.timerEx);
        ex.setText("Welcome! This Timer will help you manager your time efficiently." +
                " This is a better option than the pomodoro if you lke to control the" +
                " amount of time you work for, rather than the pre-determined limit." +
                " Would you like me to redirect you to the Timer?");


        Button btnOpen = view.findViewById(R.id.timerBtn);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), TimerTransitionActivity.class);
                startActivity(in);
            }
        });


        return view;
    }
}
