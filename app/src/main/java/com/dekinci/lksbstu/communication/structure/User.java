package com.dekinci.lksbstu.communication.structure;

public class User {
    String name;
    String institute;
    String level;
    String orientation;
    String form;
    String startStudy;
    String course;
    String semester;
    String groupName;
    String group_id;

    public User(String name, String institute, String level, String orientation, String form,
                String startStudy, String course, String semester, String groupName,String group_id) {
        this.name = name;
        this.institute = institute;
        this.level = level;
        this.orientation = orientation;
        this.form = form;
        this.startStudy = startStudy;
        this.course = course;
        this.semester = semester;
        this.group_id = group_id;
        this.groupName = groupName;
    }

    public String getName() {
        return name;
    }

    public String getInstitute() {
        return institute;
    }

    public String getLevel() {
        return level;
    }

    public String getOrientation() {
        return orientation;
    }

    public String getForm() {
        return form;
    }

    public String getStartStudy() {
        return startStudy;
    }

    public String getCourse() {
        return course;
    }

    public String getSemester() {
        return semester;
    }

    public String getGroup_id() {
        return group_id;
    }

    public String getGroupName() {
        return groupName;
    }
}
