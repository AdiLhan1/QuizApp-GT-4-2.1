package com.geektech.quizapp_gt_4_2.data.history;

import androidx.lifecycle.LiveData;

import com.geektech.quizapp_gt_4_2.model.History;
import com.geektech.quizapp_gt_4_2.model.QuizResult;

import java.util.List;

public interface IHistoryStorage {
    QuizResult getQuizResult(int id);

    int saveQuizResult(QuizResult quizResult);

    LiveData<List<QuizResult>> getAll();

    LiveData<List<History>> getAllHistory();

    void delete(QuizResult quizResult);

    void deleteById(int id);

    void deleteAll();
}
