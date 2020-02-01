package com.geektech.quizapp_gt_4_2.data.remote;

import android.util.Log;

import com.geektech.quizapp_gt_4_2.core.CoreCallback;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class QuizApiClient implements IQuizApiClient {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    TriviaApi client = retrofit.create(TriviaApi.class);

    @Override
    public void getQuestions(final QuestionsCallback callback) {
        final Call<QuizQuestionResponse> call = client.getQuestions(
                10,
                21,
                "medium"
        );
        Log.e("TAG", "getQuestions: URL-" + call.request().url());
        call.enqueue(new CoreCallback<QuizQuestionResponse>() {
            @Override
            public void onSuccess(QuizQuestionResponse result) {
                callback.onSuccess(result.getResults());
            }

            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);
            }
        });


    }

    @Override
    public void getCategories(final CategoriesCallback callback) {
        final Call<QuizCategoriesResponse> call = client.getCategories(10, 21, "easy");
        Log.e("TAG", "getCategories: URL-2-" + call.request().url());
        call.enqueue(new CoreCallback<QuizCategoriesResponse>() {
            @Override
            public void onSuccess(QuizCategoriesResponse result) {
                callback.onSuccess(result.getTriviaCategories());
            }

            @Override
            public void onFailure(Exception e) {
                callback.onFailure(e);

            }
        });
    }

    private interface TriviaApi {
        @GET("api.php")
        Call<QuizQuestionResponse> getQuestions(
                @Query("amount") int amount,
                @Query("category") int category,
                @Query("difficulty") String difficulty
        );

        @GET("api_category.php")
        Call<QuizCategoriesResponse> getCategories(@Query("amount") int amount,
                                                   @Query("category") int category,
                                                   @Query("difficulty") String difficulty);

    }
}