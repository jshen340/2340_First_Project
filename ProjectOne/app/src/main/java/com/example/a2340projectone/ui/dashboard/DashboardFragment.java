package com.example.a2340projectone.ui.dashboard;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2340projectone.MainActivity;
import com.example.a2340projectone.R;
import com.example.a2340projectone.databinding.FragmentDashboardBinding;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    ArrayList<Assignment> items = new ArrayList<>();
    AssignmentAdapter adapter;
    RecyclerView recyclerView;
    int counter = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        RecyclerView recycler = root.findViewById(R.id.recyclerView_assignment);

//        items.add(new Assignment("name", "course", "date"));

        recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new AssignmentAdapter(requireContext(), items);
        recycler.setAdapter(adapter);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView_assignment);
        adapter = new AssignmentAdapter(requireContext(), items);
        binding.addAssignmentInitial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(requireContext());
                dialog.setContentView(R.layout.fragment_add_assignment);

                EditText editText = dialog.findViewById(R.id.assignment_name_fill);
                EditText editCourse = dialog.findViewById(R.id.assignment_course_fill);
                EditText editDate = dialog.findViewById(R.id.assignment_duedate_fill);
                Button inputBtn = dialog.findViewById(R.id.add_assignment_button);
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
                        items.add(new Assignment(name, course, date));
                        adapter.notifyItemInserted(items.size() - 1);
                        recyclerView.scrollToPosition(items.size() - 1);

                        dialog.dismiss();
                    }
                });
                dialog.show();
                dialog.getWindow().setLayout(1000, 1500);
            }
        });
    }
}