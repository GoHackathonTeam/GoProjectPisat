package com.dekinci.lksbstu.communication.structure.pojos;

import com.google.gson.annotations.SerializedName;

public class Group {
    @SerializedName("id")
    private String id;

    @SerializedName("number")
    private String number;

    @SerializedName("departmentID")
    private String departmentID;

    public String getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getDepartmentID() {
        return departmentID;
    }
}
