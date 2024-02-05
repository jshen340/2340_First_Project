package com.example.a2340projectone.ui.exams;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a2340projectone.R;
import com.example.a2340projectone.databinding.FragmentAddExamBinding;
import com.example.a2340projectone.ui.dashboard.AssignmentList;
import com.example.a2340projectone.ui.dashboard.add_assignment;
import com.example.a2340projectone.ui.todolist.Task;
import com.example.a2340projectone.ui.todolist.TaskList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link add_exam#newInstance} factory method to
 * create an instance of this fragment.
 */
public class add_exam extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentAddExamBinding binding;

    public add_exam() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment add_exam.
     */
    // TODO: Rename and change types and number of parameters
    public static add_exam newInstance() {
        add_exam fragment = new add_exam();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddExamBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String examTitle = "";
                String courseTitle = "";
                String date = "";
                String location = "";
                String time = "";

                examTitle = String.valueOf(binding.examTitle.getText());
                courseTitle = String.valueOf(binding.courseTitle.getText());
                date = String.valueOf(binding.dateTitle.getText());
                location = String.valueOf(binding.locationTitle.getText());
                time = String.valueOf(binding.timeTitle.getText());

                Exam tobeAdded = new Exam(examTitle, courseTitle, location);

                if (tobeAdded.checkDate(String.valueOf(binding.dateTitle.getText()))) {
                    date = String.valueOf(binding.dateTitle.getText());
                    tobeAdded.setDate(date);
                } else {
                    Toast myToast = Toast.makeText(getActivity(), "Invalid Date Entered! Date should be entered MM-DD-YYYY", Toast.LENGTH_SHORT);
                    myToast.show();
                }

                if (tobeAdded.checkTime(String.valueOf(binding.timeTitle.getText()))) {
                    time = String.valueOf(binding.timeTitle.getText());
                    tobeAdded.setTime(time);

                } else {
                    Toast myToast = Toast.makeText(getActivity(), "Invalid Time Entered! Time should be entered HH:MM", Toast.LENGTH_SHORT);
                    myToast.show();
                }
                if (tobeAdded.checkDate(String.valueOf(binding.dateTitle.getText())) && tobeAdded.checkTime(String.valueOf(binding.timeTitle.getText()))) {
                    binding.examTitle.setText("");
                    binding.courseTitle.setText("");
                    binding.dateTitle.setText("");
                    binding.locationTitle.setText("");
                    binding.timeTitle.setText("");
                    ExamList.exams.add(tobeAdded);
                    NavHostFragment.findNavController(add_exam.this).navigate(R.id.action_add_exam_to_navigation_notifications);
                }
            }
        });
    }
}