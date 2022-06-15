package com.geektech.lovecalculator.repository;


import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.geektech.lovecalculator.hilt.App;
import com.geektech.lovecalculator.network.LoveApi;
import com.geektech.lovecalculator.network.LoveModel;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    LoveApi loveApi;

    @Inject
    public Repository(LoveApi loveApi) {
        this.loveApi = loveApi;
    }

    private final String HOST = "love-calculator.p.rapidapi.com";
    private final String KEY = "bd3d5729b1mshb569793d5354166p175908jsn502d245afefd";

    public MutableLiveData<LoveModel> getData(String firstName, String secondName) {

        MutableLiveData<LoveModel> loveModelMutableLiveData = new MutableLiveData<>();
        loveApi.loveCalculate(firstName, secondName, HOST, KEY).enqueue(new Callback<LoveModel>() {
            @Override
            public void onResponse(Call<LoveModel> call, Response<LoveModel> response) {
                if (response.isSuccessful()) {
                    loveModelMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<LoveModel> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getMessage() );
            }
        });
        return loveModelMutableLiveData;

    }
}
