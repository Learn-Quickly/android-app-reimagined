package com.pinkertone.apiwrapper.types

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Profile(
    @SerializedName("uuid")
    @Expose
    var uuid: String? = null,

    @SerializedName("school")
    @Expose
    var school: String? = null,

    @SerializedName("city")
    @Expose
    var city: String? = null,

    @SerializedName("form_letter")
    @Expose
    var formLetter: String? = null,

    @SerializedName("form_number")
    @Expose
    var formNumber: String? = null,
)