package com.geektech.quizapp_gt_4_2.presentation.result;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.geektech.quizapp_gt_4_2.R;

public class ResultActivity extends AppCompatActivity {
    ResultViewModel resultViewModel;
    private static String EXTRA_QUIZ_ID = "result_id";
    private Integer id;
    private TextView difficultValue;
    private TextView correctAnswerAmount;
    private TextView resultPercent;
    private TextView category;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        id = getIntent().getIntExtra(EXTRA_QUIZ_ID, 0);
        initView();
        resultViewModel.getResult(id);
        setViewContent();

    }

    private void setViewContent() {
        resultViewModel.quizResult.observe(this, quizResult -> {
            category.setText(quizResult.getCategory());
            difficultValue.setText(quizResult.getDifficulty());
            correctAnswerAmount.setText(quizResult.getCorrectAnswersAmount() + "/" + quizResult.getQuestions().size());
            int correctAnswersPercent = (int) ((double) quizResult.getCorrectAnswersAmount() / quizResult.getQuestions().size() * 100);
            resultPercent.setText(correctAnswersPercent + " %");
            if (correctAnswersPercent == 0) {
                imageView.setImageResource(R.drawable.looser);
            }
            if (correctAnswersPercent > 0 && correctAnswersPercent <= 30) {
                imageView.setImageResource(R.drawable.bad);
            }
            if (correctAnswersPercent > 30 && correctAnswersPercent <= 50) {
                imageView.setImageResource(R.drawable.not_bad);
            }
            if (correctAnswersPercent > 50 && correctAnswersPercent <= 89) {
                imageView.setImageResource(R.drawable.cool);
            }
            if (correctAnswersPercent > 89 && correctAnswersPercent <= 100){
                imageView.setImageResource(R.drawable.best_quality);
            }
        });
    }

    private void initView() {
        resultViewModel = ViewModelProviders.of(this).get(ResultViewModel.class);
        difficultValue = findViewById(R.id.tvResult_difficulty);
        correctAnswerAmount = findViewById(R.id.tvAnswerResult2);
        resultPercent = findViewById(R.id.tv_percent);
        category = findViewById(R.id.tvResult_category);
        imageView = findViewById(R.id.imgResult);
    }

    public static void start(Context context, Integer id) {
        context.startActivity(new Intent(context, ResultActivity.class).putExtra(EXTRA_QUIZ_ID, id));
    }

    public void btnFinish(View view) {
        finish();
    }
}
