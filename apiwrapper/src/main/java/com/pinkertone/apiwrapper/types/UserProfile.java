package com.pinkertone.apiwrapper.types;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UserProfile {
    @SerializedName("user")
    @Expose
    public User user;

    @SerializedName("profile")
    @Expose
    public Profile profile;
}
