package com.geektech.quizapp_gt_4_2.presentation.quiz.recycler;

import android.text.Html;
import android.util.Log;
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
    private Question question;

    public QuizViewHolder(@NonNull View itemView, Listener listener) {
        super(itemView);
        initViews(itemView);
        this.listener = listener;
        clickListener();
        listener.onAnswerClick(getAdapterPosition(), 0);
    }

    private void clickListener() {
        btn1.setOnClickListener(v -> {
            listener.onAnswerClick(getAdapterPosition(), 0);
            if (question.getAnswers().get(0).equals(question.getCorrectAnswers())) {
                Log.e("TAG", "clickListener: CORRECT ANSWER:" + question.getCorrectAnswers());
                btn1.setBackgroundResource(R.color.colorGreen);
            } else {
                btn1.setBackgroundResource(R.color.colorRed);
            }
        });
        btn2.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 1));
        btn3.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 2));
        btn4.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 3));
        btnTrue.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 4));
        btnFalse.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 5));
    }

    private void initViews(View itemView) {
        name = itemView.findViewById(R.id.itemTV_question);
        btn1 = itemView.findViewById(R.id.quiz_item_btn1);
        btn2 = itemView.findViewById(R.id.quiz_item_btn2);
        btn3 = itemView.findViewById(R.id.quiz_item_btn3);
        btn4 = itemView.findViewById(R.id.quiz_item_btn4);
        btnFalse = itemView.findViewById(R.id.quiz_item_false);
        btnTrue = itemView.findViewById(R.id.quiz_item_true);
        multiple = itemView.findViewById(R.id.quiz_linear_btnMultiple);
        single = itemView.findViewById(R.id.quiz_linear_btnSimple);
    }

    private void setButton(Boolean enabled) {
        btn1.setEnabled(enabled);
        btn2.setEnabled(enabled);
        btn3.setEnabled(enabled);
        btn4.setEnabled(enabled);
        btnTrue.setEnabled(enabled);
        btnFalse.setEnabled(enabled);
    }

    public void onBind(Question question) {
        this.question = question;
        setBackgroundButtons();
        if (question.getSelectedAnswerPosition() == null) {
            setButton(true);
        } else {
            setButton(false);
        }
        name.setText(Html.fromHtml(
                question.getQuestion()));
        if (question.getType() == EType.MULTIPLE) {
            multiple.setVisibility(View.VISIBLE);
            single.setVisibility(View.INVISIBLE);
            btn1.setText(Html.fromHtml(question.getAnswers().get(0)));
            btn2.setText(Html.fromHtml(question.getAnswers().get(1)));
            btn3.setText(Html.fromHtml(question.getAnswers().get(2)));
            btn4.setText(Html.fromHtml(question.getAnswers().get(3)));

        } else {
            single.setVisibility(View.VISIBLE);
            multiple.setVisibility(View.INVISIBLE);
        }
    }

    private void setBackgroundButtons() {
        if (btn1.getBackground() == null) {
            btn1.setBackgroundResource(R.drawable.btn_style);
        }
    }

    public interface Listener {
        void onAnswerClick(int position, int selectedAnswerPosition);
    }
}
