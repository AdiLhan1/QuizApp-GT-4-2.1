package com.geektech.quizapp_gt_4_2.data;

import androidx.lifecycle.LiveData;

import com.geektech.quizapp_gt_4_2.data.history.IHistoryStorage;
import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.model.History;
import com.geektech.quizapp_gt_4_2.model.Question;
import com.geektech.quizapp_gt_4_2.model.QuizResult;
import com.geektech.quizapp_gt_4_2.utils.ToastHelper;

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
        return question;
    }

    @Override
    public void getQuestions(int amount, Integer category, String difficulty, QuestionsCallback callback) {
            quizApiClient.getQuestions(amount, category, difficulty, new QuestionsCallback() {
                @Override
                public void onSuccess(List<Question> result) {
                    for (int i = 0; i < result.size(); i++) {
                        result.set(i, shuffleAnswers(result.get(i)));
                    }
                    callback.onSuccess(result);
                }

                @Override
                public void onFailure(Exception e) {
                    callback.onFailure(e);
                }
            });
    }

    @Override
    public QuizResult getQuizResult(int id) {
        return historyStorage.getQuizResult(id);
    }

    @Override
    public int saveQuizResult(QuizResult quizResult) {
        return historyStorage.saveQuizResult(quizResult);
    }

    @Override
    public LiveData<List<History>> getAllHistory() {
        return historyStorage.getAllHistory();
    }

    @Override
    public void delete(QuizResult quizResult) {
        historyStorage.delete(quizResult);
    }

    @Override
    public void deleteById(int id) {
        historyStorage.deleteById(id);
    }

    @Override
    public void deleteAll() {
        historyStorage.deleteAll();
    }


    @Override
    public LiveData<List<QuizResult>> getAll() {
        return null;
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
