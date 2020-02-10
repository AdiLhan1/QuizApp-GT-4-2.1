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
    private String mCategory = "Mixed";
    private String mDifficulty = "All";
    private int correctAnswerAmounts = 0;
    private List<Question> mQuestions;
    private int id = 0;
    private Integer count;

    MutableLiveData<List<Question>> question = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionsPosition = new MutableLiveData<>();
    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    SingleLiveEvent<Integer> openResultEvent = new SingleLiveEvent<>();
    SingleLiveEvent<Void> finishEvent = new SingleLiveEvent<>();

    public QuizViewModel() {
        currentQuestionsPosition.setValue(0);
        count = 0;
    }


    public void getQuestions(int amount, Integer category, String difficulty) {
        isLoading.setValue(true);

        quizApiClient.getQuestions(amount, category, difficulty, new IQuizApiClient.QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> result) {
                isLoading.setValue(false);
                mQuestions = result;
                question.postValue(mQuestions);
                ++id;
                if (mQuestions.get(0).getCategory().equals(mQuestions.get(1).getCategory())) {
                    mCategory = mQuestions.get(0).getCategory();
                }
                if (mQuestions.get(0).getDifficulty().equals(mQuestions.get(1).getDifficulty())) {
                    mDifficulty = mQuestions.get(0).getDifficulty().toString();
                }
            }

            @Override
            public void onFailure(Exception e) {
                isLoading.setValue(false);
                Log.e("TAG", "onFailure: " + e.getLocalizedMessage());
            }
        });

        finishEvent.call();
    }

    private int getCorrectAnswersAmount() {
        for (int i = 0; i < mQuestions.size() - 1; i++) {
            String correctAnswer = mQuestions.get(i).getCorrectAnswers();
            String selectedAnswer = mQuestions.get(i).getAnswers()
                    .get(mQuestions.get(i).getSelectedAnswerPosition());
            if (selectedAnswer.equals(correctAnswer)) {
                ++correctAnswerAmounts;
            }
        }
        return correctAnswerAmounts;
    }

    void finishQuiz() {
        QuizResult result = new QuizResult(
                id,
                mCategory,
                mDifficulty,
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
