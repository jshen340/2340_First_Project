package com.example.a2340projectone.ui.exams;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2340projectone.R;
import com.example.a2340projectone.databinding.FragmentNotificationsBinding;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    ArrayList<Exam> items = new ArrayList<>();
    ExamAdapter adapter;
    RecyclerView recyclerView;
    int counter = 0;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        RecyclerView recycler = root.findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        adapter = new ExamAdapter(items, requireContext());
        recycler.setAdapter(adapter);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new ExamAdapter(items, requireContext());
        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(requireContext());
                dialog.setContentView(R.layout.fragment_add_exam);

                EditText editName = dialog.findViewById(R.id.examTitle);
                EditText editCourse = dialog.findViewById(R.id.courseTitle);
                EditText editTime = dialog.findViewById(R.id.timeTitle);
                EditText editLocation = dialog.findViewById(R.id.locationTitle);
                EditText editDate = dialog.findViewById(R.id.dateTitle);
                Button inputBtn = dialog.findViewById(R.id.confirm);
                inputBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = "", date = "", course = "", time = "", location = "";
                        if (!editName.getText().toString().equals("")) {
                            name = editName.getText().toString();
                        }
                        if (!editCourse.getText().toString().equals("")) {
                            course = editCourse.getText().toString();
                        }
                        if (!editTime.getText().toString().equals("")) {
                            time = editTime.getText().toString();
                        }
                        if (!editLocation.getText().toString().equals("")) {
                            location = editLocation.getText().toString();
                        }
                        if (!editDate.getText().toString().equals("")) {
                            date = editDate.getText().toString();
                        }

                        items.add(new Exam(name, date, course, time, location));
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}