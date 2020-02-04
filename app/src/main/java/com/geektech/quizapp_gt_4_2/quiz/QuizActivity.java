package com.geektech.quizapp_gt_4_2.quiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.main.MainActivity;
import com.geektech.quizapp_gt_4_2.model.Question;
import com.geektech.quizapp_gt_4_2.quiz.recycler.QuizAdapter;
import com.geektech.quizapp_gt_4_2.quiz.recycler.QuizViewHolder;
import com.geektech.quizapp_gt_4_2.result.ResultActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements QuizViewHolder.Listener {
    QuizViewModel quizViewModel;
    private static final String EXTRA_AMOUNT = "amount";
    private static final String EXTRA_CATEGORY = "category";
    private static final String EXTRA_DIFFICULTY = "difficulty";
    private int amount;
    private int category;
    private String difficulty;
    private RecyclerView recyclerView;
    private List<Question> questionsList = new ArrayList<>();
    private QuizAdapter adapter;
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
        getQuestions();
        recyclerBuilder();
        quizAmount.setText(amountQuantity + "/" + getIntent().getIntExtra(EXTRA_AMOUNT, 10));
        quizCategoryName.setText(getIntent().getStringExtra(EXTRA_CATEGORY));
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
        recyclerView = findViewById(R.id.quiz_recyclerView);
        quizCategoryName = findViewById(R.id.quiz_categoryName);
        quizAmount = findViewById(R.id.quiz_amount);
        progressBar = findViewById(R.id.quiz_item_progressBar);
    }

    private void getQuestions() {
        amount = getIntent().getIntExtra(EXTRA_AMOUNT, 10);
        category = getIntent().getIntExtra(EXTRA_CATEGORY, 21);
        difficulty = getIntent().getStringExtra(EXTRA_DIFFICULTY);
        Log.e("TAG", "___++____++__+_+ " + amount + " " + category + " " + difficulty);
        quizViewModel.getQuestions(amount, category, difficulty);
        quizViewModel.question.observe(this, questions -> {
            questionsList = questions;
            adapter.updateQuestion(questions);
            getPosition();
        });
    }

    private void getPosition() {
        quizViewModel.currentPosition.observe(this, integer -> {
            recyclerView.scrollToPosition(integer - 1);
            quizAmount.setText(integer + "/" + amount);
            progressBar.setProgress(integer);
            progressBar.setMax(amount);
            if (questionsList.size() > 0)
                quizCategoryName.setText(questionsList.get(integer - 1).getCategory());
            Log.e("порядок", "2");
        });
    }

    public void btn_skip_click(View view) {
        if (progressBar.getProgress() < amount) {
            quizViewModel.nextPage();
        } else {
            ResultActivity.start(this);
        }
    }

    public void btn_back_click(View view) {
        if (progressBar.getProgress() != 1) {
            quizViewModel.prevPage();
        } else {
            MainActivity.start(this);
            finish();
        }
    }

    @Override
    public void onAnswerClick(int position, int selectedAnswerPosition) {

    }
}
