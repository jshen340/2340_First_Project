package com.example.a2340projectone.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a2340projectone.databinding.FragmentNotificationsBinding;

import java.util.LinkedList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    Exam[] data = {new Exam("Hi", "Whats up", "Hello", "I am", "LOL")};
    int counter = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textNotifications;
//        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        List<Exam> items = new LinkedList<>();
        items.add(data[0]);

        RecyclerView recycler = binding.recyclerView;
        recycler.setLayoutManager(new LinearLayoutManager(this.getContext()));
        ExamAdapter adapter = new ExamAdapter(items);
        recycler.setAdapter(adapter);

        binding.addButton.setOnClickListener(view -> {
            //NavHostFragment.findNavController(NotificationsFragment.this).navigate(R.id.action_navigation_notifications_to_mainActivity);
            items.add(data[0]);
            counter++;
            adapter.notifyItemInserted(items.size()-1);
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}