package com.geektech.quizapp_gt_4_2.quiz.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.model.Question;

import java.util.ArrayList;

public class QuizAdapter extends RecyclerView.Adapter<QuizViewHolder> {
    private ArrayList<Question> questionsList;

    public QuizAdapter(ArrayList<Question> list) {
        questionsList = list;
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_item, parent, false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        holder.onBind(questionsList.get(position).getQuestion());
    }

    @Override
    public int getItemCount() {
        return questionsList.size();
    }
}
