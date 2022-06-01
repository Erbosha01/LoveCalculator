package com.geektech.lovecalculator.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.lovecalculator.network.LoveModel;
import com.geektech.lovecalculator.repository.Repository;

public class MainViewModel extends ViewModel {

    Repository repository = new Repository();
    public LiveData<LoveModel> getLoveModelLiveData(String firstName, String secondName) {
        return repository.getData(firstName, secondName);
    }
}
