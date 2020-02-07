package com.geektech.quizapp_gt_4_2.model;


import java.util.Date;
import java.util.List;

public class QuizResult {
    private int id;
    private String category;
    private String difficulty;
    private List<Question> questions;
    private int correctAnswersAmount;
    private Date createdAt;

    public QuizResult(int id, String category, String difficulty, List<Question> questions, int correctAnswersAmount, Date createdAt) {
        this.id = id;
        this.category = category;
        this.difficulty = difficulty;
        this.questions = questions;
        this.correctAnswersAmount = correctAnswersAmount;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public int getCorrectAnswersAmount() {
        return correctAnswersAmount;
    }

    public void setCorrectAnswersAmount(int correctAnswersAmount) {
        this.correctAnswersAmount = correctAnswersAmount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
