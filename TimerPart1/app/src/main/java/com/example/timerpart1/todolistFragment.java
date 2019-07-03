package com.example.timerpart1;

import android.app.Activity;
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

public class todolistFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todolist, container, false);

        TextView ex = view.findViewById(R.id.todoEx);
        ex.setText("Welcome! With this ToDo List feature, you can plan out tasks and events" +
                " for the day, week, or as long you would like." +
                " You can also add sub-tasks to divide-and-conquer your tasks." +
                " Would you like me to redirect you to the ToDo list?");


        Button btnOpen = view.findViewById(R.id.todoBtn);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), ListTransitionActivity.class);
                startActivity(in);
            }
        });


        return view;

    }
}
