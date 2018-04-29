package com.dekinci.lksbstu.communication.server;

public class UserData {
    private String login;
    private String passHash;

    private String name;
    private String surname;
    private String patronym;
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


    public UserData(String login, String passHash,
                    String name, String surname, String patronym,
                    String institute, String groupId, String groupName,
                    String educationLvl, String educationType, String educationRole,
                    Integer course, Integer semester, String enrollmentYear) {
        this.login = login;
        this.passHash = passHash;
        this.name = name;
        this.surname = surname;
        this.patronym = patronym;
        this.avatarUrl = "https://image.freepik.com/free-icon/no-translate-detected_318-34042.jpg";
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

    public UserData() {
    }

    public String getLogin() {
        return login;
    }

    public String getPassHash() {
        return passHash;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronym() {
        return patronym;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getInstitute() {
        return institute;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getEducationLvl() {
        return educationLvl;
    }

    public String getEducationType() {
        return educationType;
    }

    public String getEducationRole() {
        return educationRole;
    }

    public Integer getCourse() {
        return course;
    }

    public Integer getSemester() {
        return semester;
    }

    public String getEnrollmentYear() {
        return enrollmentYear;
    }
}
