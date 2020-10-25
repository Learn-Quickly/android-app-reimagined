package com.pinkertone.apiwrapper.types;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Profile {
    @SerializedName("uuid")
    @Expose
    public String uuid;

    @SerializedName("school")
    @Expose
    public String school;

    @SerializedName("city")
    @Expose
    public String city;

    @SerializedName("form_letter")
    @Expose
    public String formLetter;

    @SerializedName("form_number")
    @Expose
    public String formNumber;
}
