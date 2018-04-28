package com.dekinci.lksbstu.communication.structure.pojos;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("name")
    private String name;

    @SerializedName("surname")
    private String surname;

    @SerializedName("status")
    private String status;

    @SerializedName("patronymic")
    private String patronymic;

    @SerializedName("institute")
    private String institute;

    @SerializedName("groupId")
    String groupId;

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



}
