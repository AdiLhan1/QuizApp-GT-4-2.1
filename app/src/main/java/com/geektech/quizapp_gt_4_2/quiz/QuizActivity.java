package com.geektech.quizapp_gt_4_2.quiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.model.Question;
import com.geektech.quizapp_gt_4_2.quiz.recycler.QuizAdapter;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
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

    public static void start(Context context, Integer amount, String category, String difficulty) {
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
        adapter = new QuizAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return true;
            }
        });

    }

    private void initViews() {
        recyclerView = findViewById(R.id.quiz_recyclerView);
        quizCategoryName = findViewById(R.id.quiz_categoryName);
        quizAmount = findViewById(R.id.quiz_amount);
    }

    private void getQuestions() {
        amount = getIntent().getIntExtra(EXTRA_AMOUNT, 10);
        category = getIntent().getIntExtra(EXTRA_CATEGORY, 21);
        difficulty = getIntent().getStringExtra(EXTRA_DIFFICULTY);
        quizViewModel.getQuestions(amount, category, difficulty);
        quizViewModel.question.observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                questionsList = questions;
                adapter.updateQuestion(questions);
            }
        });

    }
}
