package com.pinkertone.apiwrapper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class HeadersInterceptor implements Interceptor {
    private String authToken;
    private String language = "en";

    public HeadersInterceptor(String authToken, String language){
        this.authToken = authToken;
        this.language = language;
    }

    public HeadersInterceptor(String authToken){
        this.authToken = authToken;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        Request request = original.newBuilder()
                .header("Accept", "application/json")
                .header("Authorization", authToken)
                .header("Accept-Language", language)
                .method(original.method(), original.body())
                .build();

        Response response = chain.proceed(request);

        return response;
    }
}
