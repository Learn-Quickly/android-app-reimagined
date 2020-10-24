package com.pinkertone.apiwrapper;


import org.junit.runners.model.InitializationError;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import okhttp3.OkHttpClient;

import java.rmi.NotBoundException;

public class ApiService {  // Singleton for making requests to API
    private static ApiService instance;
    private String baseUrl;
    private String authToken = "";
    private String language;

    public IApiEndpoints apiService;

    public ApiService(String baseUrl, String authToken, String language){
        this.baseUrl = baseUrl;
        this.authToken = authToken;
        this.language = language;
        initApiService();
    }

    public ApiService(String baseUrl, String language) {
        this.baseUrl = baseUrl;
        this.language = language;
        initApiService();
    }

    public ApiService() throws NotBoundException {
        if (baseUrl.isEmpty()) {
            throw new NotBoundException("baseUrl parameter was not set");
        }
        if (authToken.isEmpty()) {
            throw new NotBoundException("authtoken parameter was not set");
        }
        if (language.isEmpty()) {
            throw new NotBoundException("language parameter was not set");
        }
    }

    private void initApiService(){
        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(new HeadersInterceptor(authToken, language)).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();

        apiService = retrofit.create(IApiEndpoints.class);
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
        initApiService();
    }

    public static synchronized ApiService getInstance(String baseUrl, String authToken, String language) {
        if (instance == null) {
            instance = new ApiService(baseUrl, authToken, language);
        }
        return instance;
    }

    public static synchronized ApiService getInstance(String baseUrl, String language) {
        if (instance == null) {
            instance = new ApiService(baseUrl, language);
        }
        return instance;
    }

    public static synchronized ApiService getInstance() throws InitializationError {
        if (instance == null) {
            throw new InitializationError("ApiService was not initialized before");
        }
        return instance;
    }


}
