package com.geektech.quizapp_gt_4_2.data.history;

import androidx.lifecycle.LiveData;

import com.geektech.quizapp_gt_4_2.model.QuizResult;

import java.util.List;

public interface IHistoryStorage {
    QuizResult getQuizResult(int id);

    int saveQuizResult(QuizResult quizResult);

    LiveData<List<QuizResult>> getAll();

    void delete(int id);

    void deleteAll();
}
