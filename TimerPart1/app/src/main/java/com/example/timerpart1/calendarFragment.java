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

public class calendarFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        TextView ex = view.findViewById(R.id.calendarEx);
        ex.setText("Welcome! This Calendar will help you keep track of events and deadlines." +
                " Would you like me to redirect you to the Calendar?");

        Button btnOpen = view.findViewById(R.id.calendarBtn);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), CalendarActivity.class);
                startActivity(in);
            }
        });


        return view;

    }


}
