package com.geektech.quizapp_gt_4_2.result;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.geektech.quizapp_gt_4_2.R;

public class ResultActivity extends AppCompatActivity {
    ResultViewModel resultViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);

    }
}
