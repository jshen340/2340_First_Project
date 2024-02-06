package com.example.a2340projectone.ui.dashboard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.a2340projectone.MainActivity;
import com.example.a2340projectone.R;
import com.example.a2340projectone.databinding.FragmentAddAssignmentBinding;
import com.example.a2340projectone.ui.home.CourseList;
import com.example.a2340projectone.ui.todolist.Task;
import com.example.a2340projectone.ui.todolist.TaskList;

import java.util.ArrayList;

public class add_assignment extends Fragment {
    private FragmentAddAssignmentBinding binding;
    public add_assignment() {
        // Required empty public constructor
    }
    public static add_assignment newInstance() {
        add_assignment fragment = new add_assignment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddAssignmentBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, CourseList.coursesAvailable);
        Spinner spinner = binding.assignmentCourseFill;
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        binding.addAssignmentButton.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View view) {
                String taskTitle = "";
                String courseTitle ="";
                String date = "";


                taskTitle = String.valueOf(binding.assignmentNameFill.getText());
                Assignment tobeAdded = new Assignment(taskTitle, courseTitle);
                if (tobeAdded.checkDate(String.valueOf(binding.assignmentDuedateFill.getText()))) {
                    date = String.valueOf(binding.assignmentDuedateFill.getText());
                    binding.assignmentNameFill.setText("");
                    binding.assignmentDuedateFill.setText("");
                    tobeAdded.setCourse((String) spinner.getSelectedItem());
                    tobeAdded.setDate(date);
                    AssignmentList.assignments.add(tobeAdded);
                    NavHostFragment.findNavController(add_assignment.this).navigate(R.id.action_add_assignment_to_navigation_dashboard);
                } else {
                    Toast myToast = Toast.makeText(getActivity(), "Invalid Date Entered! Date should be entered MM-DD-YYYY", Toast.LENGTH_SHORT);
                    myToast.show();
                }


        }
    });
    }
}