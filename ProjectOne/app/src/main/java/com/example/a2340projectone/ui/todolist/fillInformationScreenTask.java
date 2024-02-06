package com.example.a2340projectone.ui.todolist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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

                taskTitle = String.valueOf(binding.taskFill.getText());
                courseTitle = String.valueOf(binding.categoryFill.getText());
                date = String.valueOf(binding.duedateFill.getText());

                Task toBeAdded = new Task(taskTitle, courseTitle);
                if (toBeAdded.checkDate(String.valueOf(binding.duedateFill.getText()))) {
                    toBeAdded.setDate(binding.duedateFill.getText().toString());
                    TaskList.tasks.add(toBeAdded);
                    binding.duedateFill.setText("");
                    binding.categoryFill.setText("");
                    binding.taskFill.setText("");
                    NavHostFragment.findNavController(fillInformationScreenTask.this).navigate(R.id.action_fillInformationScreenTask_to_finalAddTask);
                } else {
                    Toast myToast = Toast.makeText(getActivity(), "Invalid Date Entered! Date should be entered MM-DD-YYYY", Toast.LENGTH_SHORT);
                    myToast.show();
                }






            }
        });
    }
}