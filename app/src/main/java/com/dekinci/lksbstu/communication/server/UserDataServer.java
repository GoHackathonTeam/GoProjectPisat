package com.dekinci.lksbstu.communication.server;

import com.dekinci.lksbstu.communication.structure.Login;
import com.dekinci.lksbstu.communication.structure.pojos.User;

import java.util.HashMap;
import java.util.Map;

public class UserDataServer {
    private static final Map<String, UserData> users = new HashMap<>();

    static {
        users.put("01", new UserData(
                "login", String.valueOf("pass".hashCode()),
                "Name", "Surname", "Patron",
                "Inst", "1", "13531",
                "bach", "full", "stud",
                1, 1, "2017"));

        users.put("02", new UserData(
                "a", String.valueOf("0000".hashCode()),
                "Name", "Surname", "Patron",
                "Inst", "1", "13531",
                "bach", "full", "stud",
                1, 1, "2017"));

        users.put("03", new UserData(
                "b", String.valueOf("0000".hashCode()),
                "Name", "Surname", "Patron",
                "Inst", "1", "13531",
                "bach", "full", "stud",
                1, 1, "2017"));

        users.put("04", new UserData(
                "c", String.valueOf("0000".hashCode()),
                "Name", "Surname", "Patron",
                "Inst", "1", "13531",
                "bach", "full", "stud",
                1, 1, "2017"));

        users.put("05", new UserData(
                "d", String.valueOf("0000".hashCode()),
                "Name", "Surname", "Patron",
                "Inst", "1", "13531",
                "bach", "full", "stud",
                1, 1, "2017"));

        users.put("06", new UserData(
                "e", String.valueOf("0000".hashCode()),
                "Name", "Surname", "Patron",
                "Inst", "1", "13531",
                "bach", "full", "stud",
                1, 1, "2017"));

        users.put("07", new UserData(
                "f", String.valueOf("0000".hashCode()),
                "Name", "Surname", "Patron",
                "Inst", "1", "13531",
                "bach", "full", "stud",
                1, 1, "2017"));

        users.put("08", new UserData(
                "g", String.valueOf("0000".hashCode()),
                "Name", "Surname", "Patron",
                "Inst", "1", "13531",
                "bach", "full", "stud",
                1, 1, "2017"));

        users.put("09", new UserData(
                "h", String.valueOf("0000".hashCode()),
                "Name", "Surname", "Patron",
                "Inst", "1", "13531",
                "bach", "full", "stud",
                1, 1, "2017"));

        users.put("10", new UserData(
                "i", String.valueOf("0000".hashCode()),
                "Name", "Surname", "Patron",
                "Inst", "1", "13531",
                "bach", "full", "stud",
                1, 1, "2017"));
    }

    public User getUser(String id) {
        UserData data = users.get(id);
        if (data != null) {
            return new User(id, data.getName(), data.getSurname(), data.getPatronym(), data.getAvatarUrl(),
                    data.getInstitute(), data.getGroupId(), data.getGroupName(),
                    data.getEducationLvl(), data.getEducationType(), data.getEducationRole(),
                    data.getCourse(), data.getSemester(), data.getEnrollmentYear());
        }
        return null;
    }

    public Login login(String login, String password) {
        String ph = String.valueOf(password.hashCode());
        for (Map.Entry<String, UserData> entry : users.entrySet()) {
            UserData data = entry.getValue();
            if (data.getLogin().equals(login) && data.getPassHash().equals(ph))
                return new Login("x", entry.getKey());
        }

        return null;
    }
}
