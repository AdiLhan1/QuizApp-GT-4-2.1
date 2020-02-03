package com.geektech.quizapp_gt_4_2.quiz;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.model.Question;

import java.util.List;

public class QuizViewModel extends ViewModel {
    private IQuizApiClient quizApiClient = App.quizApiClient;

    public void init(int amount, @Nullable Integer category, String difficulty) {

    }

    public MutableLiveData<List<Question>> question = new MutableLiveData<>();
    public MutableLiveData<Integer> currentPosition = new MutableLiveData<>();

    public void getQuestions(int amount, Integer category, String difficulty) {
        App.quizApiClient.getQuestions(new IQuizApiClient.QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> result) {
                question.postValue(result);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
