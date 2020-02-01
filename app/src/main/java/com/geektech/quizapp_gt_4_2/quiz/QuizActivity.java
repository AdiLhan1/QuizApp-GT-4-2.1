package com.geektech.quizapp_gt_4_2.quiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.model.Question;
import com.geektech.quizapp_gt_4_2.quiz.recycler.QuizAdapter;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    QuizViewModel quizViewModel;
    private static final String EXTRA_AMOUNT = "amount";
    private static final String EXTRA_CATEGORY = "category";
    private static final String EXTRA_DIFFICULTY = "difficulty";
    private RecyclerView recyclerView;
    private QuizAdapter adapter;
    private TextView quizCategoryName, quizAmount;
    private int amountStart = 0;
    private int amountQuantity;

    public static void start(
            Context context
            , Integer amount
            , String category
            , String difficulty
    ) {
        context.startActivity(new Intent(context, QuizActivity.class)
                .putExtra(EXTRA_AMOUNT, amount)
                .putExtra(EXTRA_CATEGORY, category)
                .putExtra(EXTRA_DIFFICULTY, difficulty)
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        recyclerView = findViewById(R.id.quiz_recyclerView);
        quizCategoryName = findViewById(R.id.quiz_categoryName);
        quizAmount = findViewById(R.id.quiz_amount);
        quizViewModel = ViewModelProviders.of(this).get(QuizViewModel.class);
//        quizViewModel.init();
        final ArrayList<Question> list = new ArrayList<>();
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        App.quizApiClient.getQuestions(new IQuizApiClient.QuestionsCallback() {
            @Override
            public void onSuccess(List<Question> questions) {
                list.addAll(questions);
                adapter = new QuizAdapter(list);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                Log.e("--------", questions.get(0).getQuestion() + " " + questions.get(0).getDifficulty());
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("TAG", "onFailure: " + e.getLocalizedMessage());
            }
        });
        quizAmount.setText(amountStart + "/" + getIntent().getIntExtra(EXTRA_AMOUNT, 10));
        quizCategoryName.setText(getIntent().getStringExtra(EXTRA_CATEGORY));
    }
}
