package com.geektech.quizapp_gt_4_2.data.remote;

import com.geektech.quizapp_gt_4_2.core.IBaseCallback;
import com.geektech.quizapp_gt_4_2.model.Category;
import com.geektech.quizapp_gt_4_2.model.Question;

import java.util.List;

public interface IQuizApiClient {
    void getQuestions(int amount, Integer category, String difficulty, QuestionsCallback callback);

//    void getQuestions(QuestionsCallback callback);

    void getCategories(CategoriesCallback callback);

    void getCountGlobal(CountGlobalCallback callback);

    void getQuestionCount(Integer category, QuestionCountCallback questionCount);

    interface QuestionsCallback extends IBaseCallback<List<Question>> {
    }

    interface CategoriesCallback extends IBaseCallback<List<Category>> {
    }

    interface CountGlobalCallback extends IBaseCallback<QuizGlobalResponse> {

    }

    interface QuestionCountCallback extends IBaseCallback<QuizQuestionCount> {

    }
}
