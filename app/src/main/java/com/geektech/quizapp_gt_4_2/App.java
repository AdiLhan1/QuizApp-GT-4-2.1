package com.geektech.quizapp_gt_4_2;

import android.app.Application;

import com.geektech.quizapp_gt_4_2.data.QuizRepository;
import com.geektech.quizapp_gt_4_2.data.history.HistoryStorage;
import com.geektech.quizapp_gt_4_2.data.history.IHistoryStorage;
import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.data.remote.QuizApiClient;

public class App extends Application {
    public static IQuizApiClient quizApiClient;
    public static IHistoryStorage historyStorage;

    @Override
    public void onCreate() {
        super.onCreate();
        QuizRepository repository = new QuizRepository(
                new QuizApiClient(),
                new HistoryStorage()
        );

        quizApiClient = repository;
        historyStorage = repository;
    }
}
