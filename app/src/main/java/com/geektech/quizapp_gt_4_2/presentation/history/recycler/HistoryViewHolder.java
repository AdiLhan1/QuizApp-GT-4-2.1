package com.geektech.quizapp_gt_4_2.presentation.history.recycler;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.model.History;

import java.util.ArrayList;

public class HistoryViewHolder extends RecyclerView.ViewHolder {
    TextView category, answers, difficulty, date;

    public HistoryViewHolder(@NonNull View itemView) {
        super(itemView);
        category = itemView.findViewById(R.id.history_category);
        answers = itemView.findViewById(R.id.history_correctAnswers);
        difficulty = itemView.findViewById(R.id.history_difficulty);
        date = itemView.findViewById(R.id.history_date);
    }

    public void onBind(ArrayList<History> historyList, int pos) {
        category.setText(historyList.get(pos).getCategoryName());
        answers.setText(historyList.get(pos).getCorrectAnswers());
        difficulty.setText(historyList.get(pos).getDifficulty());
        date.setText(String.valueOf(historyList.get(pos).getDate()));
    }
}
