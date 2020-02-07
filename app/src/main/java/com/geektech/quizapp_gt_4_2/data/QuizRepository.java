package com.geektech.quizapp_gt_4_2.data;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.data.history.IHistoryStorage;
import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.model.Question;
import com.geektech.quizapp_gt_4_2.model.QuizResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizRepository implements IHistoryStorage, IQuizApiClient {
    private IQuizApiClient quizApiClient;
    private IHistoryStorage historyStorage;

    public QuizRepository(
            IQuizApiClient client,
            IHistoryStorage storage
    ) {
        quizApiClient = client;
        historyStorage = storage;
    }

    private Question shuffleAnswers(Question question) {
        ArrayList<String> answers = new ArrayList<>();

        answers.add(question.getCorrectAnswers());
        answers.addAll(question.getIncorrectAnswers());

        Collections.shuffle(answers);
        question.setAnswers(answers);
        Log.e("TAG", "shuffleAnswers: "+answers );
        return question;
    }

    @Override
    public void getQuestions(int amount, Integer category, String difficulty, QuestionsCallback callback) {
        App.quizApiClient.getQuestions(amount, category, difficulty, new IQuizApiClient.QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> result) {
                for (int i = 0; i < result.size(); i++) {
                    Question q = result.get(i);
                    result.set(i, shuffleAnswers(q));
                    Log.e("TAG", "onSuccess:11111111 " + result.get(i));
                }
                Log.e("----------",  result+"");
                callback.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("TAG", "onFailure: " + e);
                callback.onFailure(e);
            }
        });
    }

    @Override
    public QuizResult getQuizResult(int id) {
        return null;
    }

    @Override
    public int saveQuizResult(QuizResult quizResult) {
        return 0;
    }

    @Override
    public LiveData<List<QuizResult>> getAll() {
        return null;
    }

    @Override
    public void delete(int id) {
        historyStorage.delete(id);
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void getCategories(CategoriesCallback callback) {

    }

    @Override
    public void getCountGlobal(CountGlobalCallback callback) {

    }

    @Override
    public void getQuestionCount(Integer category, QuestionCountCallback questionCount) {

    }
}
