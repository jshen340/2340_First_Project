package com.example.a2340projectone.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2340projectone.R;
import com.example.a2340projectone.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

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

        RecyclerView recycler = view.findViewById(R.id.recyclerView_assignment);
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        AssignmentAdapter adapter = new AssignmentAdapter(AssignmentList.assignments);
        recycler.setAdapter(adapter);
        adapter.notifyItemInserted(AssignmentList.assignments.size()-1);
        AssignmentAdapter adapter1 = new AssignmentAdapter(AssignmentList.completedAssignments);
        binding.sortCourseButton.setOnClickListener(view1 -> {
            AssignmentList.courseSorted();
            adapter.notifyDataSetChanged();
        });

        binding.dateSortedButton.setOnClickListener(view1 -> {
            AssignmentList.dateSorted();
            adapter.notifyDataSetChanged();
        });
        binding.assignmentCompletedButton.setOnClickListener(view1 -> {
            if (AssignmentList.completeListOn) {
                recycler.setAdapter(adapter1);
                AssignmentList.completeListOn = !AssignmentList.completeListOn;
                adapter1.notifyItemInserted(AssignmentList.completedAssignments.size()-1);
            } else {
                recycler.setAdapter(adapter);
                AssignmentList.completeListOn = !AssignmentList.completeListOn;
                adapter.notifyItemInserted(AssignmentList.assignments.size()-1);
            }

        });
        binding.addAssignmentInitial.setOnClickListener(view2 -> {
            NavHostFragment.findNavController(DashboardFragment.this).navigate(R.id.action_navigation_dashboard_to_add_task);
        });
                    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}