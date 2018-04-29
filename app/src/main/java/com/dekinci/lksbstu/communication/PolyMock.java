package com.dekinci.lksbstu.communication;

import android.util.Log;

import com.dekinci.lksbstu.PolyApp;
import com.dekinci.lksbstu.communication.factories.AnnouncementsFactory;
import com.dekinci.lksbstu.communication.server.DialogsServer;
import com.dekinci.lksbstu.communication.server.NewsServer;
import com.dekinci.lksbstu.communication.server.ScheduleServer;
import com.dekinci.lksbstu.communication.server.UserDataServer;
import com.dekinci.lksbstu.communication.structure.Announcement;
import com.dekinci.lksbstu.communication.structure.DaySchedule;
import com.dekinci.lksbstu.communication.structure.Gradebook;
import com.dekinci.lksbstu.communication.structure.Login;
import com.dekinci.lksbstu.communication.structure.Message;
import com.dekinci.lksbstu.communication.structure.News;
import com.dekinci.lksbstu.communication.structure.pojos.User;
import com.dekinci.lksbstu.utils.FactCallback;
import com.dekinci.lksbstu.utils.ResultCallback;

import java.util.ArrayList;
import java.util.List;

public class PolyMock implements PolyApi {
    private UserDataServer dataServer = new UserDataServer();
    private ScheduleServer scheduleServer = new ScheduleServer();
    private NewsServer newsServer = new NewsServer();
    private DialogsServer dialogsServer = new DialogsServer();

    private Login mLogin;

    public PolyMock() {
        mLogin = PolyApp.restoreCredentials();
    }

    @Override
    public boolean loggedIn() {
        return mLogin != null;
    }

    @Override
    public void login(String login, String password, FactCallback callback) {
        if (mLogin != null)
            callback.success();

        Login result = dataServer.login(login, password);
        if (result != null) {
            mLogin = result;
            PolyApp.persistCredentials(result);
            callback.success();
        }
        else
            callback.failure(new IllegalArgumentException("User with such login/password is not found"));
    }

    @Override
    public void getUserInfo(String user_id, ResultCallback<User> resultCallback) {
        User result = dataServer.getUser(user_id);
        if (result != null)
            resultCallback.success(result);
        else
            resultCallback.failure(new IllegalArgumentException("User not found"));
    }

    @Override
    public void getUserInfo(ResultCallback<User> resultCallback) {
        if (!loggedIn())
            resultCallback.failure(new IllegalStateException("Not logged in"));

        User result = dataServer.getUser(mLogin.getId());
        if (result != null)
            resultCallback.success(result);
        else
            resultCallback.failure(new IllegalStateException("User not found"));
    }

    @Override
    public void getSchedule(String date, String type, ResultCallback<List<DaySchedule>> resultCallback) {
        List<DaySchedule> list = new ArrayList<>();

        switch (type) {
            case "day":
                list.add(scheduleServer.getDaySchedule(date));
                break;
            case "week":
                list.addAll(scheduleServer.getWeekSchedule(date).getDaySchedules());
                break;
            case "month":
                list.addAll(scheduleServer.getMonthSchedule(date).getSchedules());
                break;
            default:
                Log.wtf("Exemple", "Wrong schedule type");
        }

        resultCallback.success(list);
    }

    @Override
    public void getGradebook(ResultCallback<List<Gradebook>> resultCallback) {
        List<Gradebook> gradebookList = new ArrayList<>();

        for (int i = 0; i < 7; i++)
            gradebookList.add(new Gradebook("Программирование", "Экзамен", "13-05-2018",
                    "Карамелькин Н.Н.", "зачет"));
        resultCallback.success(gradebookList);
    }

    @Override
    public void getNews(ResultCallback<List<News>> resultCallback, int from, int to) {
        List<News> news = new ArrayList<>();
        for (int i = from; i < to; i++)
            news.add(newsServer.forceNewsById(String.valueOf(i)));
        resultCallback.success(news);
    }

    @Override
    public void logOut() {
        mLogin = null;
        PolyApp.deleteCredentials();
    }

    @Override
    public void sendTask(String group_id, String msg, FactCallback factCallback) {

    }

    @Override
    public void getAnnouncements(ResultCallback<List<Announcement>> resultCallback, int from, int to) {
        AnnouncementsFactory announcementsFactory = new AnnouncementsFactory();
        List<Announcement> news = new ArrayList<>();
        for (int i = from; i < to; i++)
            news.add(announcementsFactory.generateNew());
        resultCallback.success(news);
    }

    @Override
    public void sendNotification(String other_user_id, String msg, FactCallback factCallback) {

    }

    @Override
    public void sendMessage(String other_user_id, String message, FactCallback factCallback) {

    }

    @Override
    public void getMessageList(String user_id, ResultCallback<List<Message>> resultCallback, int from, int to) {

    }

    @Override
    public void getDialogs(ResultCallback<List<String>> resultCallback) {
        try {
            resultCallback.success(dialogsServer.getDialogs(mLogin.getId()));
        } catch (Exception e) {
            resultCallback.failure(e);
        }
    }

    @Override
    public void sendMessageForGroup(String message, FactCallback factCallback) {

    }

    @Override
    public void getGroupMessage(ResultCallback<List<Message>> resultCallback, int from, int to) {

    }
}
