package com.geektech.quizapp_gt_4_2.data.remote;

import com.geektech.quizapp_gt_4_2.model.Global;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class QuizQuestionCount {
    @SerializedName("global")
    private Global global;
    @SerializedName("categories")
    private Map<Global, String> categories;

    public QuizQuestionCount(Global global, Map<Global, String> categories) {
        this.global = global;
        this.categories = categories;
    }

    public Global getGlobal() {
        return global;
    }

    public void setGlobal(Global global) {
        this.global = global;
    }

    public Map<Global, String> getCategories() {
        return categories;
    }

    public void setCategories(Map<Global, String> categories) {
        this.categories = categories;
    }
}
