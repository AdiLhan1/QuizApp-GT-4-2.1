package com.geektech.quizapp_gt_4_2.model;

public class History {
    private String categoryName;
    private String correctAnswers;
    private String difficulty;
    private String date;
    private int id;

    public History() {
    }

    public History(String categoryName, String correctAnswers, String difficulty, String date, int id) {
        this.categoryName = categoryName;
        this.correctAnswers = correctAnswers;
        this.difficulty = difficulty;
        this.date = date;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(String correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
