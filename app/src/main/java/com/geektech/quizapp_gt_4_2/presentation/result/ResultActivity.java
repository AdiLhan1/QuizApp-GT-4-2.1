package com.geektech.quizapp_gt_4_2.presentation.result;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.presentation.main.MainActivity;

public class ResultActivity extends AppCompatActivity {
    ResultViewModel resultViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, ResultActivity.class));
    }

    public void btn_finish(View view) {
        MainActivity.start(this);
    }
}
