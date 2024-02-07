package com.example.a2340projectone.ui.todolist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.a2340projectone.MainActivity;
import com.example.a2340projectone.R;
import com.example.a2340projectone.databinding.FragmentFinalAddTaskBinding;
import com.example.a2340projectone.ui.dashboard.AssignmentList;
import com.example.a2340projectone.ui.exams.ExamList;
import com.example.a2340projectone.ui.home.HomeFragment;

public class finalAddTask extends Fragment {

    private FragmentFinalAddTaskBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFinalAddTaskBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.completedSort.setText("INCOMPLETE");
        RecyclerView recycler = view.findViewById(R.id.recyclerView_todo);
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        TaskAdapter adapter = new TaskAdapter(TaskList.tasks);
        TaskAdapter adapter1 = new TaskAdapter(TaskList.completedTasks);
        recycler.setAdapter(adapter);
        adapter.notifyItemInserted(TaskList.tasks.size()-1);
        binding.sortDateButton.setOnClickListener( view2 -> {
            TaskList.dateSorted();
            adapter.notifyDataSetChanged();
        });
        binding.categorySort.setOnClickListener( view2 -> {
            TaskList.categorySorted();
            adapter.notifyDataSetChanged();;
        });
        binding.completedSort.setOnClickListener( view2 -> {
            if (TaskList.completedListOn) {
                recycler.setAdapter(adapter1);
                TaskList.completedListOn = !TaskList.completedListOn;
                binding.completedSort.setText("COMPLETE");
                adapter1.notifyItemInserted(AssignmentList.completedAssignments.size()-1);
            } else {
                recycler.setAdapter(adapter);
                TaskList.completedListOn = !TaskList.completedListOn;
                binding.completedSort.setText("INCOMPLETE");
                adapter.notifyItemInserted(AssignmentList.assignments.size()-1);
            };
        });

        binding.addButtonFinal.setOnClickListener(view2 -> {
            NavHostFragment.findNavController(finalAddTask.this).navigate(R.id.fillInformationScreenTask);
        });

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}