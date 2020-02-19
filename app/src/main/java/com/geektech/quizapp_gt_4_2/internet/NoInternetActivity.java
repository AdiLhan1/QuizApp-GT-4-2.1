package com.geektech.quizapp_gt_4_2.internet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.presentation.quiz.QuizActivity;
import com.geektech.quizapp_gt_4_2.utils.CheckInternetConnection;

public class NoInternetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_internet);
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, NoInternetActivity.class));
    }

    public void btnRetry(View view) {
        if (CheckInternetConnection.isNetworkAvailable()) {
            QuizActivity.start(NoInternetActivity.this);
            finish();
        } else {
            return;
        }
    }
}
