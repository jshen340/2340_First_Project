package com.example.a2340projectone.ui.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

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
                String sectionDash = binding.sectioninput.getText().toString();
                String roomDash = binding.roominput.getText().toString();
                String[] days = new String[5];

                if (checkIfAnyDaySelected()) {
                    days[0] = textIfChecked(binding.mondayCheckbox, "M");
                    days[1] = textIfChecked(binding.tuesdayCheckbox, "T");
                    days[2] = textIfChecked(binding.wedCheckbox, "W");
                    days[3] = textIfChecked(binding.thursCheckbox, "Th");
                    days[4] = textIfChecked(binding.friCheckbox, "F");
                }
                String daysString = "";
                Courses toBeAdded = new Courses(courseDash, instructorDash, daysString, sectionDash, buildingDash, roomDash);
                for (int i = 0; i < days.length; i++) {
                    if (!days[i].equals("")) {
                        toBeAdded.getDaysClassHeld()[i] = true;
                        if (i == 0 || daysString.equals("")) {
                            daysString += days[i];
                        } else {
                            daysString += ", " + days[i];
                        }
                    } else {
                        toBeAdded.getDaysClassHeld()[i] = false;
                    }
                }

                if (!CourseList.coursesAvailable.contains(courseDash)) {
                    CourseList.coursesAvailable.add(courseDash);
                }
                toBeAdded.setDay(daysString);

                if (toBeAdded.checkTime(binding.timestartinput.getText().toString())) {
                    toBeAdded.setTimeStart(binding.timestartinput.getText().toString());
                } else {
                    Toast myToast = Toast.makeText(getActivity(), "Start time should be HH:MM!", Toast.LENGTH_SHORT);
                    myToast.show();
                }

                if (toBeAdded.checkTime(binding.timeendinput.getText().toString())) {
                    toBeAdded.setTimeEnd(binding.timeendinput.getText().toString());
                } else {
                    Toast myToast = Toast.makeText(getActivity(), "End time should be HH:MM!", Toast.LENGTH_SHORT);
                    myToast.show();
                }


                if (toBeAdded.checkTime(binding.timestartinput.getText().toString()) && toBeAdded.checkTime(binding.timeendinput.getText().toString())) {
                    // Clear input fields
                    binding.coursenameinput.setText("");
                    binding.instructorinput.setText("");
                    binding.buildinginput.setText("");
                    binding.timestartinput.setText("");
                    binding.timeendinput.setText("");
                    binding.sectioninput.setText("");
                    binding.roominput.setText("");
                    // Navigate back to coursedash
                    // Add a new course
                    CourseList.courses.add(toBeAdded);
                    NavHostFragment.findNavController(AddCourse.this).navigate(R.id.action_addCourse_to_coursedash);
                }

            }
        });
    }

    private boolean checkIfAnyDaySelected() {
        return binding.mondayCheckbox.isChecked() || binding.tuesdayCheckbox.isChecked()
                || binding.wedCheckbox.isChecked() || binding.thursCheckbox.isChecked() ||
                binding.friCheckbox.isChecked();
    }

    private String textIfChecked(CheckBox c, String s) {
        if (c.isChecked()) {
            return s;
        }
        return "";
    }
}