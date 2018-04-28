package com.dekinci.lksbstu.communication;

import android.content.Context;

import com.dekinci.lksbstu.PolyApp;
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PolyExemple implements PolyApi {

    private List<User> users;
    private List<News> news;
    private Login LOGIN;
    private File dialogs;
    private String dialogGroup = "txt/dialogGroup.txt";
    private Context context;

    public PolyExemple(Context context) {
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

        this.context = context;
        dialogs = new File(context.getDataDir(), "dialogs");
        if (!dialogs.exists()){
            try {
                dialogs.createNewFile();
            } catch (Exception e) { }
        }
    }

    @Override
    public void getUserInfo(ResultCallback<User> resultCallback) {
        for (User user : users) {
            if (LOGIN.getID().equals(user.getId())) {
                resultCallback.success(user);
            }
        }
        resultCallback.failure(new FileNotFoundException());
    }

    @Override
    public void login(String login, String password, FactCallback callback) {
        String user_login = "a";
        String user_pass = "0";
        LOGIN = new Login("dsfwe12dcds", "00000001");

        if (login.equals(user_login) && password.equals(user_pass)){
            callback.success();
        }else
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
    public void getGradebook(ResultCallback<List<Gradebook>> resultCallback) {
        List<Gradebook> gradebookList = new ArrayList<>();

        for (int i = 0; i < 7; i++)
            gradebookList.add(new Gradebook("Программирование", "Экзамен","13-05-2018",
                 "Карамелькин Н.Н.","зачет"));
        resultCallback.success(gradebookList);
    }

//    @Override
//    public void sendTask(String user_id, String group_id, String msg, FactCallback factCallback) {
//
//    }


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
    public void logOut() {
        LOGIN = null;
        PolyApp.deleteCredentials();
    }

    @Override
    public void sendTask(String group_id, String msg, FactCallback factCallback) {

    }

    @Override
    public void getNotification(ResultCallback<List<Message>> resultCallback, int from, int to) {

    }

    @Override
    public void sendNotification(Message msg, FactCallback factCallback) {

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
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(dialogs)));

        } catch (Exception e) {

        }
    }


    @Override
    public void sendMessageForGroup(String message, FactCallback factCallback) {

    }

    @Override
    public void getGroupMessage(ResultCallback<List<Message>> resultCallback, int from, int to) {
        try {
            Scanner scanner = new Scanner(dialogGroup);
            List<Message> messages = new ArrayList<>();
            while (scanner.hasNext()){
                messages.add(new Message(scanner.next(), null, scanner.next()));
            }
            resultCallback.success(messages);

        } catch (Exception e) {

        }
    }


}
