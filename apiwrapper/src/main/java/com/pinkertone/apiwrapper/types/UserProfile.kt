package com.pinkertone.apiwrapper.types

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserProfile (
    @SerializedName("user_data")
    @Expose
    var user: User? = null,

    @SerializedName("profile_data")
    @Expose
    var profile: Profile? = null,
)