package com.geektech.lovecalculator.ui;

import android.app.Activity;
import android.content.SharedPreferences;
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
import com.geektech.lovecalculator.databinding.FragmentBoardBinding;
import com.geektech.lovecalculator.ui.adaptor.BoardAdaptor;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BoardFragment extends Fragment {
    private FragmentBoardBinding binding;

    @Inject
    BoardAdaptor adaptor;

    @Inject
    SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoardBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.BoardViewPager.setAdapter(adaptor);
        clickListener();
    }

    private void clickListener() {
        binding.btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences.edit().putBoolean("isShown", true).apply();

                NavController navController = Navigation.findNavController((Activity) requireActivity(), R.id.nav_host_fragment);
                navController.navigateUp();
            }
        });
    }
}