package com.example.a2340projectone.ui.courses;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.a2340projectone.R;
import com.example.a2340projectone.todo_activity.TodoActivity;

public class CoursesFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_courses, container, false);
        Button gotodo = view.findViewById(R.id.gotodo_btn);
        gotodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TodoActivity.class);
                startActivity(intent);
            }
        });
        Button course = view.findViewById(R.id.class1);
        course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TodoActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}