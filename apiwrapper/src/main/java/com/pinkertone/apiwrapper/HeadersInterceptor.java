package com.pinkertone.apiwrapper;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Request.Builder;

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

        Builder builder = original.newBuilder();
        if (!authToken.isEmpty()) {
            builder
                    .header("Accept", "application/json")
                    .header("Authorization", "Token " + authToken)
                    .header("Accept-Language", language)
                    .method(original.method(), original.body());
        } else {
            builder
                    .header("Accept", "application/json")
                    .header("Accept-Language", language)
                    .method(original.method(), original.body());
        }
        Request request = builder.build();

        Response response = chain.proceed(request);

        return response;
    }
}
