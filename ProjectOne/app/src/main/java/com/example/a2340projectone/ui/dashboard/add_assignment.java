//package com.example.a2340projectone.ui.dashboard;
//
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.navigation.fragment.NavHostFragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.a2340projectone.R;
//import com.example.a2340projectone.databinding.FragmentAddAssignmentBinding;
//import com.example.a2340projectone.ui.todolist.Task;
//import com.example.a2340projectone.ui.todolist.TaskList;
//
//public class add_assignment extends Fragment {
//    private FragmentAddAssignmentBinding binding;
//
//    public add_assignment() {
//        // Required empty public constructor
//    }
//
//    public static add_assignment newInstance() {
//        add_assignment fragment = new add_assignment();
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        binding = FragmentAddAssignmentBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//        return root;
//    }
//
//    @Override
//    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        binding.addAssignmentButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String taskTitle = "";
//                String courseTitle = "";
//                String date = "";
//
//
//                try {
//                    taskTitle = String.valueOf(binding.assignmentNameFill.getText());
//                    courseTitle = String.valueOf(binding.assignmentCourseFill.getText());
//                    date = String.valueOf(binding.assignmentDuedateFill.getText());
//                } catch (Exception e) {
//
//                }
//                binding.assignmentNameFill.setText("");
//                binding.assignmentCourseFill.setText("");
//                binding.assignmentDuedateFill.setText("");
//                AssignmentList.assignments.add(new Assignment(taskTitle, date, courseTitle));
//                TaskList.tasks.add(new Assignment(taskTitle + " (Assignment)", date, courseTitle));
//                NavHostFragment.findNavController(add_assignment.this).navigate(R.id.action_add_assignment_to_navigation_dashboard);
//
//
//            }
//        });
//    }
//}