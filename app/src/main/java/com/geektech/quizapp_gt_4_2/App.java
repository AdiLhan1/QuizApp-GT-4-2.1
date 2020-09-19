package com.geektech.quizapp_gt_4_2;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.room.Room;

import com.geektech.quizapp_gt_4_2.data.QuizRepository;
import com.geektech.quizapp_gt_4_2.data.db.QuizDatabase;
import com.geektech.quizapp_gt_4_2.data.history.HistoryStorage;
import com.geektech.quizapp_gt_4_2.data.history.IHistoryStorage;
import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.data.remote.QuizApiClient;

public class App extends Application {
    public static IQuizApiClient quizApiClient;
    public static IHistoryStorage historyStorage;
    public static QuizRepository repository;
    public static QuizDatabase quizDatabase;
    @SuppressLint("StaticFieldLeak")
    public static Context instance;
//    public static FirebaseTranslator myTranslator;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        quizDatabase = Room.databaseBuilder(this,
                QuizDatabase.class,
                "quiz.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        quizDatabase.historyDao();

        repository = new QuizRepository(new QuizApiClient(), new HistoryStorage(quizDatabase.historyDao()));

        quizApiClient = repository;
        historyStorage = repository;

    }

//    private void fireBaseBuilder() {
//        FirebaseTranslatorOptions options =
//                new FirebaseTranslatorOptions.Builder()
//                        .setSourceLanguage(FirebaseTranslateLanguage.EN)
//                        .setTargetLanguage(FirebaseTranslateLanguage.RU)
//                        .build();
//
//        final FirebaseTranslator englishRussianTranslator =
//                FirebaseNaturalLanguage.getInstance().getTranslator(options);
//        FirebaseModelDownloadConditions conditions = new FirebaseModelDownloadConditions.Builder()
//                .requireWifi()
//                .build();
//
//        englishRussianTranslator.downloadModelIfNeeded(conditions)
//                .addOnSuccessListener(
//                        v -> {
//                            myTranslator = englishRussianTranslator;
//                            Log.e("TAG", "firebaseBuilder: " + englishRussianTranslator);
//                        })
//                .addOnFailureListener(
//                        e -> {
//                            Log.e("TAG", "firebaseBuilder: " + e.getLocalizedMessage());
//                        });
//
//    }
}
