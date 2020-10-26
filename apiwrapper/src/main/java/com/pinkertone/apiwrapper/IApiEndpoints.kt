package com.pinkertone.apiwrapper

import com.google.gson.JsonObject
import com.pinkertone.apiwrapper.types.Token
import com.pinkertone.apiwrapper.types.UserProfile
import retrofit2.http.*

interface IApiEndpoints {
    @FormUrlEncoded
    @POST("account/api/user/create")
    suspend fun createUser(
        @Field("username") username: String, @Field("email") email: String,
        @Field("password") password: String
    ): Token

    @FormUrlEncoded
    @POST("account/api/token/login")
    suspend fun loginUser(
        @Field("username") username: String,
        @Field("password") password: String
    ): Token

    @GET("account/api/user/me")
    suspend fun getUserInfo(): UserProfile

    @POST("account/api/user/me")
    suspend fun changeUserInfo(@Body userProfile: JsonObject): Token
}