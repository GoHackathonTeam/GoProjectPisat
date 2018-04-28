package com.dekinci.lksbstu.communication;

import com.dekinci.lksbstu.utils.FactCallback;
import com.dekinci.lksbstu.utils.ResultCallback;

public interface PolyApi {
    void getUserinfo(int user_id, ResultCallback resultCallback);
    void login(String login, String password, ResultCallback resultCallback);
    void getSchedule(int group_id, ResultCallback resultCallback);
    void sendTask(int user_id, int group_id, String msg, FactCallback factCallback);
    void sendDoc(FactCallback factCallback);
    void getNews(ResultCallback resultCallback);
}
