package com.pinkertone.apiwrapper;

import com.pinkertone.apiwrapper.types.Token;
import com.pinkertone.apiwrapper.types.UserProfile;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class ApiServiceTest {

    @Test
    public void createWithoutAndSettingTokenTest() throws IOException {
        ApiService apiServiceSingleton = new ApiService(Constants.BASE_URL, "uk");

        // Creating call for login
        Call<Token> loginCall = apiServiceSingleton.apiService
                .loginUser(Constants.TEST_USERNAME, Constants.TEST_PASSWORD);

        Response<Token> loginResponse = loginCall.execute();
        Token token = loginResponse.body();
        assertNotNull(token);
        assertEquals(loginResponse.code(), 200);

        // Setting token
        apiServiceSingleton.setAuthToken(token.token);

        // Creating call for getting user data
        Call<UserProfile> secondCall = apiServiceSingleton.apiService
                .getUserInfo();  // This endpoint was chosen just if auth interceptor works correctly

        Response<UserProfile> secondResponse = secondCall.execute();
        UserProfile userProfile = secondResponse.body();
        assertNotNull(userProfile);
        assertEquals(secondResponse.code(), 200);
    }
}
