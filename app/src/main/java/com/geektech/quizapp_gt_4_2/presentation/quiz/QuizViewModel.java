package com.geektech.quizapp_gt_4_2.presentation.quiz;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

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
    private Boolean isClicked = true;
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
                    correctAnswerAmounts++;
                }
            }
        }
        return correctAnswerAmounts;
    }

    void startTimeDown() {
        if (countDownTimer != null) countDownTimer.cancel();
        countDownTimer = new CountDownTimer(i, 1000) {

            public void onTick(long millisUntilFinished) {
                timeDown.setValue(millisUntilFinished / 1000);
            }

            public void onFinish() {
                onSkipClick();
            }
        }.start();
    }

    @SuppressLint("SetTextI18n")
    void delayTime(RecyclerView recyclerView, Integer integer, TextView quizAmount, ProgressBar progressBar, TextView quizCategoryName, List<Question> questionsList, Integer amount) {
        if (isClicked) {
            new CountDownTimer(300, 1000) {
                public void onTick(long millisUntilFinished) {
                }

                @SuppressLint("SetTextI18n")
                public void onFinish() {
                    recyclerView.scrollToPosition(integer);
                    quizAmount.setText(integer + 1 + "/" + amount);
                    progressBar.setProgress(integer + 1);
                    progressBar.setMax(amount);
                    quizCategoryName.setText(questionsList.get(integer).getCategory());
                    cancel();
                }
            }.start();
        } else {
            recyclerView.scrollToPosition(integer);
            quizAmount.setText(integer + 1 + "/" + amount);
            progressBar.setProgress(integer + 1);
            progressBar.setMax(amount);
            quizCategoryName.setText(questionsList.get(integer).getCategory());
        }
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
                countDownTimer.cancel();
            } else {
                isClicked = true;
                currentQuestionsPosition.setValue(++count);
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
            isClicked = false;
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
                isClicked = false;
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
