package com.dekinci.lksbstu.communication;

import com.dekinci.lksbstu.PolyApp;
import com.dekinci.lksbstu.communication.factories.AnnouncementsFactory;
import com.dekinci.lksbstu.communication.factories.NewsFactory;
import com.dekinci.lksbstu.communication.factories.UserFactory;
import com.dekinci.lksbstu.communication.structure.Announcement;
import com.dekinci.lksbstu.communication.structure.DaySchedule;
import com.dekinci.lksbstu.communication.structure.Gradebook;
import com.dekinci.lksbstu.communication.structure.Login;
import com.dekinci.lksbstu.communication.structure.Message;
import com.dekinci.lksbstu.communication.structure.News;
import com.dekinci.lksbstu.communication.structure.Schedule;
import com.dekinci.lksbstu.communication.structure.pojos.User;
import com.dekinci.lksbstu.utils.FactCallback;
import com.dekinci.lksbstu.utils.ResultCallback;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PolyExemple implements PolyApi {

    private List<User> users = new ArrayList<>();
    private Login mLogin;

    public PolyExemple() {
        UserFactory userFactory = new UserFactory();
        for (int i = 0; i < 5; i++)
            users.add(userFactory.getStudent());
        users.add(userFactory.getPrepod());
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
        ArrayList<DaySchedule> daySchedList = null;
        DaySchedule schedule;
        Schedule sched;
        switch (type) {
            case "day":
                schedule = new DaySchedule("28 мая 2018");
                sched = new Schedule("Практика",
                        "Программирование", "Глухих М.В.", "ГЗ, ауд 237");
                schedule.add(sched);
                sched = new Schedule("Практика",
                        "Программирование", "Глухих М.В.", "ГЗ, ауд 232");
                schedule.add(sched);
                sched = new Schedule("Практика",
                        "Программирование", "Глухих М.В.", "ГЗ, ауд 231");
                schedule.add(sched);
                daySchedList.add(schedule);

                resultCallback.success(daySchedList);
                break;
            case "week":
                for (int i = 0; i < 7; i++) {
                    schedule = new DaySchedule("28 мая 2018");
                    sched = new Schedule("Практика",
                            "Программирование", "Глухих М.В.", "ГЗ, ауд 237");
                    schedule.add(sched);
                    sched = new Schedule("Лабораторная",
                            "История", "Лебницин М.В.", "ГЗ, ауд 234");
                    schedule.add(sched);
                    sched = new Schedule("Лекция",
                            "Математика", "Коровин Л.Л.", "ГЗ, ауд 233");
                    schedule.add(sched);
                    daySchedList.add(schedule);
                }
                resultCallback.success(daySchedList);
                break;
            case "month":
                for (int i = 0; i < 30; i++) {
                    schedule = new DaySchedule("28 мая 2018");
                    sched = new Schedule("Практика",
                            "Программирование", "Глухих М.В.", "ГЗ, ауд 237");
                    schedule.add(sched);
                    sched = new Schedule("Лабораторная",
                            "История", "Лебницин М.В.", "ГЗ, ауд 234");
                    schedule.add(sched);
                    sched = new Schedule("Лекция",
                            "Математика", "Коровин Л.Л.", "ГЗ, ауд 233");
                    schedule.add(sched);
                    daySchedList.add(schedule);
                }
                resultCallback.success(daySchedList);
                break;
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
