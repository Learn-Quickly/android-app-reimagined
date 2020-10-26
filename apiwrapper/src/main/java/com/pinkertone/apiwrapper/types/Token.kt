package com.pinkertone.apiwrapper.types

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Token (
    @SerializedName("token")
    @Expose
    var string: String,
)