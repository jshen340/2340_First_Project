package com.example.a2340projectone;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a2340projectone.CourseList;
import com.example.a2340projectone.R;
import com.example.a2340projectone.databinding.FragmentAddCourseBinding;

public class AddCourse extends Fragment {
    private FragmentAddCourseBinding binding;

    public AddCourse() {
        // Required empty public constructor
    }

    public static AddCourse newInstance() {
        AddCourse fragment = new AddCourse();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddCourseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.addtodash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve data from input fields
                String courseDash = binding.coursenameinput.getText().toString();
                String instructorDash = binding.instructorinput.getText().toString();
                String buildingDash = binding.buildinginput.getText().toString();
                String dayDash = binding.dayinput.getText().toString();
                String timestartDash = binding.timestartinput.getText().toString();
                String timeendDash = binding.timeendinput.getText().toString();
                String sectionDash = binding.sectioninput.getText().toString();
                String roomDash = binding.roominput.getText().toString();

                // Add a new course
                CourseList.courses.add(new Courses(courseDash, instructorDash, buildingDash, dayDash, timestartDash, timeendDash, sectionDash, roomDash));

                // Clear input fields
                binding.coursenameinput.setText("");
                binding.instructorinput.setText("");
                binding.buildinginput.setText("");
                binding.dayinput.setText("");
                binding.timestartinput.setText("");
                binding.timeendinput.setText("");
                binding.sectioninput.setText("");
                binding.roominput.setText("");

                // Navigate back to coursedash
                NavHostFragment.findNavController(AddCourse.this).navigate(R.id.action_addCourse_to_coursedash);
            }
        });
    }
}