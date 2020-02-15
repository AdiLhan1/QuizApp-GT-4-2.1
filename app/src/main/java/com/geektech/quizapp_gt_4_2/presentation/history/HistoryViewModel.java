package com.geektech.quizapp_gt_4_2.presentation.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.model.History;

import java.util.List;

public class HistoryViewModel extends ViewModel {

    private List<History> mHistory;
    public LiveData<List<History>> history = App.historyStorage.getAllHistory();

}
