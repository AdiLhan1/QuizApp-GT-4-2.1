package com.geektech.quizapp_gt_4_2.quiz.recycler;

import android.text.Html;
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
    private Button btn1, btn2, btn3, btn4, btnTrue, btnFalse;
    private LinearLayout multiple, single;

    public QuizViewHolder(@NonNull View itemView, Listener listener) {
        super(itemView);
        name = itemView.findViewById(R.id.itemTV_question);
        btn1 = itemView.findViewById(R.id.quiz_item_btn1);
        btn2 = itemView.findViewById(R.id.quiz_item_btn2);
        btn3 = itemView.findViewById(R.id.quiz_item_btn3);
        btn4 = itemView.findViewById(R.id.quiz_item_btn4);
        btnFalse = itemView.findViewById(R.id.quiz_item_false);
        btnTrue = itemView.findViewById(R.id.quiz_item_true);
        multiple = itemView.findViewById(R.id.quiz_linear_btnMultiple);
        single = itemView.findViewById(R.id.quiz_linear_btnSimple);
        this.listener = listener;

        listener.onAnswerClick(getAdapterPosition(), 0);
    }

    public void onBind(Question question) {
        name.setText(Html.fromHtml(
                question.getQuestion()));
        if (question.getType() == EType.MULTIPLE) {
            multiple.setVisibility(View.VISIBLE);
            single.setVisibility(View.INVISIBLE);
            btn1.setText(Html.fromHtml(
                    question.getAnswers().get(0)));
            btn2.setText(Html.fromHtml(
                    question.getAnswers().get(1)));
            btn3.setText(Html.fromHtml(
                    question.getAnswers().get(2)));
            btn4.setText(Html.fromHtml(
                    question.getAnswers().get(3)));
        } else {
            single.setVisibility(View.VISIBLE);
            multiple.setVisibility(View.INVISIBLE);
            btnTrue.setText(Html.fromHtml(
                    question.getAnswers().get(0)
            ));
            btnFalse.setText(Html.fromHtml(
                    question.getAnswers().get(1)
            ));
        }
    }

    public interface Listener {
        void onAnswerClick(int position, int selectedAnswerPosition);
    }
}
