package com.pinkertone.apiwrapper;

import com.pinkertone.apiwrapper.types.Token;
import com.pinkertone.apiwrapper.types.User;
import com.pinkertone.apiwrapper.types.UserProfile;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ApiWrapper {
    private ApiService apiServiceSingleton;
    private String authToken = "";
    private String baseUrl;
    private String language;

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
        apiServiceSingleton.setAuthToken(authToken);
    }

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

    public void loginUser(final ICallback<Token> callback, String username, String password) {
        Call<Token> loginCall = apiServiceSingleton.apiService
                .loginUser(username, password);

        loginCall.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                callback.onSuccess(response.body());
                authToken = response.body().token;
                apiServiceSingleton.setAuthToken(authToken);
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                t.printStackTrace();
                callback.onFail(t);
            }
        });
    }

    public void createUser(final ICallback<Token> callback, String username, String email, String password) {
        Call<Token> createCall = apiServiceSingleton.apiService
                .createUser(username, email, password);

        createCall.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                callback.onSuccess(response.body());
                authToken = response.body().token;
                apiServiceSingleton.setAuthToken(authToken);
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                t.printStackTrace();
                callback.onFail(t);
            }
        });
    }

    public void getUserInfo(final ICallback<UserProfile> callback) {
        final Call<UserProfile> userInfoCall = apiServiceSingleton.apiService
                .getUserInfo();
        userInfoCall.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public boolean isAuthorized() {
        return !authToken.isEmpty() & !apiServiceSingleton.getAuthToken().isEmpty();
    }
}