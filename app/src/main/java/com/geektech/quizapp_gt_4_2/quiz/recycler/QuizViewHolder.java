package com.geektech.quizapp_gt_4_2.quiz.recycler;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp_gt_4_2.R;

public class QuizViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private Listener listener;

    public QuizViewHolder(@NonNull View itemView, Listener listener) {
        super(itemView);
        name = itemView.findViewById(R.id.itemTV_question);
        this.listener = listener;

        listener.onAnswerClick(getAdapterPosition(), 0);
    }

    public void onBind(String s) {
        name.setText(s);
    }

    public interface Listener {
        void onAnswerClick(int position, int selectedAnswerPosition);
    }
}
