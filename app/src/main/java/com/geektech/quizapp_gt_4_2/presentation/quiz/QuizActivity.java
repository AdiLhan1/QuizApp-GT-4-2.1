package com.geektech.quizapp_gt_4_2.presentation.quiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.model.Question;
import com.geektech.quizapp_gt_4_2.presentation.main.MainActivity;
import com.geektech.quizapp_gt_4_2.presentation.quiz.recycler.QuizAdapter;
import com.geektech.quizapp_gt_4_2.presentation.quiz.recycler.QuizViewHolder;
import com.geektech.quizapp_gt_4_2.presentation.result.ResultActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements QuizViewHolder.Listener {
    QuizViewModel quizViewModel;
    private static final String EXTRA_AMOUNT = "amount";
    private static final String EXTRA_CATEGORY = "category";
    private static final String EXTRA_DIFFICULTY = "difficulty";
    private int amount;
    private Integer category;
    private Button btnSkip;
    private String difficulty;
    private RecyclerView recyclerView;
    private ConstraintLayout layout;
    private List<Question> questionsList = new ArrayList<>();
    private QuizAdapter adapter;
    private LottieAnimationView lottieAnimationView;
    private TextView quizCategoryName, quizAmount;
    private int amountQuantity;
    private ProgressBar progressBar;

    public static void start(Context context, Integer amount, Integer category, String difficulty) {
        context.startActivity(new Intent(context, QuizActivity.class)
                .putExtra(EXTRA_AMOUNT, amount)
                .putExtra(EXTRA_CATEGORY, category)
                .putExtra(EXTRA_DIFFICULTY, difficulty)
        );
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
        initViews();
        initLoading();
        getQuestions();
        recyclerBuilder();
        quizAmount.setText(amountQuantity + "/" + getIntent().getIntExtra(EXTRA_AMOUNT, 10));
        quizCategoryName.setText(getIntent().getStringExtra(EXTRA_CATEGORY));
    }

    private void initLoading() {
        quizViewModel.isLoading.observe(this, aBoolean -> {
            if (aBoolean) {
                layout.setVisibility(View.INVISIBLE);
            } else {
                lottieAnimationView.setVisibility(View.GONE);
                layout.setVisibility(View.VISIBLE);
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void recyclerBuilder() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(manager);
        adapter = new QuizAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnTouchListener((v, event) -> true);

    }

    private void initViews() {
        lottieAnimationView = findViewById(R.id.animation_view_loading);
        layout = findViewById(R.id.layout);
        btnSkip = findViewById(R.id.btn_skip);
        recyclerView = findViewById(R.id.quiz_recyclerView);
        quizCategoryName = findViewById(R.id.quiz_categoryName);
        quizAmount = findViewById(R.id.quiz_amount);
        progressBar = findViewById(R.id.quiz_item_progressBar);
    }

    private void getQuestions() {
        amount = getIntent().getIntExtra(EXTRA_AMOUNT, 10);
        category = getIntent().getIntExtra(EXTRA_CATEGORY, 21);
        difficulty = getIntent().getStringExtra(EXTRA_DIFFICULTY);
        if (category == 8) {
            category = 0;
            questionObserver();
        } else {
            questionObserver();
        }

    }

    private void questionObserver() {
        quizViewModel.getQuestions(amount, category, difficulty);
        quizViewModel.question.observe(this, questions -> {
            questionsList = questions;
            adapter.updateQuestion(questions);
            getPosition();
        });
    }


    @SuppressLint("SetTextI18n")
    private void getPosition() {
        quizViewModel.currentQuestionsPosition.observe(this, integer -> {
            Log.e("TAG", "getPosition: integer: " + integer);
            recyclerView.scrollToPosition(integer);
            quizAmount.setText(integer + 1 + "/" + amount);
            progressBar.setProgress(integer + 1);
            progressBar.setMax(amount);
            quizCategoryName.setText(questionsList.get(integer).getCategory());
            if (integer + 1 == questionsList.size()) {
                btnSkip.setText(R.string.finish);
            } else {
                btnSkip.setText(R.string.skip);
            }
        });
    }

    public void btn_skip_click(View view) {
        if (progressBar.getProgress() < amount) {
            quizViewModel.onSkipClick();
        } else {
            ResultActivity.start(this);
        }
    }

    public void btn_back_click(View view) {
        if (progressBar.getProgress() != 1) {
            quizViewModel.onBackPressed();
        } else {
            MainActivity.start(this);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (progressBar.getProgress() != 1) {
            quizViewModel.onBackPressed();
        } else {
            MainActivity.start(this);
            finish();
        }
    }

    @Override
    public void onAnswerClick(int position, int selectedAnswerPosition) {
        quizViewModel.onAnswerClick(position, selectedAnswerPosition);
    }
}
