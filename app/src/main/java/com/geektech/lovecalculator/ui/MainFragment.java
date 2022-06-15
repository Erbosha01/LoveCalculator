package com.geektech.lovecalculator.ui;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geektech.lovecalculator.R;
import com.geektech.lovecalculator.databinding.FragmentMainBinding;
import com.geektech.lovecalculator.viewmodel.MainViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainFragment extends Fragment {
    private FragmentMainBinding binding;
    private MainViewModel mainViewModel;
    @Inject
    SharedPreferences preferences;

    public final static String BUNDLE_KEY_ONE = "fname";
    public final static String BUNDLE_KEY_TWO = "sname";
    public final static String BUNDLE_KEY_THREE = "percentage";
    public final static String BUNDLE_KEY_FOUR = "result";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding = FragmentMainBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        preferences.getBoolean("isShown", false);
        boolean prefs = preferences.getBoolean("isShown", false);
        if (!prefs) {
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
            navController.navigate(R.id.boardFragment);
        }

        initClickListener();
    }

    private void initClickListener() {
        binding.btnTry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataFromLoveApi();
            }
        });
    }

    private void getDataFromLoveApi() {
        String firstName = binding.etNameOne.getText().toString();
        String secondName = binding.etNameTwo.getText().toString();
        mainViewModel.getLoveModelLiveData(firstName, secondName).observe(this, LoveModel -> {
            Log.e("TAG", "getDataFromLoveApi: " + LoveModel.getPercentage());
            if (LoveModel != null) {
                Bundle bundle = new Bundle();
                String oneName = LoveModel.getFirstName();
                String twodName = LoveModel.getSecondName();
                String percentage = LoveModel.getPercentage();
                String result = LoveModel.getResult();
                bundle.putString(BUNDLE_KEY_ONE, oneName);
                bundle.putString(BUNDLE_KEY_TWO, twodName);
                bundle.putString(BUNDLE_KEY_THREE, percentage);
                bundle.putString(BUNDLE_KEY_FOUR, result);
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.action_mainFragment_to_resultFragment, bundle);
            }
        });
    }
}