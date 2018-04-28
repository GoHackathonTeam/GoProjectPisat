package com.dekinci.lksbstu.communication;

import com.dekinci.lksbstu.communication.structure.DaySchedule;
import com.dekinci.lksbstu.communication.structure.Gradebook;
import com.dekinci.lksbstu.communication.structure.Login;
import com.dekinci.lksbstu.communication.structure.News;
import com.dekinci.lksbstu.communication.structure.Schedule;
import com.dekinci.lksbstu.communication.structure.pojos.User;
import com.dekinci.lksbstu.utils.FactCallback;
import com.dekinci.lksbstu.utils.ResultCallback;

import java.util.List;

public interface PolyApi {
    void getUserInfo(String user_id, ResultCallback<User> resultCallback);
    void login(String login, String password, ResultCallback<Login> resultCallback);
    void getSchedule(String group_id, int type, ResultCallback<List<DaySchedule>> resultCallback);
    void getGradebook(String user_id, ResultCallback<Gradebook> resultCallback);
    void sendTask(String user_id, String group_id, String msg, FactCallback factCallback);
    void sendDoc(FactCallback factCallback);
    void getNews(ResultCallback<News> resultCallback);
}
