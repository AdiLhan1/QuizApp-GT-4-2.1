package com.geektech.quizapp_gt_4_2.main;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geektech.quizapp_gt_4_2.App;
import com.geektech.quizapp_gt_4_2.data.remote.IQuizApiClient;
import com.geektech.quizapp_gt_4_2.data.remote.QuizGlobalResponse;
import com.geektech.quizapp_gt_4_2.data.remote.QuizQuestionCount;
import com.geektech.quizapp_gt_4_2.model.Category;

import java.util.List;

public class MainViewModel extends ViewModel {

    public MainViewModel() {
        Log.d("ololo", "View model create");
    }

    public MutableLiveData<List<Category>> categoriesLiveData = new MutableLiveData<>();
    public MutableLiveData<QuizGlobalResponse> globalLiveData = new MutableLiveData<>();
    public MutableLiveData<QuizQuestionCount> questionCountLiveData = new MutableLiveData<>();

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public void getGlobal() {
        App.quizApiClient.getCountGlobal(new IQuizApiClient.CountGlobalCallback() {
            @Override
            public void onSuccess(QuizGlobalResponse result) {
                globalLiveData.setValue(result);
                Log.e("TAG", "Global " + result.getCategories() + result.getGlobal());
            }

            @Override
            public void onFailure(Exception e) {
                Log.e("TAG", "onFailure: countGlobal " + e);
            }
        });
    }

    public void getCategories() {
        App.quizApiClient.getCategories(new IQuizApiClient.CategoriesCallback() {
            @Override
            public void onSuccess(List<Category> result) {
                categoriesLiveData.setValue(result);
                Log.e("TAG", "Categories: " + result.get(0).getName() + result.get(0).getId());
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    public void getQuestionsCount(Integer integer) {
        App.quizApiClient.getQuestionCount(integer, new IQuizApiClient.QuestionCountCallback() {
            @Override
            public void onSuccess(QuizQuestionCount result) {
                questionCountLiveData.setValue(result);
                Log.e("TAG", "QuestionCount: " + result.toString());
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }


}
