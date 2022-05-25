package com.geektech.lovecalculator.network;

import com.google.gson.annotations.SerializedName;

public class LoveModel {

    @SerializedName("fname")
    private String firstName;

    @SerializedName("sname")
    private String secondName;
    private String percentage;
    private String result;

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getPercentage() {
        return percentage;
    }

    public String getResult() {
        return result;
    }
}
