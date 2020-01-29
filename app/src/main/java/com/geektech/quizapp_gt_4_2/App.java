package com.geektech.quizapp_gt_4_2;

import android.app.Application;

import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.data.remote.QuizApiClient;

public class App extends Application {
    public static IQuizApiClient quizApiClient;
    @Override
    public void onCreate() {
        super.onCreate();
        quizApiClient = new QuizApiClient();
    }

}
