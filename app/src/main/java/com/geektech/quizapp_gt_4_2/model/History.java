package com.geektech.quizapp_gt_4_2.model;

import java.util.Date;

public class History {
    private int id;
    private String categoryName;
    private int correctAnswers;
    private String difficulty;
    private int amount;

    private String date;

    public History() {
    }

    public History(int id, String categoryName, int correctAnswers, String difficulty, int amount, String date) {
        this.categoryName = categoryName;
        this.correctAnswers = correctAnswers;
        this.difficulty = difficulty;
        this.date = date;
        this.amount = amount;
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

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
