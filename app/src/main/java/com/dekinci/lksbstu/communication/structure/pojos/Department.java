package com.dekinci.lksbstu.communication.structure.pojos;

import com.google.gson.annotations.SerializedName;

public class Department {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("abbreviation")
    private String abbreviation;

    @SerializedName("instituteID")
    private String instituteID;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getInstituteID() {
        return instituteID;
    }
}
