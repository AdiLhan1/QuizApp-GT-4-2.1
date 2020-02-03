package com.geektech.quizapp_gt_4_2.model;

import com.google.gson.annotations.SerializedName;

public class Global {
    @SerializedName("total_num_of_questions")
    private int totalNumofQuestion;
    @SerializedName("total_num_of_pending_questions")
    private int totalNumofPendingQuestion;
    @SerializedName("total_num_of_verified_questions")
    private int totalNumofVerifedQuestion;
    @SerializedName("total_num_of_rejected_questions")
    private int totalNumofRejectedQuestion;

    public int getTotalNumofQuestion() {
        return totalNumofQuestion;
    }

    public void setTotalNumofQuestion(int totalNumofQuestion) {
        this.totalNumofQuestion = totalNumofQuestion;
    }

    public int getTotalNumofPendingQuestion() {
        return totalNumofPendingQuestion;
    }

    public void setTotalNumofPendingQuestion(int totalNumofPendingQuestion) {
        this.totalNumofPendingQuestion = totalNumofPendingQuestion;
    }

    public int getTotalNumofVerifedQuestion() {
        return totalNumofVerifedQuestion;
    }

    public void setTotalNumofVerifedQuestion(int totalNumofVerifedQuestion) {
        this.totalNumofVerifedQuestion = totalNumofVerifedQuestion;
    }

    public int getTotalNumofRejectedQuestion() {
        return totalNumofRejectedQuestion;
    }

    public void setTotalNumofRejectedQuestion(int totalNumofRejectedQuestion) {
        this.totalNumofRejectedQuestion = totalNumofRejectedQuestion;
    }
}
