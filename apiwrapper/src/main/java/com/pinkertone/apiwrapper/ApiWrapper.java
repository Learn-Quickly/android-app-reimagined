package com.pinkertone.apiwrapper;

import com.pinkertone.apiwrapper.types.Token;
import com.pinkertone.apiwrapper.types.UserProfile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiWrapper {
    private ApiService apiServiceSingleton;
    private String authToken = "";
    private String baseUrl;
    private String language;

    public ApiWrapper(String baseUrl, String authToken, String language){
        this.baseUrl = baseUrl;
        this.authToken = authToken;
        this.language = language;
        this.apiServiceSingleton = ApiService.getInstance(baseUrl, authToken, language);
    }

    public ApiWrapper(String baseUrl, String language) {
        this.baseUrl = baseUrl;
        this.language = language;
        this.apiServiceSingleton = ApiService.getInstance(baseUrl, language);
    }

    public Token loginUser(String username, String password) {
        Call<Token> loginCall = apiServiceSingleton.apiService
                .loginUser(username, password);

        final Token[] token = new Token[1];
        loginCall.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                token[0] = response.body();
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                t.printStackTrace();
            }
        });
        authToken = token[0].token;
        apiServiceSingleton.setAuthToken(authToken);

        return token[0];
    }

    public Token createUser(String username, String email, String password) {
        Call<Token> createCall = apiServiceSingleton.apiService
                .createUser(username, email, password);

        final Token[] token = new Token[1];
        createCall.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                token[0] = response.body();
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                t.printStackTrace();
            }
        });

        authToken = token[0].token;
        apiServiceSingleton.setAuthToken(authToken);

        return token[0];
    }

    public UserProfile getUserInfo() {
        Call<UserProfile> userInfoCall = apiServiceSingleton.apiService
                .getUserInfo();

        final UserProfile[] userProfiles = new UserProfile[1];
        userInfoCall.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                userProfiles[0] = response.body();
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return userProfiles[0];

    }

    public boolean isAuthorized() {
        return !authToken.isEmpty();
    }
}