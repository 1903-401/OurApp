package com.example.timerpart1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class introFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_intro, container, false);

        TextView ex = view.findViewById(R.id.introEx);
        ex.setText("Hi, I'm Blink! I will be your personal assistant" +
                " Since you are already here, I am sure you understand" +
                " the importance of time management. I am here to give you motivation," +
                " remind you of your tasks, and help you through proven scientific methods" +
                " for productivity, such as the Pomodoro Technique. Of course, we" +
                " will also have fun with games, which you will only get access to" +
                " after doing some studying. Are you ready? Swipe right to use the " +
                " navigation bar.");

        return view;
    }

}
