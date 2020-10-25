package com.pinkertone.apiwrapper;

import com.google.gson.JsonObject;
import com.pinkertone.apiwrapper.types.Token;
import com.pinkertone.apiwrapper.types.UserProfile;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Header;

public interface IApiEndpoints {
    @FormUrlEncoded
    @POST("account/api/user/create")
    Call<Token> createUser(@Field("username") String username, @Field("email") String email,
                           @Field("password") String password);
    @FormUrlEncoded
    @POST("account/api/token/login")
    Call<Token> loginUser(@Field("username") String username, @Field("password") String password);

    @GET("account/api/user/me")
    Call<UserProfile> getUserInfo();

    @POST("account/api/user/me")
    Call<Token> changeUserInfo(@Body JsonObject userProfile);
}
