package com.geektech.quizapp_gt_4_2.presentation.quiz;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.core.SingleLiveEvent;
import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.model.Question;
import com.geektech.quizapp_gt_4_2.model.QuizResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class QuizViewModel extends ViewModel {
    private IQuizApiClient quizApiClient = App.quizApiClient;
    private String mCategoryString;
    private String mDifficultyString;
    private List<Question> mQuestions;
    private int id;
    private long i = 30000;
    private long j;
    private Integer count;
    private CountDownTimer countDownTimer;

    MutableLiveData<List<Question>> question = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionsPosition = new MutableLiveData<>();
    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    MutableLiveData<Long> timeDown = new MutableLiveData<>();

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
                try {
                    if (mQuestions.get(1).getCategory().equals(mQuestions.get(2).getCategory())) {
                        mCategoryString = mQuestions.get(1).getCategory();
                    } else {
                        mCategoryString = "Mixed";
                    }
                    if (difficulty != null) {
                        mDifficultyString = mQuestions.get(0).getDifficulty().toString();
                    } else {
                        mDifficultyString = "All";
                    }
                } catch (IndexOutOfBoundsException e) {
                    isLoading.setValue(false);
                    finishEvent.call();
                }
            }

            @Override
            public void onFailure(Exception e) {
                isLoading.setValue(false);
                Log.e("TAG", "onFailure: " + e.getLocalizedMessage());
            }
        });
    }

    private int getCorrectAnswersAmount() {
        int correctAnswerAmounts = 0;
        for (int i = 0; i <= mQuestions.size() - 1; i++) {
            if (mQuestions.get(i).getSelectedAnswerPosition() != -1) {
                String correctAnswer = mQuestions.get(i).getCorrectAnswers();
                String selectedAnswer = mQuestions.get(i).getAnswers()
                        .get(mQuestions.get(i).getSelectedAnswerPosition());
                if (correctAnswer.equals(selectedAnswer)) {
                    ++correctAnswerAmounts;
                }
            }
        }
        return correctAnswerAmounts;
    }

    void startTimeDown() {
        Log.e("TAG", "startTimeDown: " + mQuestions.get(currentQuestionsPosition.getValue()).getSelectedAnswerPosition());
        if (countDownTimer != null) countDownTimer.cancel();
//        if (mQuestions.get(currentQuestionsPosition.getValue()).getSelectedAnswerPosition() != null)
//            countDownTimer.cancel();
//        if (j > 0 && j < 29) {
//            i = j * 1000;
//        } else {
//            i = 30000;
//        }
        countDownTimer = new CountDownTimer(i, 1000) {

            public void onTick(long millisUntilFinished) {
                timeDown.setValue(millisUntilFinished / 1000);
//                j = millisUntilFinished / 1000;
//                Log.e("TAG", "onTick: " + j);
            }

            public void onFinish() {
                onSkipClick();
            }
        }.start();
    }

    void finishQuiz() {
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("d.MMM.yyyy, HH:mm");
        String date = df.format(Calendar.getInstance().getTime());
        QuizResult result = new QuizResult(
                id,
                mCategoryString,
                mDifficultyString,
                mQuestions,
                getCorrectAnswersAmount(),
                date
        );
        int resultId = App.historyStorage.saveQuizResult(result);
        finishEvent.call();
        openResultEvent.setValue(resultId);
    }

    void onAnswerClick(int position, int selectedAnswerPosition) {
        if (mQuestions.size() > position && position >= 0) {
            if (mQuestions.get(position).getSelectedAnswerPosition() == null) {
                mQuestions.get(position).setSelectedAnswerPosition(selectedAnswerPosition);
                question.setValue(mQuestions);
            }
            if (position + 1 == mQuestions.size()) {
                finishQuiz();
            } else {
                new CountDownTimer(500, 1000) {
                    public void onTick(long millisUntilFinished) {
                    }

                    public void onFinish() {
                        currentQuestionsPosition.setValue(++count);
                    }
                }.start();
            }
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public void onSkipClick() {
        Integer currentPosition = currentQuestionsPosition.getValue();
        if (currentPosition != null) {
            onAnswerClick(currentQuestionsPosition.getValue(), -1);
        } else {
            finishQuiz();
        }
    }

    public void onBackPressed() {
        Integer currentPosition = currentQuestionsPosition.getValue();
        if (currentPosition != null) {
            if (currentPosition != 0) {
                currentQuestionsPosition.setValue(--count);
                countDownTimer.cancel();
            } else {
                finishEvent.call();
                countDownTimer.cancel();
            }
        } else {
            finishEvent.call();
            countDownTimer.cancel();
        }
    }
}
