package com.dekinci.lksbstu.communication;

import com.dekinci.lksbstu.utils.FactCallback;
import com.dekinci.lksbstu.utils.ResultCallback;

public interface PolyApi {
    void getUserInfo(String user_id, ResultCallback resultCallback);
    void login(String login, String password, ResultCallback resultCallback);
    void getSchedule(String group_id, int type, ResultCallback resultCallback);
    void getGradebook(String user_id, ResultCallback resultCallback);
    void sendTask(String user_id, String group_id, String msg, FactCallback factCallback);
    void sendDoc(FactCallback factCallback);
    void getNews(ResultCallback resultCallback);
}
