package com.pinkertone.apiwrapper.types;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Token {
    @SerializedName("token")
    @Expose
    public String token;
}
