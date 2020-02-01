package com.geektech.quizapp_gt_4_2.quiz;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.model.Question;

public class QuizViewModel extends ViewModel {
    private IQuizApiClient quizApiClient = App.quizApiClient;

    public void init(int amount, @Nullable Integer category, String difficulty) {

    }

    public MutableLiveData<Question> question = new MutableLiveData<>();

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
