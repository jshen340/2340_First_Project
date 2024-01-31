package com.example.a2340projectone.ui.dashboard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a2340projectone.R;
import com.example.a2340projectone.databinding.FragmentAddTaskBinding;

public class add_task extends Fragment {
    private FragmentAddTaskBinding binding;
    public add_task() {
        // Required empty public constructor
    }
    public static add_task newInstance() {
        add_task fragment = new add_task();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.confirm.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View view) {
                String taskTitle = "";
                String courseTitle ="";
                String date = "";


                try {
                    taskTitle = String.valueOf(binding.taskFill.getText());
                    courseTitle = String.valueOf(binding.classFill.getText());
                    date = String.valueOf(binding.duedateFill.getText());
                } catch (Exception e) {

                }
            add_taskDirections.ActionAddTaskToNavigationDashboard action = add_taskDirections.actionAddTaskToNavigationDashboard(75);
            try {
                NavHostFragment.findNavController(add_task.this).navigate(action);
            } catch (Exception e) {

            }

            binding.duedateFill.setText("");
            binding.classFill.setText("");
            binding.taskFill.setText("");
            TaskList.tasks.add(new Task(taskTitle, courseTitle, date));
            NavHostFragment.findNavController(add_task.this).navigate(R.id.action_add_task_to_navigation_dashboard);


        }
    });
    }
}