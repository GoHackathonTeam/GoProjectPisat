package com.dekinci.lksbstu.communication;

import android.support.annotation.NonNull;
import android.util.Log;

import com.dekinci.lksbstu.communication.structure.DaySchedule;
import com.dekinci.lksbstu.communication.structure.Gradebook;
import com.dekinci.lksbstu.communication.structure.Login;
import com.dekinci.lksbstu.communication.structure.News;
import com.dekinci.lksbstu.communication.structure.Schedule;
import com.dekinci.lksbstu.communication.structure.pojos.User;
import com.dekinci.lksbstu.utils.FactCallback;
import com.dekinci.lksbstu.utils.ResultCallback;
import com.dekinci.lksbstu.utils.Synchronizer;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PolyLib implements PolyApi{

    ServerApi serverApi;
    private final static String LOG_TAG = "PolyLib";

    public PolyLib() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rawgit.com/startandroid/data/master/messages/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.serverApi = retrofit.create(ServerApi.class);
    }


    @Override
    public void getUserInfo(String user_id, ResultCallback<User> resultCallback) {
        Call<User> userCall = serverApi.getUserInfo(user_id);

        enqueueCall(userCall, resultCallback);
    }

    @Override
    public void login(String login, String password, FactCallback callback) {
        Call<Login> loginCall = serverApi.login(login, password);

        enqueueCall(loginCall, new ResultCallback<Login>() {
            @Override
            public void success(Login login) {
                callback.success();
            }

            @Override
            public void failure(Throwable throwable) {
                callback.failure(throwable);
            }
        });
    }

    @Override
    public void getSchedule(String group_id, String type, ResultCallback<List<DaySchedule>> resultCallback) {
        Call<List<DaySchedule>> scheduleCall = serverApi.getSchedule(group_id, type);

        enqueueCall(scheduleCall, resultCallback);
    }

    @Override
    public void getGradebook(String user_id, ResultCallback<List<Gradebook>> resultCallback) {
        Call<List<Gradebook>> gradebookCall = serverApi.getGradebook(user_id);

        enqueueCall(gradebookCall, resultCallback);
    }

    @Override
    public void sendTask(String user_id, String group_id, String msg, FactCallback factCallback) {
        Exception exception = new Exception();
        factCallback.failure(exception);
    }

    @Override
    public void sendDoc(FactCallback factCallback) {
        Exception exception = new Exception();
        factCallback.failure(exception);
    }

    @Override
    public void getNews(ResultCallback<List<News>> resultCallback) {

    }



    private <T> void enqueueCall(Call<T> call, ResultCallback<T> callback) {
        Log.v(LOG_TAG, "Enqueuing call");
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                printResponseInfo(response);

                if (response.body() != null)
                    callback.success(response.body());
                else
                    callback.failure(new Throwable("Something went wrong"));
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
