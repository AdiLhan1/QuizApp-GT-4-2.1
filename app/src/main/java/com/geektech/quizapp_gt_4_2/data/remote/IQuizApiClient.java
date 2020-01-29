package com.geektech.quizapp_gt_4_2.data.remote;

import com.geektech.quizapp_gt_4_2.model.Question;

import java.util.List;

public interface IQuizApiClient {
    void getQuestions(QuestionsCallback callback);

    interface QuestionsCallback {
        void onSuccess(List<Question> questions);

        void onFailure(Exception e);
    }
}
