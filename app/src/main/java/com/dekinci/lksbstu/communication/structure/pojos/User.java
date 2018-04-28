package com.dekinci.lksbstu.communication.structure.pojos;

import com.dekinci.lksbstu.communication.structure.UserStatus;
import com.dekinci.lksbstu.utils.Utils;
import com.google.gson.annotations.SerializedName;

public class User {
    public static User simpleUser(UserStatus status) {
        User result = new User();
        result.name = Utils.capitalize(status.getStatus());
        result.surname = Utils.capitalize(status.getStatus());
        result.patronymic = Utils.capitalize(status.getStatus());

        result.status = status.getStatus();
        //TODO...
        return result;
    }

    @SerializedName("name")
    private String name;

    @SerializedName("surname")
    private String surname;

    @SerializedName("patronymic")
    private String patronymic;

    @SerializedName("status")
    private String status;

    @SerializedName("institute")
    private String institute;

    @SerializedName("groupId")
    private String groupId;

    @SerializedName("education")
    private String education;

    @SerializedName("type")
    private String type;

    @SerializedName("enrollmentDate")
    private String enrollmentDate;

    @SerializedName("course")
    private String course;

    @SerializedName("semester")
    private String semester;


    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getStatus() {
        return status;
    }

    public String getInstitute() {
        return institute;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getEducation() {
        return education;
    }

    public String getType() {
        return type;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public String getCourse() {
        return course;
    }

    public String getSemester() {
        return semester;
    }
}
