package com.dekinci.lksbstu.communication;

import com.dekinci.lksbstu.PolyApp;
import com.dekinci.lksbstu.communication.structure.DaySchedule;
import com.dekinci.lksbstu.communication.structure.Gradebook;
import com.dekinci.lksbstu.communication.structure.Login;
import com.dekinci.lksbstu.communication.structure.News;
import com.dekinci.lksbstu.communication.structure.Schedule;
import com.dekinci.lksbstu.communication.structure.UserStatus;
import com.dekinci.lksbstu.communication.structure.*;
import com.dekinci.lksbstu.communication.structure.pojos.User;
import com.dekinci.lksbstu.utils.FactCallback;
import com.dekinci.lksbstu.utils.ResultCallback;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PolyExemple implements PolyApi {
    private List<User> users;
    private List<News> news;

    public PolyExemple() {
        users = new ArrayList<>();
        users.add(new User("0", "Валерия", "Житникова", "Сахипзадовна",
                UserStatus.STUDENT.getStatus(), "ИКНТ", "43134",
                "bachelor", User.Types.FULL_TIME, "2007-12-03T10:15:30",
                3,1));
        users.add(new User("1", "Григорий", "Зубрин", "Владиславович",
                UserStatus.STUDENT.getStatus(), "ИКНТ", "43134",
                "bachelor", User.Types.FULL_TIME, "2007-12-03T10:15:30",
                3,1));
        users.add(new User("2", "Владимир", "Путин", "Владимирович",
                UserStatus.STUDENT.getStatus(), "ИКНТ", "43134",
                "bachelor", User.Types.FULL_TIME, "2007-12-03T10:15:30",
                3,1));
        users.add(new User("3", "Евгения", "Лососева", "Ашалайбовна",
                UserStatus.STUDENT.getStatus(), "ИКНТ", "43154",
                "bachelor", User.Types.FULL_TIME, "2007-12-03T10:15:30",
                3,1));

        users.add(new User("3", "Елизавета", "Арбузова", "Вольфовна",
                UserStatus.TEACHER.getStatus(), "ИКНТ", "teachers",
                "bachelor", User.Types.FULL_TIME, "2007-12-03T10:15:30",
                3,1));

        news = new ArrayList<>();
        news.add(new News("5", "Внимание!!!", "Очень важная информация", "2018.03.09 15:34"));
        news.add(new News("56", "Кое-что случилось", "Вы и сами наверное догадались, что <b>новости</b> это <i>новости</i>", "2017.12.01 12:01"));
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
    public void login(String login, String password, FactCallback callback) {
        String user_login = "a";
        String user_pass = "0";
        Login LOGIN = new Login("dsfwe12dcds", "00000001");

        if (login.equals(user_login) && password.equals(user_pass)){
            callback.success();
        }else
            callback.failure(new Exception());
    }

    @Override
    public void getSchedule(String group_id, String type, ResultCallback<List<DaySchedule>> resultCallback) {
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
                for (int i = 0; i < 7; i++){
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
                for (int i = 0; i < 30; i++){
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
    public void getGradebook(String user_id, ResultCallback<List<Gradebook>> resultCallback) {
        List<Gradebook> gradebookList = new ArrayList<>();

        for (int i = 0; i < 7; i++)
            gradebookList.add(new Gradebook("Программирование", "Экзамен","13-05-2018",
                 "Карамелькин Н.Н.","зачет"));
        resultCallback.success(gradebookList);
    }

    @Override
    public void sendTask(String user_id, String group_id, String msg, FactCallback factCallback) {

    }

    @Override
    public void sendDoc(FactCallback factCallback) {

    }

    @Override
    public void getNews(ResultCallback<List<News>> resultCallback, int from, int to) {
        if (from < 0 || to < 0)
            throw new IllegalArgumentException();
        if (to > news.size())
            to = news.size();
        if (from > news.size())
            resultCallback.success(new ArrayList<>());
        resultCallback.success(news.subList(from, to));
    }

    @Override
    public void logOut(Login login) {
        login = new Login();
        PolyApp.deleteCredentials();
    }
}
