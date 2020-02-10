package com.geektech.quizapp_gt_4_2.presentation.quiz;

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
    public MutableLiveData<Integer> currentQuestionsPosition = new MutableLiveData<>();
    private List<Question> mQuestions;

    private Integer count;

    public QuizViewModel() {
        currentQuestionsPosition.setValue(0);
        count = 0;
    }

    SingleLiveEvent<Integer> openResultEvent = new SingleLiveEvent<>();
    SingleLiveEvent<Void> finishEvent = new SingleLiveEvent<>();

    public void getQuestions(int amount, Integer category, String difficulty) {
        quizApiClient.getQuestions(amount, category, difficulty, new IQuizApiClient.QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> result) {
                mQuestions = result;
                question.postValue(mQuestions);
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

    void onAnswerClick(int position, int selectedAnswerPosition) {
        if (mQuestions.size() > position && position >= 0) {
            mQuestions.get(position)
                    .setSelectedAnswerPosition(selectedAnswerPosition);
            Log.e("TAG", "onAnswerClick setAnswer: " + position + selectedAnswerPosition);

            question.setValue(mQuestions);

            if (position + 1 == mQuestions.size()) {
                finishQuiz();
            } else {
                currentQuestionsPosition.setValue(++count);
            }
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public void onSkipClick() {
        currentQuestionsPosition.setValue(++count);
    }

    public void onBackPressed() {
        currentQuestionsPosition.setValue(--count);
    }
}
