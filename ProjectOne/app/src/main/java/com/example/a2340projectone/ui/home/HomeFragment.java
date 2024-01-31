package com.example.a2340projectone.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.a2340projectone.R;
import com.example.a2340projectone.databinding.FragmentHomeBinding;
import com.example.a2340projectone.ui.dashboard.DashboardFragment;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.classBtn.setOnClickListener(view2 -> {
            NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_navigation_home_to_list);
        });
//        binding.classBtn.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//                Fragment fragment = new DashboardFragment();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.nav_host_fragment_activity_main,fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//                }
//        });
    }

    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}