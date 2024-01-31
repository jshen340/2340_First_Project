package com.example.a2340projectone.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2340projectone.R;
import com.example.a2340projectone.databinding.FragmentNotificationsBinding;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    final ArrayList<Exam> data = new ArrayList<>();
    List<Exam> items = new ArrayList<>();
    int counter = 0;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);


        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


//        final TextView textView = binding.textNotifications;
//        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        //items.add(new Exam("FILLERORG", "Whats up", "Hello", "I am", "LOL"));
        RecyclerView recycler = binding.recyclerView;
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        ExamAdapter adapter = new ExamAdapter(ExamList.exams);
        recycler.setAdapter(adapter);
        adapter.notifyItemInserted(ExamList.exams.size()-1);
        binding.addButton.setOnClickListener(view2 -> {
            NavHostFragment.findNavController(NotificationsFragment.this).navigate(R.id.action_navigation_notifications_to_add_exam);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}