package com.geektech.quizapp_gt_4_2.presentation.settings;

import androidx.lifecycle.ViewModel;

import com.geektech.quizapp_gt_4_2.App;


public class SettingsViewModel extends ViewModel {

    public void clearAllHistory() {
        App.historyStorage.deleteAll();
    }
}
