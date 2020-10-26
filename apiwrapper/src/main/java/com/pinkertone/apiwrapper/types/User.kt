package com.pinkertone.apiwrapper.types

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("username")
    @Expose
    var username: String? = null,

    @SerializedName("email")
    @Expose
    var email: String? = null,

    @SerializedName("first_name")
    @Expose
    var firstName: String? = null,

    @SerializedName("last_name")
    @Expose
    var lastName: String? = null,
)