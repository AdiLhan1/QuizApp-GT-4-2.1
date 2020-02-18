package com.geektech.quizapp_gt_4_2.presentation.quiz.recycler;

import android.graphics.Color;
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
    private Button multipleButton1, multipleButton2, multipleButton3, multipleButton4, booleanBtnTrue, booleanBtnFalse;
    private LinearLayout buttonMultiple, booleanButtons;

    public QuizViewHolder(@NonNull View itemView, Listener listener) {
        super(itemView);
        initViews(itemView);
        this.listener = listener;
        clickListener();
    }

    private void clickListener() {
        multipleButton1.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 0));
        multipleButton2.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 1));
        multipleButton3.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 2));
        multipleButton4.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 3));
        booleanBtnTrue.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 0));
        booleanBtnFalse.setOnClickListener(v -> listener.onAnswerClick(getAdapterPosition(), 1));
    }

    private void initViews(View itemView) {
        name = itemView.findViewById(R.id.itemTV_question);
        multipleButton1 = itemView.findViewById(R.id.quiz_item_btn1);
        multipleButton2 = itemView.findViewById(R.id.quiz_item_btn2);
        multipleButton3 = itemView.findViewById(R.id.quiz_item_btn3);
        multipleButton4 = itemView.findViewById(R.id.quiz_item_btn4);
        booleanBtnFalse = itemView.findViewById(R.id.quiz_item_false);
        booleanBtnTrue = itemView.findViewById(R.id.quiz_item_true);
        buttonMultiple = itemView.findViewById(R.id.quiz_linear_btnMultiple);
        booleanButtons = itemView.findViewById(R.id.quiz_linear_btnSimple);
    }

    private void setButton(Boolean enabled) {
        multipleButton1.setEnabled(enabled);
        multipleButton2.setEnabled(enabled);
        multipleButton3.setEnabled(enabled);
        multipleButton4.setEnabled(enabled);
        booleanBtnTrue.setEnabled(enabled);
        booleanBtnFalse.setEnabled(enabled);
    }


    public void onBind(Question question) {
        resetButtons();
        if (question.getSelectedAnswerPosition() == null) {
            setButton(true);
        } else {
            setButton(false);
        }
        name.setText(Html.fromHtml(
                question.getQuestion()));
        if (question.getType() == EType.MULTIPLE) {
            buttonMultiple.setVisibility(View.VISIBLE);
            booleanButtons.setVisibility(View.INVISIBLE);
            setTextButtons(question, multipleButton1, multipleButton2, multipleButton3, multipleButton4);
        } else {
            booleanButtons.setVisibility(View.VISIBLE);
            buttonMultiple.setVisibility(View.INVISIBLE);
        }
        if (question.getSelectedAnswerPosition() != null) {
            setSelected(question);
        }
    }

    private void setTextButtons(Question question, Button... buttons) {
        int i = 0;
        for (Button button : buttons) {
            button.setText(Html.fromHtml(question.getAnswers().get(i)));
            i++;
        }
    }

    private void resetButtons() {
        resetAnswerButtons(multipleButton1, multipleButton2, multipleButton3, multipleButton4, booleanBtnTrue, booleanBtnFalse);
    }

    private void resetAnswerButtons(Button... buttons) {
        for (Button button : buttons) {
            button.setBackgroundResource(R.drawable.btn_style);
            button.setTextColor(itemView.getResources().getColor(R.color.btn_color));
        }
    }

    private void setSelected(Question question) {
        if (question.getSelectedAnswerPosition() != null) {
            switch (question.getSelectedAnswerPosition()) {
                case 0:
                    if (question.getCorrectAnswers().equals(question.getAnswers().get(0))) {
                        multipleButton1.setBackgroundResource(R.drawable.btn_correct_answer);
                        booleanBtnTrue.setBackgroundResource(R.drawable.btn_correct_answer);
                        multipleButton1.setTextColor(Color.WHITE);
                        booleanBtnTrue.setTextColor(Color.WHITE);
                    } else {
                        multipleButton1.setBackgroundResource(R.drawable.btn_incorrect_answer);
                        booleanBtnTrue.setBackgroundResource(R.drawable.btn_incorrect_answer);
                        multipleButton1.setTextColor(Color.WHITE);
                        booleanBtnTrue.setTextColor(Color.WHITE);
                    }
                    break;
                case 1:
                    if (question.getCorrectAnswers().equals(question.getAnswers().get(1))) {
                        multipleButton2.setBackgroundResource(R.drawable.btn_correct_answer);
                        booleanBtnFalse.setBackgroundResource(R.drawable.btn_correct_answer);
                        multipleButton2.setTextColor(Color.WHITE);
                        booleanBtnFalse.setTextColor(Color.WHITE);
                    } else {
                        multipleButton2.setBackgroundResource(R.drawable.btn_incorrect_answer);
                        booleanBtnFalse.setBackgroundResource(R.drawable.btn_incorrect_answer);
                        multipleButton2.setTextColor(Color.WHITE);
                        booleanBtnFalse.setTextColor(Color.WHITE);
                    }
                    break;
                case 2:
                    if (question.getAnswers().get(2).equals(question.getCorrectAnswers())) {
                        multipleButton3.setBackgroundResource(R.drawable.btn_correct_answer);
                        multipleButton3.setTextColor(Color.WHITE);
                    } else {
                        multipleButton3.setBackgroundResource(R.drawable.btn_incorrect_answer);
                        multipleButton3.setTextColor(Color.WHITE);
                    }
                    break;
                case 3:
                    if (question.getAnswers().get(3).equals(question.getCorrectAnswers())) {
                        multipleButton4.setBackgroundResource(R.drawable.btn_correct_answer);
                        multipleButton4.setTextColor(Color.WHITE);
                    } else {
                        multipleButton4.setBackgroundResource(R.drawable.btn_incorrect_answer);
                        multipleButton4.setTextColor(Color.WHITE);
                    }
                    break;
            }
        }
    }

    public interface Listener {
        void onAnswerClick(int position, int selectedAnswerPosition);
    }
}
