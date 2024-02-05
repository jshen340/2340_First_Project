package com.example.a2340projectone.ui.todolist;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.a2340projectone.R;
import com.example.a2340projectone.databinding.FragmentFinalAddTaskBinding;

import java.util.ArrayList;

public class finalAddTask extends Fragment {
        private FragmentFinalAddTaskBinding binding;
        ArrayList<Task> items = new ArrayList<>();
        TaskAdapter adapter;
        RecyclerView recyclerView;
    public finalAddTask() {
        // Required empty public constructor
    }


//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFinalAddTaskBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        RecyclerView recycler = root.findViewById(R.id.recyclerView_todo);

        recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new TaskAdapter(items, requireContext());
        recycler.setAdapter(adapter);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView_todo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new TaskAdapter(items, requireContext());
        binding.addButtonFinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(requireContext());
                dialog.setContentView(R.layout.fragment_fill_information_screen_task);

                EditText editCourse = dialog.findViewById(R.id.category_fill);
                EditText editDate = dialog.findViewById(R.id.duedate_fill);
                EditText editText = dialog.findViewById(R.id.task_fill);
                Button inputBtn = dialog.findViewById(R.id.confirm_task);

                inputBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = "", course = "", date = "";
                        if (!editText.getText().toString().equals("")) {
                            name = editText.getText().toString();
                        }
                        if (!editCourse.getText().toString().equals("")) {
                            course = editCourse.getText().toString();
                        }
                        if (!editDate.getText().toString().equals("")) {
                            date = editDate.getText().toString();
                        }
                        items.add(new Task(name, course, date));
                        adapter.notifyItemInserted(items.size() - 1);
                        recyclerView.scrollToPosition(items.size() - 1);

                        dialog.dismiss();
                    }
                });
                dialog.show();
                dialog.getWindow().setLayout(1000, 1500);
            }
        });

        binding.sortDateButton.setOnClickListener( view2 -> {
            TaskList.dateSorted();
            TaskAdapter adapter2 = new TaskAdapter(items, requireContext());
            recyclerView.setAdapter(adapter2);
            adapter2.notifyItemInserted(TaskList.tasks.size()-1);
        });
        binding.categorySort.setOnClickListener( view2 -> {
            TaskList.categorySorted();
            TaskAdapter adapter2 = new TaskAdapter(items, requireContext());
            recyclerView.setAdapter(adapter2);
            adapter2.notifyItemInserted(TaskList.tasks.size()-1);
        });
        binding.completedSort.setOnClickListener( view2 -> {
            TaskList.completeSort();
            TaskAdapter adapter2 = new TaskAdapter(items, requireContext());
            recyclerView.setAdapter(adapter2);
            adapter2.notifyItemInserted(TaskList.tasks.size()-1);
        });
    }
}