package com.geektech.quizapp_gt_4_2.quiz;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.model.Question;

import java.util.List;

public class QuizViewModel extends ViewModel {
    public MutableLiveData<List<Question>> question = new MutableLiveData<>();
    public MutableLiveData<Integer> currentPosition = new MutableLiveData<>();
    private Integer count;

    public QuizViewModel() {
        currentPosition.setValue(1);
        count = 1;
    }

    public void getQuestions(int amount, Integer category, String difficulty) {
        App.quizApiClient.getQuestions(amount, category, difficulty, new IQuizApiClient.QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> result) {
                question.postValue(result);
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("TAG", "onFailure: " + e);
            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public void nextPage() {
        currentPosition.setValue(++count);
    }

    public void prevPage() {
        currentPosition.setValue(--count);
    }
}
