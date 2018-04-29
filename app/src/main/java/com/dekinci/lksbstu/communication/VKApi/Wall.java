package com.dekinci.lksbstu.communication.VKApi;

import android.support.annotation.NonNull;
import android.util.Log;

import com.dekinci.lksbstu.utils.ResultCallback;
import com.dekinci.lksbstu.utils.Synchronizer;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Wall {
    private final static String LOG_TAG = "Posts";

    VKApi vkApi;

    public Wall(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.vk.com/method/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.vkApi = retrofit.create(VKApi.class);

        System.out.println("LExa LOH");
    }

    public void getPolyMem(ResultCallback<List<VKposts>> resultCallback){
        Call<List<VKposts>> vkposts = vkApi.getPolyMem();
        System.out.println("LExa LOH");
        enqueueCall(vkposts, resultCallback);
    }

    private <T> void enqueueCall(Call<T> call, ResultCallback<T> callback) {
        Log.v(LOG_TAG, "Enqueuing call");
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                printResponseInfo(response);

                if (response.body() != null){
                    callback.success(response.body());
                }
                else {
                    callback.failure(new Throwable("Something went wrong"));
                }
            }

            @Override
            public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
                callback.failure(t);
                Log.e(LOG_TAG, t.toString());
            }
        });
        Log.v(LOG_TAG, "Call enqueued");
    }
    private <T> T executeCall(Call<T> call) throws IllegalStateException {
        Log.v(LOG_TAG, "Executing call");
        try {
            Response<T> response = call.execute();
            printResponseInfo(response);

            if (response.body() != null)
                return response.body();
            else
                throw new IllegalStateException("Response body is null");
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
    private String buildHeader() {
        Synchronizer<String> synchronizer = new Synchronizer<>();
        return "";
    }

    private String buildHeader(String token) {
        return String.format(" Bearer %s", token);
    }
    private <T> void printResponseInfo(Response<T> response) {
        if (response.errorBody() != null) try {
            Log.w(LOG_TAG, response.errorBody().string());
        } catch (Exception ignored) {
        }
        Log.v(LOG_TAG, "Enqueued call code: " + response.code());
    }
}
