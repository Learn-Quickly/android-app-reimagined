package com.pinkertone.apiwrapper;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class ApiService {
    private final String baseUrl;
    private String authToken = "";
    private final String language;

    public IApiEndpoints apiService;

    public ApiService(String baseUrl, String authToken, String language) {
        this(baseUrl, language);
        this.authToken = authToken;
    }

    public ApiService(String baseUrl, String language) {
        this.baseUrl = baseUrl;
        this.language = language;
        initApiService();
    }

    // TODO: test with android
    private void initApiService() {

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

    public String getAuthToken() {
        return authToken;
    }
}
