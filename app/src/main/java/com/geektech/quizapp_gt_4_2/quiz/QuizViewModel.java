package com.geektech.quizapp_gt_4_2.quiz;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.core.SingleLiveEvent;
import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.model.Question;
import com.geektech.quizapp_gt_4_2.model.QuizResult;

import java.util.Date;
import java.util.List;

public class QuizViewModel extends ViewModel {
    private IQuizApiClient quizApiClient = App.quizApiClient;

    public MutableLiveData<List<Question>> question = new MutableLiveData<>();
    public MutableLiveData<Integer> currentPosition = new MutableLiveData<>();
    private List<Question> mQuestions;

    private Integer count;

    public QuizViewModel() {
        currentPosition.setValue(1);
        count = 1;
    }

    SingleLiveEvent<Integer> openResultEvent = new SingleLiveEvent<>();
    SingleLiveEvent<Void> finishEvent = new SingleLiveEvent<>();

    public void getQuestions(int amount, Integer category, String difficulty) {
        App.quizApiClient.getQuestions(amount, category, difficulty, new IQuizApiClient.QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> result) {
                question.postValue(result);
                mQuestions = result;
                question.setValue(mQuestions);
                Log.e("TAG", "onSuccess: " + result.get(0).getAnswers());
                Log.e("==========", result.get(0).getAnswers() + "");
            }

            @Override
            public void onFailure(Exception e) {

            }
        });

        finishEvent.call();
    }

    private int getCorrectAnswersAmount() {
        //TODO:
        return 0;
    }

    void finishQuiz() {
        QuizResult result = new QuizResult(
                0,
                "",
                "",
                mQuestions,
                getCorrectAnswersAmount(),
                new Date()
        );

        int resultId = App.historyStorage.saveQuizResult(result);

        //TODO: Start Result activity
        finishEvent.call();
        openResultEvent.setValue(resultId);
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
