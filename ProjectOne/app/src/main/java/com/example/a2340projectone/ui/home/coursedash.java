package com.example.a2340projectone.ui.home;

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

import com.example.a2340projectone.R;
import com.example.a2340projectone.databinding.FragmentCoursedashBinding;
import com.example.a2340projectone.ui.exams.ExamList;

import java.util.ArrayList;
import java.util.List;

public class coursedash extends Fragment {

    private FragmentCoursedashBinding binding;
    private final ArrayList<Courses> data = new ArrayList<>();
    private List<Courses> items = new ArrayList<>();

    public coursedash() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coursedash, container, false);

        Button addcbtn = view.findViewById(R.id.addcbtn);

        addcbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Use NavHostFragment.findNavController() to navigate to the addCourse fragment
                NavHostFragment.findNavController(coursedash.this)
                        .navigate(R.id.action_coursedash_to_addCourse);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recycler = view.findViewById(R.id.courselist_recycle);
        recycler.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Initialize the items list and pass it to the adapter
        CourseAdapter adapter = new CourseAdapter(CourseList.courses);
        recycler.setAdapter(adapter);
        adapter.notifyItemInserted(CourseList.courses.size()-1);
    }
}