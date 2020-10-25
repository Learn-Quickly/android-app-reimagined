package com.pinkertone.apiwrapper;

import androidx.annotation.NonNull;

import com.pinkertone.apiwrapper.types.Token;
import com.pinkertone.apiwrapper.types.UserProfile;

import org.junit.runners.model.InitializationError;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Singleton for client to make requests
public class ApiWrapper {
    private static ApiWrapper instance;
    private final ApiService apiService;
    private String authToken = "";
    private final String language;

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
        apiService.setAuthToken(authToken);
    }

    private ApiWrapper(String baseUrl, String authToken, String language) {
        this(baseUrl, language);
        this.authToken = authToken;
    }

    private ApiWrapper(String baseUrl, String language) {
        this.language = language;
        this.apiService = new ApiService(baseUrl, language);
    }

    public void loginUser(final ICallback<Token> callback, String username, String password) {
        Call<Token> loginCall = apiService.apiService
                .loginUser(username, password);

        loginCall.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(@NonNull Call<Token> call, @NonNull Response<Token> response) {
                callback.onSuccess(response.body());
                authToken = response.body().token;
                apiService.setAuthToken(authToken);
            }

            @Override
            public void onFailure(@NonNull Call<Token> call, @NonNull Throwable t) {
                t.printStackTrace();
                callback.onFail(t);
            }
        });
    }

    public void createUser(final ICallback<Token> callback, String username, String email, String password) {
        Call<Token> createCall = apiService.apiService
                .createUser(username, email, password);

        createCall.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(@NonNull Call<Token> call, @NonNull Response<Token> response) {
                callback.onSuccess(response.body());
                authToken = response.body().token;
                apiService.setAuthToken(authToken);
            }

            @Override
            public void onFailure(@NonNull Call<Token> call, @NonNull Throwable t) {
                t.printStackTrace();
                callback.onFail(t);
            }
        });
    }

    public void getUserInfo(final ICallback<UserProfile> callback) {
        final Call<UserProfile> userInfoCall = apiService.apiService
                .getUserInfo();
        userInfoCall.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(@NonNull Call<UserProfile> call, @NonNull Response<UserProfile> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure (@NonNull Call<UserProfile> call, @NonNull Throwable t) {
                t.printStackTrace();
                callback.onFail(t);
            }
        });

    }

    public static synchronized ApiWrapper getInstance(String baseUrl, String authToken, String language) {
        if (instance == null) {
            instance = new ApiWrapper(baseUrl, authToken, language);
        }
        return instance;
    }

    public static synchronized ApiWrapper getInstance(String baseUrl, String language) {
        if (instance == null) {
            instance = new ApiWrapper(baseUrl, language);
        }
        return instance;
    }

    public static synchronized ApiWrapper getInstance() throws InitializationError {
        if (instance == null) {
            throw new InitializationError("ApiService was not initialized before");
        }
        return instance;
    }

    public boolean isAuthorized() {
        return !authToken.isEmpty() & !apiService.getAuthToken().isEmpty();
    }
}