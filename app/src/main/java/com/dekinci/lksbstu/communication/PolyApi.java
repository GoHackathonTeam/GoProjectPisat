package com.dekinci.lksbstu.communication;

import com.dekinci.lksbstu.communication.structure.Announcement;
import com.dekinci.lksbstu.communication.structure.DaySchedule;
import com.dekinci.lksbstu.communication.structure.Gradebook;
import com.dekinci.lksbstu.communication.structure.Login;
import com.dekinci.lksbstu.communication.structure.Message;
import com.dekinci.lksbstu.communication.structure.News;
import com.dekinci.lksbstu.communication.structure.pojos.User;
import com.dekinci.lksbstu.utils.FactCallback;
import com.dekinci.lksbstu.utils.ResultCallback;

import java.util.List;

public interface PolyApi {
    void getUserInfo(String user_id, ResultCallback<User> resultCallback);
    void getUserInfo(ResultCallback<User> resultCallback);
    void login(String login, String password, FactCallback callback);
    void getSchedule(String data, String type, ResultCallback<List<DaySchedule>> resultCallback);
    void getGradebook(ResultCallback<List<Gradebook>> resultCallback);
    void getNews(ResultCallback<List<News>> resultCallback, int from, int to);
    void logOut();

    void sendTask(String group_id, String msg, FactCallback factCallback);
//    void getTack()

    void getAnnouncements(ResultCallback<List<Announcement>> resultCallback, int from, int to);
    void sendNotification(String other_user_id, String msg, FactCallback factCallback);

    void sendMessage(String other_user_id, String message, FactCallback factCallback);
    void getMessageList(String user_id, ResultCallback<List<Message>> resultCallback, int from, int to);
    void getDialogs(ResultCallback<List<String>> resultCallback);

    void sendMessageForGroup(String message, FactCallback factCallback);
    void getGroupMessage(ResultCallback<List<Message>> resultCallback, int from, int to);


}
