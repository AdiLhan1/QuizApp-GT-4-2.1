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

    void onAnswerClick(int position, int selectedAnswerPosition) {
        // 20, 19
        // 20, 20
        // 20, 21
        // 20, -1

        if (mQuestions.size() > position && position >= 0) {
            mQuestions.get(position)
                    .setSelectedAnswerPosition(selectedAnswerPosition);

            question.setValue(mQuestions);

            // 20, 17 -> 18
            // 20, 18 -> 19
            // 20, 19 -> 20
            // 20, 20

            if (position + 1 == mQuestions.size()) {
                //TODO: Finish quiz
            } else {
                currentPosition.setValue(position + 1);
            }
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public void onSkipClick() {
        currentPosition.setValue(++count);
    }

    public void onBackPressed() {
        currentPosition.setValue(--count);
    }
}
