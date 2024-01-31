package com.example.a2340projectone.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2340projectone.R;
import com.example.a2340projectone.databinding.FragmentDashboardBinding;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    final ArrayList<Task> data = new ArrayList<>();
    List<Task> items = new ArrayList<>();
    int counter = 0;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
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
                binding.addButton.setOnClickListener(view2 -> {
                        NavHostFragment.findNavController(DashboardFragment.this).navigate(R.id.action_navigation_dashboard_to_add_task);
                            });

                    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}