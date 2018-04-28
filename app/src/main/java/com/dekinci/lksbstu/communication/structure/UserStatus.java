package com.dekinci.lksbstu.communication.structure;

public enum UserStatus {
    STUDENT("student"),
    TEACHER("teacher"),
    DIRECTOR("director");

    private String status;

    public static UserStatus getUserStatus(String strStatus) {
        UserStatus[] statuses = UserStatus.values();
        for (UserStatus status : statuses)
            if (status.getStatus().equals(strStatus))
                return status;
        throw new IllegalStateException("Wrong user status string");
    }

    UserStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
