package com.geektech.lovecalculator.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geektech.lovecalculator.R;
import com.geektech.lovecalculator.databinding.FragmentResultBinding;

public class ResultFragment extends Fragment {
    private FragmentResultBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentResultBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getBundle();
        initClickListener();
    }

    private void initClickListener() {
        binding.btnTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment);
                navController.navigate(R.id.action_resultFragment_to_mainFragment);
            }
        });
    }

    private void getBundle() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            String firstName = bundle.getString(MainFragment.BUNDLE_KEY_ONE);
            String secondName = bundle.getString(MainFragment.BUNDLE_KEY_TWO);
            String percentage = bundle.getString(MainFragment.BUNDLE_KEY_THREE);
            String result = bundle.getString(MainFragment.BUNDLE_KEY_FOUR);
            binding.tvMe.setText(firstName);
            binding.tvYou.setText(secondName);
            binding.tvPercent.setText(percentage);
            binding.tvResult.setText(result);
        }
    }
}