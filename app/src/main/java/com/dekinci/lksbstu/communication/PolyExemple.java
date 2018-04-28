package com.dekinci.lksbstu.communication;

import com.dekinci.lksbstu.communication.structure.DaySchedule;
import com.dekinci.lksbstu.communication.structure.Gradebook;
import com.dekinci.lksbstu.communication.structure.Login;
import com.dekinci.lksbstu.communication.structure.News;
import com.dekinci.lksbstu.communication.structure.Schedule;
import com.dekinci.lksbstu.communication.structure.pojos.User;
import com.dekinci.lksbstu.utils.FactCallback;
import com.dekinci.lksbstu.utils.ResultCallback;

import java.util.ArrayList;
import java.util.List;

public class PolyExemple implements PolyApi {


    @Override
    public void getUserInfo(String user_id, ResultCallback<User> resultCallback) {
        resultCallback.success(new User());
    }

    @Override
    public void login(String login, String password, FactCallback callback) {
        String user_login = "tovpeko.k";
        String user_pass = "12345678";
        Login LOGIN = new Login();
        LOGIN.setID("00000001");
        LOGIN.setTOKEN("dsfwe12dcds");

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
    public void getNews(ResultCallback<News> resultCallback) {

    }
}
