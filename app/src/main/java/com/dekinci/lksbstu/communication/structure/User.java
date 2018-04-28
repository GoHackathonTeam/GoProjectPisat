package com.dekinci.lksbstu.communication.structure;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("name")
    private String name;

    @SerializedName("institute")
    private String institute;

    @SerializedName("level")
    private String level;

    @SerializedName("orientation")
    private String orientation;

    @SerializedName("form")
    private String form;

    @SerializedName("startStudy")
    private String startStudy;

    @SerializedName("course")
    private String course;

    @SerializedName("semester")
    private String semester;

    @SerializedName("groupName")
    private String groupName;

    @SerializedName("group_id")
    private String group_id;


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
