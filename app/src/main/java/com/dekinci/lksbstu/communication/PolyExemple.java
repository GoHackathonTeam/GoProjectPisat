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
    public void login(String login, String password, ResultCallback<Login> resultCallback) {
        String user_login = "tovpeko.k";
        String user_pass = "12345678";
        Login LOGIN = new Login();
        LOGIN.setID("00000001");
        LOGIN.setTOKEN("dsfwe12dcds");

        if (login.equals(user_login) && password.equals(user_pass)){
            resultCallback.success(LOGIN);
        }else
            resultCallback.failure(new Exception());
    }

    @Override
    public void getSchedule(String group_id, int type, ResultCallback<List<DaySchedule>> resultCallback) {
        List<Schedule> schedule = new ArrayList<Schedule>();
        Schedule sched;
        switch (type) {
            case 1:
                sched = new Schedule("28 апреля 2018", "Практика",
                        "Программирование", "Глухих М.В.", "ГЗ, ауд 237");
                schedule.add(sched);
                resultCallback.success(schedule);
                break;
            case 2:
                for (int i = 0; i < 7; i++){
                    sched = new Schedule(i + " апреля 2018", "Практика",
                            "Программирование", "Глухих М.В.", "ГЗ, ауд 237");

                }
        }
    }

    @Override
    public void getGradebook(String user_id, ResultCallback<Gradebook> resultCallback) {

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
