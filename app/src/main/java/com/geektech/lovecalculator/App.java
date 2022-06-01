package com.geektech.lovecalculator;

import android.app.Application;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.geektech.lovecalculator.network.LoveApi;
import com.geektech.lovecalculator.network.RetrofitService;

public class App extends Application {
    public static LoveApi api;

    @Override
    public void onCreate() {
        RetrofitService retrofitService = new RetrofitService();
        api = retrofitService.getLoveApi();
        super.onCreate();
    }
}
