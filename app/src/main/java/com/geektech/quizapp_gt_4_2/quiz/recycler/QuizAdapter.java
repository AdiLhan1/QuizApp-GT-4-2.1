package com.geektech.quizapp_gt_4_2.quiz.recycler;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizViewHolder> {
    private List<Question> questionsList = new ArrayList<>();
    private QuizViewHolder.Listener listener;

    public QuizAdapter(QuizViewHolder.Listener listener) {
        this.listener = listener;
    }

    public void updateQuestion(List<Question> list) {
        this.questionsList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_item, parent, false);
        return new QuizViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        holder.onBind(questionsList.get(position));
        Log.e("TAG", "onBindViewHolder: " + position);
    }

    @Override
    public int getItemCount() {
        return questionsList.size();
    }

    public void setQuestionsList(List<Question> questionsList) {
        this.questionsList.clear();
        this.questionsList.addAll(questionsList);
        notifyDataSetChanged();
    }
}
