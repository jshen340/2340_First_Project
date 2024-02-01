package com.example.a2340projectone.ui.todolist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a2340projectone.R;
import com.example.a2340projectone.databinding.FragmentFillInformationScreenTaskBinding;

public class fillInformationScreenTask extends Fragment {

    private FragmentFillInformationScreenTaskBinding binding;

    public fillInformationScreenTask() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFillInformationScreenTaskBinding.inflate(inflater, container, false);
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
                    courseTitle = String.valueOf(binding.categoryFill.getText());
                    date = String.valueOf(binding.duedateFill.getText());
                } catch (Exception e) {

                }

                binding.duedateFill.setText("");
                binding.categoryFill.setText("");
                binding.taskFill.setText("");
                TaskList.tasks.add(new Task(taskTitle, courseTitle, date));
                NavHostFragment.findNavController(fillInformationScreenTask.this).navigate(R.id.action_fillInformationScreenTask_to_finalAddTask);


            }
        });
    }
}