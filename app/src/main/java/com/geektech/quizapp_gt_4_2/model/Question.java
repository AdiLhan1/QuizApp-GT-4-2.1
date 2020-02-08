package com.geektech.quizapp_gt_4_2.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Question {
    private String category;
    private EType type;
    private String question;
    private EDifficulty difficulty;
    @SerializedName("correct_answer")
    private String correctAnswers;
    @SerializedName("incorrect_answers")
    private List<String> incorrectAnswers;

    private List<String> answers;

    private Integer selectedAnswerPosition;

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public Integer getSelectedAnswerPosition() {
        return selectedAnswerPosition;
    }

    public void setSelectedAnswerPosition(Integer selectedAnswerPosition) {
        this.selectedAnswerPosition = selectedAnswerPosition;
    }

    public Question() {
    }

    public Question(String category, EType type, String question, EDifficulty difficulty, String correctAnswers, List<String> incorrectAnswers) {
        this.category = category;
        this.type = type;
        this.question = question;
        this.difficulty = difficulty;
        this.correctAnswers = correctAnswers;
        this.incorrectAnswers = incorrectAnswers;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public EType getType() {
        return type;
    }

    public void setType(EType type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public EDifficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(EDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(String correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public List<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(List<String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }
}
