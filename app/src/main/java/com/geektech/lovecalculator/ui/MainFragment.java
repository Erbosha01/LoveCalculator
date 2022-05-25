package com.geektech.lovecalculator.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.geektech.lovecalculator.App;
import com.geektech.lovecalculator.R;
import com.geektech.lovecalculator.databinding.FragmentMainBinding;
import com.geektech.lovecalculator.network.LoveModel;

import javax.security.auth.login.LoginException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {
    private FragmentMainBinding binding;
    private final String HOST = "love-calculator.p.rapidapi.com";
    private final String KEY = "bd3d5729b1mshb569793d5354166p175908jsn502d245afefd";
    public final static String BUNDLE_KEY_ONE = "fname";
    public final static String BUNDLE_KEY_TWO = "sname";
    public final static String BUNDLE_KEY_THREE = "percentage";
    public final static String BUNDLE_KEY_FOUR = "result";

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

        App.api.loveCalculate(firstName, secondName, HOST, KEY).enqueue(new Callback<LoveModel>() {
            @Override
            public void onResponse(Call<LoveModel> call, Response<LoveModel> response) {
                if (response.isSuccessful()) {
                    Log.e("TAG", "onResponse: " + response.body());
                    Bundle bundle = new Bundle();
                    String firsName = response.body().getFirstName();
                    String secondName = response.body().getSecondName();
                    String percentage = response.body().getPercentage();
                    String result = response.body().getResult();
                    bundle.putString(BUNDLE_KEY_ONE, firsName);
                    bundle.putString(BUNDLE_KEY_TWO, secondName);
                    bundle.putString(BUNDLE_KEY_THREE, percentage);
                    bundle.putString(BUNDLE_KEY_FOUR, result);
                    NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
                    navController.navigate(R.id.action_mainFragment_to_resultFragment, bundle);
                }
            }

            @Override
            public void onFailure(Call<LoveModel> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getLocalizedMessage());
            }
        });
    }
}