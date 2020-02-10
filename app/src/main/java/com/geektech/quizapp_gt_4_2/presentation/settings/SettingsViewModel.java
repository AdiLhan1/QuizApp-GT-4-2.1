package com.geektech.quizapp_gt_4_2.presentation.settings;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class SettingsViewModel extends ViewModel {
    public MutableLiveData<String> message = new MutableLiveData<>();

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
