package com.pinkertone.apiwrapper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {  // Singleton for making requests to API
    private static ApiService instance;
    private static String baseUrl;

    public IApiEndpoints apiService;

    public ApiService(String baseUrl){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(IApiEndpoints.class);

    }

    public static synchronized ApiService getInstance(String baseUrl) {
        if (instance == null) {
            instance = new ApiService(baseUrl);
        }
        return instance;
    }
}
