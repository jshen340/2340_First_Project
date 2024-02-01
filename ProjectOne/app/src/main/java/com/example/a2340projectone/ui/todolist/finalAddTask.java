package com.example.a2340projectone.ui.todolist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a2340projectone.R;
import com.example.a2340projectone.databinding.FragmentFinalAddTaskBinding;

public class finalAddTask extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private FragmentFinalAddTaskBinding binding;

    public finalAddTask() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

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
        RecyclerView recycler = view.findViewById(R.id.recyclerView_todo);
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        TaskAdapter adapter = new TaskAdapter(TaskList.tasks);
        recycler.setAdapter(adapter);
        adapter.notifyItemInserted(TaskList.tasks.size()-1);
        binding.addButtonFinal.setOnClickListener(view2 -> {
            NavHostFragment.findNavController(finalAddTask.this).navigate(R.id.action_finalAddTask_to_fillInformationScreenTask);
        });

    }
}