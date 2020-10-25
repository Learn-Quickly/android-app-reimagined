package com.pinkertone.apiwrapper;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;

class HeadersInterceptor implements Interceptor {
    private final String authToken;
    private String language = "en";

    public HeadersInterceptor(String authToken, String language) {
        this.authToken = authToken;
        this.language = language;
    }

    public HeadersInterceptor(String authToken) {
        this.authToken = authToken;
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    @Override
    @NonNull
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
