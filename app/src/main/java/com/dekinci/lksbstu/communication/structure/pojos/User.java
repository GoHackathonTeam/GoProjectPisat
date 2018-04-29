package com.dekinci.lksbstu.communication.structure.pojos;

public class User {

    public enum Types {
        FULL_TIME,
        EXTRAMURAL,
        BOTH,
    }


    public User() {
    }

    public User(String id, String login,
                String name, String surname, String patronymic, String avatarUrl,
                String institute, String groupId, String groupName,
                String educationLvl, String educationType, String educationRole,
                Integer course, Integer semester, String enrollmentYear) {
        this.login = login;
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.avatarUrl = avatarUrl;
        this.institute = institute;
        this.groupId = groupId;
        this.groupName = groupName;
        this.educationLvl = educationLvl;
        this.educationType = educationType;
        this.educationRole = educationRole;
        this.course = course;
        this.semester = semester;
        this.enrollmentYear = enrollmentYear;
    }

    private String id;
    private String login;

    private String name;
    private String surname;
    private String patronymic;
    private String avatarUrl;

    private String institute;
    private String groupId;
    private String groupName;

    private String educationLvl;
    private String educationType;
    private String educationRole;

    private Integer course;
    private Integer semester;
    private String enrollmentYear;

    public String getLogin() {
        return login;
    }

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

    public String getEducationRole() {
        return educationRole;
    }

    public String getInstitute() {
        return institute;
    }

    public String getGroupId() {
        return groupId;
    }

    public int getCourse() {
        return course;
    }

    public int getSemester() {
        return semester;
    }

    public String getEducationLvl() {
        return educationLvl;
    }

    public String getEducationType() {
        return educationType;
    }

    public String getEnrollmentYear() {
        return enrollmentYear;
    }
}
