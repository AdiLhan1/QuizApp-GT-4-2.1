package com.geektech.quizapp_gt_4_2;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.geektech.quizapp_gt_4_2.data.QuizRepository;
import com.geektech.quizapp_gt_4_2.data.db.QuizDatabase;
import com.geektech.quizapp_gt_4_2.data.history.HistoryStorage;
import com.geektech.quizapp_gt_4_2.data.history.IHistoryStorage;
import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.data.remote.QuizApiClient;
import com.geektech.quizapp_gt_4_2.model.History;

import java.util.List;

public class App extends Application {
    public static IQuizApiClient quizApiClient;
    public static IHistoryStorage historyStorage;
    public static QuizDatabase quizDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        quizDatabase = Room.databaseBuilder(this,
                QuizDatabase.class,
                "quiz.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        quizDatabase.historyDao();

        QuizRepository repository = new QuizRepository(
                new QuizApiClient(),
                new HistoryStorage(quizDatabase.historyDao())
        ) {
            @Override
            public LiveData<List<History>> getAllHistory() {
                return null;
            }
        };

        quizApiClient = repository;
        historyStorage = repository;
    }
}
