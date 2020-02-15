package com.geektech.quizapp_gt_4_2.presentation.history.recycler;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.model.History;

public class HistoryViewHolder extends RecyclerView.ViewHolder {
    private TextView category, answers, difficulty, date;
    private ImageView dots;
    private Listener listener;

    public HistoryViewHolder(@NonNull View itemView, Listener listener) {
        super(itemView);
        this.listener = listener;
        category = itemView.findViewById(R.id.history_category);
        answers = itemView.findViewById(R.id.history_correctAnswers);
        difficulty = itemView.findViewById(R.id.history_difficulty);
        date = itemView.findViewById(R.id.history_date);
        dots = itemView.findViewById(R.id.dots);
        dots.setOnClickListener(v -> {
            listener.onClick(v, getAdapterPosition());
        });
    }

    public void onBind(History history) {
        Log.e("TAG", "onBind: " + history.getCategoryName());
        category.setText(history.getCategoryName());
        answers.setText(history.getCorrectAnswers() + "/" + history.getAmount());
        difficulty.setText(history.getDifficulty());
        date.setText(String.valueOf(history.getDate().toString()));
    }

    public interface Listener {
        void onClick(View view, int id);
    }
}
