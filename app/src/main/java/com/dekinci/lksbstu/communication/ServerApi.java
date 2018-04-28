package com.dekinci.lksbstu.communication;

import com.dekinci.lksbstu.communication.structure.Gradebook;
import com.dekinci.lksbstu.communication.structure.Schedule;
import com.dekinci.lksbstu.communication.structure.User;
import com.dekinci.lksbstu.utils.FactCallback;
import com.dekinci.lksbstu.utils.ResultCallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServerApi {
    @GET("getUserInfo")
    Call<User> getUserInfo(String user_id, ResultCallback resultCallback);

    @GET("login")
    Call<String> login(String login, String password, ResultCallback resultCallback);

    @GET("getSchedule")
    Call<List<Schedule>> getSchedule(String group_id, int type, ResultCallback resultCallback);

    @GET("getGradebook")
    Call<Gradebook> getGradebook(String user_id, ResultCallback resultCallback);

    @POST("sendTask")
    void sendTask(String user_id, String group_id, String msg, FactCallback factCallback);
}
