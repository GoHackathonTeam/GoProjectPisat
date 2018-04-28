package com.dekinci.lksbstu.communication;

import com.dekinci.lksbstu.communication.structure.User;
import com.dekinci.lksbstu.utils.FactCallback;
import com.dekinci.lksbstu.utils.ResultCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PolyLib implements PolyApi{

    ServerApi serverApi;

    public PolyLib() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rawgit.com/startandroid/data/master/messages/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServerApi serverApi = retrofit.create(ServerApi.class);
    }

    @Override
    public void getUserInfo(String user_id, ResultCallback resultCallback) {
        Call<User> user = serverApi.getUserInfo(user_id, resultCallback);

        user.enqueue(Callback<>);
    }

    @Override
    public void login(String login, String password, ResultCallback resultCallback) {

    }

    @Override
    public void getSchedule(String group_id, int type, ResultCallback resultCallback) {

    }

    @Override
    public void getGradebook(String user_id, ResultCallback resultCallback) {

    }

    @Override
    public void sendTask(String user_id, String group_id, String msg, FactCallback factCallback) {

    }

    @Override
    public void sendDoc(FactCallback factCallback) {

    }

    @Override
    public void getNews(ResultCallback resultCallback) {

    }
}
