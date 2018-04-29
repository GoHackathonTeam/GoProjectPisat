package com.dekinci.lksbstu.communication;

import android.util.Log;

import com.dekinci.lksbstu.PolyApp;
import com.dekinci.lksbstu.communication.factories.AnnouncementsFactory;
import com.dekinci.lksbstu.communication.factories.NewsFactory;
import com.dekinci.lksbstu.communication.factories.ScheduleFactory;
import com.dekinci.lksbstu.communication.factories.UserFactory;
import com.dekinci.lksbstu.communication.structure.Announcement;
import com.dekinci.lksbstu.communication.structure.DaySchedule;
import com.dekinci.lksbstu.communication.structure.Gradebook;
import com.dekinci.lksbstu.communication.structure.Login;
import com.dekinci.lksbstu.communication.structure.Message;
import com.dekinci.lksbstu.communication.structure.News;
import com.dekinci.lksbstu.communication.structure.Schedule;
import com.dekinci.lksbstu.communication.structure.UserStatus;
import com.dekinci.lksbstu.communication.structure.pojos.User;
import com.dekinci.lksbstu.utils.FactCallback;
import com.dekinci.lksbstu.utils.ResultCallback;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PolyExemple implements PolyApi {

    private List<User> users = new ArrayList<>();
    private Login mLogin;

    public PolyExemple() {
        mLogin = PolyApp.restoreCredentials();

        UserFactory userFactory = new UserFactory();
        for (int i = 0; i < 5; i++)
            users.add(userFactory.getStudent());
        users.add(userFactory.getPrepod());
        users.add(new User("0", "13531/4", "Пользователь", "Полезный", "Тестовый",
                UserStatus.STUDENT.getStatus(), "ИКНТ", "13531/4",
                "бакалавр", User.Types.FULL_TIME,
                "3 января 1970", 10, 10));
    }

    @Override
    public boolean isLoggedIn() {
        return mLogin != null;
    }

    @Override
    public void getUserInfo(String user_id, ResultCallback<User> resultCallback) {
        for (User user : users) {
            if (user_id.equals(user.getId())) {
                resultCallback.success(user);
            }
        }
        resultCallback.failure(new FileNotFoundException());
    }

    @Override
    public void getUserInfo(ResultCallback<User> resultCallback) {
        for (User user : users) {
            if (mLogin.getID().equals(user.getId())) {
                resultCallback.success(user);
            }
        }
        resultCallback.failure(new FileNotFoundException());
    }

    @Override
    public void login(String login, String password, FactCallback callback) {
        if (mLogin != null)
            callback.success();

        String user_login = "a";
        String user_pass = "0";
        mLogin = new Login("dsfwe12dcds", "00000001");

        if (login.equals(user_login) && password.equals(user_pass)) {
            PolyApp.persistCredentials(mLogin);
            callback.success();
        } else
            callback.failure(new Exception());
    }

    @Override
    public void getSchedule(String data, String type, ResultCallback<List<DaySchedule>> resultCallback) {
        ScheduleFactory factory = new ScheduleFactory();

        switch (type) {
            case "day":
                resultCallback.success(Collections.singletonList(factory.createDaySchedule()));
                break;
            case "week":
                resultCallback.success(factory.createWeekSchedule().getDaySchedules());
                break;
            case "month":
                resultCallback.success(factory.createMonthSchedule().getSchedules());
                break;
            default:
                Log.wtf("Exemple", "Wrong schedule type");
        }
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
        NewsFactory newsFactory = new NewsFactory();
        List<News> news = new ArrayList<>();
        for (int i = from; i < to; i++)
            news.add(newsFactory.generateNew());
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

    }

    @Override
    public void sendMessageForGroup(String message, FactCallback factCallback) {

    }

    @Override
    public void getGroupMessage(ResultCallback<List<Message>> resultCallback, int from, int to) {

    }
}
