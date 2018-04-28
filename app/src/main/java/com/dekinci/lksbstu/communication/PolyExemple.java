package com.dekinci.lksbstu.communication;

import com.dekinci.lksbstu.communication.structure.Gradebook;
import com.dekinci.lksbstu.communication.structure.Login;
import com.dekinci.lksbstu.communication.structure.News;
import com.dekinci.lksbstu.communication.structure.Schedule;
import com.dekinci.lksbstu.communication.structure.User;
import com.dekinci.lksbstu.utils.FactCallback;
import com.dekinci.lksbstu.utils.ResultCallback;

import java.util.List;

public class PolyExemple implements PolyApi {
    @Override
    public void getUserInfo(String user_id, ResultCallback<User> resultCallback) {

    }

    @Override
    public void login(String login, String password, ResultCallback<Login> resultCallback) {

    }

    @Override
    public void getSchedule(String group_id, int type, ResultCallback<List<Schedule>> resultCallback) {

    }

    @Override
    public void getGradebook(String user_id, ResultCallback<Gradebook> resultCallback) {

    }

    @Override
    public void sendTask(String user_id, String group_id, String msg, FactCallback factCallback) {

    }

    @Override
    public void sendDoc(FactCallback factCallback) {

    }

    @Override
    public void getNews(ResultCallback<News> resultCallback) {

    }
}
