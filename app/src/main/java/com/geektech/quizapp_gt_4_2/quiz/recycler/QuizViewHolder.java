package com.geektech.quizapp_gt_4_2.quiz.recycler;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.quizapp_gt_4_2.R;
import com.geektech.quizapp_gt_4_2.model.EType;
import com.geektech.quizapp_gt_4_2.model.Question;

public class QuizViewHolder extends RecyclerView.ViewHolder {
    private TextView name;
    private Listener listener;
    private Button btn1;
    private LinearLayout multiple, single;

    public QuizViewHolder(@NonNull View itemView, Listener listener) {
        super(itemView);
        name = itemView.findViewById(R.id.itemTV_question);
        btn1=itemView.findViewById(R.id.quiz_item_btn1);
        multiple = itemView.findViewById(R.id.quiz_linear_btnMultiple);
        single = itemView.findViewById(R.id.quiz_linear_btnSimple);
        this.listener = listener;

        listener.onAnswerClick(getAdapterPosition(), 0);
    }

    public void onBind(Question question) {
        name.setText(question.getQuestion());
        if (question.getType() == EType.MULTIPLE) {
            multiple.setVisibility(View.VISIBLE);
            single.setVisibility(View.INVISIBLE);
        } else {
            single.setVisibility(View.VISIBLE);
            multiple.setVisibility(View.INVISIBLE);
        }
//        btn1.setText(question.getAnswers().get(0));
    }

    public interface Listener {
        void onAnswerClick(int position, int selectedAnswerPosition);
    }
}
