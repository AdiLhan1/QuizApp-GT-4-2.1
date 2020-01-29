package com.geektech.quizapp_gt_4_2.data.remote;

import com.geektech.quizapp_gt_4_2.model.Question;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuizQuestionResponse {
    @SerializedName("response_code")
    private int responsecode;
    private List<Question> results;

    public int getResponsecode() {
        return responsecode;
    }

    public void setResponsecode(int responsecode) {
        this.responsecode = responsecode;
    }

    public List<Question> getResults() {
        return results;
    }

    public void setResults(List<Question> results) {
        this.results = results;
    }
}
