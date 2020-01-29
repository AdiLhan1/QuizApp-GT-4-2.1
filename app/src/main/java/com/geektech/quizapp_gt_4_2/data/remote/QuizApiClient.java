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
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    QuizApi client = retrofit.create(QuizApi.class);

    @Override
    public void getQuestions(final QuestionsCallback callback) {
        final Call<QuizQuestionResponse> call = client.getQuestions(
                10,
                null,
                "hard"
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

    private interface QuizApi {
        @GET("api.php")
        Call<QuizQuestionResponse> getQuestions(
                @Query("amount") int amount,
                @Query("category") String category,
                @Query("difficulty") String difficulty
        );
    }
}
