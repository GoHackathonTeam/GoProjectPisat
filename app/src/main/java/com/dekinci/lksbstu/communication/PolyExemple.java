package com.dekinci.lksbstu.communication;

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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PolyExemple implements PolyApi {

    private List<User> users;
    private List<News> news;
    private Login LOGIN;
    private String groupId;
    private File dialogs;
    private File dialogGroup;
    private File notificationFile;

    private List<Message> messages;
    private List<Message> chat;
    private List<String> notifications;

    public PolyExemple() {
        users = new ArrayList<>();
        users.add(new User("00000001", "13531/1", "Кирилл", "Товпеко", "Александрович",
                UserStatus.STUDENT.getStatus(), "ИКНТ", "43134",
                "bachelor", User.Types.FULL_TIME, "2007-12-03T10:15:30",
                3, 1));
        users.add(new User("1", "13531/1", "Григорий", "Зубрин", "Владиславович",
                UserStatus.STUDENT.getStatus(), "ИКНТ", "43134",
                "bachelor", User.Types.FULL_TIME, "2007-12-03T10:15:30",
                3, 1));
        users.add(new User("2", "13531/1", "Владимир", "Путин", "Владимирович",
                UserStatus.STUDENT.getStatus(), "ИКНТ", "43134",
                "bachelor", User.Types.FULL_TIME, "2007-12-03T10:15:30",
                3, 1));
        users.add(new User("3", "13531/9", "Евгения", "Лососева", "Ашалайбовна",
                UserStatus.STUDENT.getStatus(), "ИКНТ", "43154",
                "bachelor", User.Types.FULL_TIME, "2007-12-03T10:15:30",
                3, 1));

        users.add(new User("548", "Teachers", "Елизавета", "Арбузова", "Вольфовна",
                UserStatus.TEACHER.getStatus(), "ИКНТ", "teachers",
                "bachelor", User.Types.FULL_TIME, "2007-12-03T10:15:30",
                3, 1));

        news = new ArrayList<>();
        news.add(new News("5", "Внимание!!!", "Очень важная информация", "2018.03.09 15:34"));
        news.add(new News("56", "Кое-что случилось", "Вы и сами наверное догадались, что <b>новости</b> это <i>новости</i>", "2017.12.01 12:01"));

        File innerDir = PolyApp.getInnerDir();
        dialogs = new File(innerDir, "dialogs");
        getUserInfo(user -> dialogGroup = new File(innerDir, groupId = user.getGroupId()));
        notificationFile = new File(innerDir, LOGIN.getID());
        messages = new ArrayList<>();
        chat = new ArrayList<>();

        try {
            if (dialogs.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(dialogs));
                String sender;
                while ((sender = reader.readLine()) != null) {
                    String receiver = reader.readLine();
                    String text = reader.readLine();
                    messages.add(new Message(sender, receiver, text));
                }
                reverse(messages);
            }
            if (dialogGroup.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(dialogGroup));
                String sender;
                while ((sender = reader.readLine()) != null) {
                    String text = reader.readLine();
                    chat.add(new Message(sender, groupId, text));
                }
                reverse(chat);
            }
            if (notificationFile.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(notificationFile));
                String notification;
                while ((notification = reader.readLine()) != null) {
                    notifications.add(notification);
                }
                reverse(notifications);
            }
        } catch (Exception e) { }
    }

    private <T> void reverse(List<T> list) {
        int offset = list.size() - 1;
        int half = list.size() / 2;
        for (int i = 0; i < half; i++) {
            T message = list.get(i);
            list.set(i, list.get(offset - i));
            list.set(offset - i, message);
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

        if (login.equals(user_login) && password.equals(user_pass)) {
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

//    @Override
//    public void sendTask(String user_id, String group_id, String msg, FactCallback factCallback) {
//
//    }


    @Override
    public void getNews(ResultCallback<List<News>> resultCallback, int from, int to) {
        try {
            if (from < 0 || to < 0 || from > to)
                throw new IllegalArgumentException();
            int size = news.size() - 1;
            if (to > size)
                to = size;
            if (from > to) {
                resultCallback.success(new ArrayList<>());
                return;
            }
            resultCallback.success(news.subList(from, to));
        } catch (Exception e) {
            resultCallback.failure(e);
        }
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
    public void getNotification(ResultCallback<List<String>> resultCallback, int from, int to) {
        try {
            if (from < 0 || to < 0 || from > to)
                throw new IllegalArgumentException();
            int size = notifications.size() - 1;
            if (to > size)
                to = size;
            if (from > to) {
                resultCallback.success(new ArrayList<>());
                return;
            }
            resultCallback.success(notifications.subList(from, to));
        } catch (Exception e) {
            resultCallback.failure(e);
        }
    }

    @Override
    public void sendNotification(String other_user_id, String msg, FactCallback factCallback) {
        try {
            Writer writer = new FileWriter(new File(PolyApp.getInnerDir(), other_user_id));
            writer.write(msg);
            writer.write('\n');
            writer.close();
            factCallback.success();
        } catch (Exception e) {
            factCallback.failure(e);
        }
    }

    @Override
    public void sendMessage(String other_user_id, String message, FactCallback factCallback) {
        messages.add(0, new Message(LOGIN.getID(), other_user_id, message));
        try {
            FileWriter writer = new FileWriter(dialogs, true);
            writer.write(LOGIN.getID());
            writer.write('\n');
            writer.write(other_user_id);
            writer.write('\n');
            writer.write(message);
            writer.write('\n');
            writer.close();
            factCallback.success();
        } catch (Exception e) {
            factCallback.failure(e);
        }
    }

    @Override
    public void getMessageList(String user_id, ResultCallback<List<Message>> resultCallback, int from, int to) {
        List<Message> result = new ArrayList<>();
        int size = messages.size();
        int i;
        int j;
        for (i = 0, j = 0; i < from && j < size; j++) {
            Message message = messages.get(j);
            if (message.getSenderId().equals(LOGIN.getID()) && message.getReceiverId().equals(user_id)) {
                i++;
            }
            for (; i < to && j < size; j++) {
                message = messages.get(j);
                if (message.getSenderId().equals(LOGIN.getID()) && message.getReceiverId().equals(user_id)
                        || message.getSenderId().equals(user_id) && message.getReceiverId().equals(LOGIN.getID())) {
                    result.add(message);
                    i++;
                }
            }
            resultCallback.success(result);
        }
    }


    @Override
    public void getDialogs(ResultCallback<List<String>> resultCallback) {
        Set<String> result = new HashSet<>();
        for (Message message : messages) {
            if (message.getReceiverId().equals(LOGIN.getID())) {
                result.add(message.getSenderId());
            }
            if (message.getSenderId().equals(LOGIN.getID())) {
                result.add(message.getReceiverId());
            }
            try {
            } catch (Exception e) {
                resultCallback.failure(e);
            }
            resultCallback.success(new ArrayList<>(result));
        }
    }

    @Override
    public void sendMessageForGroup(String message, FactCallback factCallback) {
        chat.add(0, new Message(LOGIN.getID(), groupId, message));
        try {
            FileWriter writer = new FileWriter(dialogGroup);
            writer.write(LOGIN.getID());
            writer.write('\n');
            writer.write(message);
            writer.write('\n');
            writer.close();
            factCallback.success();
        } catch (Exception e) {
            factCallback.failure(e);
        }
    }

    @Override
    public void getGroupMessage(ResultCallback<List<Message>> resultCallback, int from, int to) {
        try {
            if (from < 0 || to < 0 || from > to)
                throw new IllegalArgumentException();
            int size = chat.size() - 1;
            if (to > size)
                to = size;
            if (from > to) {
                resultCallback.success(new ArrayList<>());
                return;
            }
            resultCallback.success(chat.subList(from, to));
        } catch (Exception e) {
            resultCallback.failure(e);
        }
    }
}
