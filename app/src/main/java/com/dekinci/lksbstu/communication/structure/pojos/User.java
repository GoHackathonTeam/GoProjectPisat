package com.dekinci.lksbstu.communication.structure.pojos;

import com.dekinci.lksbstu.communication.structure.UserStatus;
import com.dekinci.lksbstu.utils.Utils;
import com.google.gson.annotations.SerializedName;

public class User {

    public enum Types {
        FULL_TIME,
        EXTRAMURAL,
        BOTH,
    }

    public static User simpleUser(UserStatus status) {
        User result = new User();
        result.name = Utils.capitalize(status.getStatus());
        result.surname = Utils.capitalize(status.getStatus());
        result.patronymic = Utils.capitalize(status.getStatus());

        result.status = status.getStatus();
        //TODO...
        return result;
    }

    public User() {

    }

    public User(String id, String groupName, String name, String surname, String patronymic,
                String status, String institute, String groupId,
                String education, Types type, String enrollmentDate,
                int course, int semester) {
        this.id = id;
        this.groupName = groupName;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.status = status;
        this.institute = institute;
        this.groupId = groupId;
        this.education = education;
        this.type = type;
        this.enrollmentDate = enrollmentDate;
        this.course = course;
        this.semester = semester;
    }

    @SerializedName("id")
    private String id;

    private String avatarUrl = "https://images-na.ssl-images-amazon.com/images/I/41q1QAln%2BQL.jpg";

    @SerializedName("groupName")
    private String groupName;

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
    private Types type;

    @SerializedName("enrollmentDate")
    private String enrollmentDate;

    @SerializedName("course")
    private int course;

    @SerializedName("semester")
    private int semester;

    public String getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getGroupName() {
        return groupName;
    }

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

    public Types getType() {
        return type;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public int getCourse() {
        return course;
    }

    public int getSemester() {
        return semester;
    }
}
