package com.pinkertone.apiwrapper.types;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UserProfile {
    @SerializedName("user_data")
    @Expose
    public User user;

    @SerializedName("profile_data")
    @Expose
    public Profile profile;
}
