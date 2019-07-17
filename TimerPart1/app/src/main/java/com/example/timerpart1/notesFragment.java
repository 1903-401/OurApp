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

public class notesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);

        TextView ex = view.findViewById(R.id.notesEx);
        ex.setText("Welcome! With this Notes feature, you can jot down and save" +
                " what's on your mind, whether its notes from class" +
                " or your grocery list." +
                " Would you like me to redirect you to the ToDo list?");


        Button btnOpen = view.findViewById(R.id.notesBtn);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), NotesTransitionActivity.class);
                startActivity(in);
            }
        });


        return view;

    }
}
