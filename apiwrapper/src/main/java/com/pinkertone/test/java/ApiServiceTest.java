package com.pinkertone.test.java;

import com.pinkertone.apiwrapper.ApiService;
import com.pinkertone.apiwrapper.types.Token;
import com.pinkertone.apiwrapper.types.UserProfile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;


public class ApiServiceTest {
    private final String BASE_URL = "http://lqrl.tk/";

    @Test
    public void createWithoutAndSettingTokenTest() throws IOException {

        ApiService apiServiceSingleton = ApiService.getInstance(BASE_URL, "uk");

        Call<Token> loginCall = apiServiceSingleton.apiService  // creating call for login
                .loginUser("let45fc", "let45fclet45fc");

        Response<Token> loginResponse = loginCall.execute();
        Token token = loginResponse.body();
        assertNotNull(token);
        assertEquals(loginResponse.code(), 200);

        apiServiceSingleton.setAuthToken(token.token);  // setting token

        Call<UserProfile> secondCall = apiServiceSingleton.apiService  // creating call for getting user data
                .getUserInfo();  // This endpoint was chosen just if auth interceptor works correctly

        Response<UserProfile> secondResponse = secondCall.execute();
        UserProfile userProfile = secondResponse.body();
        assertNotNull(userProfile);
        assertEquals(secondResponse.code(), 200);
    }
}
