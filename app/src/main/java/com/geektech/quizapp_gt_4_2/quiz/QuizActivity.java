package com.geektech.quizapp_gt_4_2.quiz;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.geektech.quizapp_gt_4_2.R;

public class QuizActivity extends AppCompatActivity {
    QuizViewModel mViewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        mViewmodel = ViewModelProviders.of(this).get(QuizViewModel.class);
    }
}
