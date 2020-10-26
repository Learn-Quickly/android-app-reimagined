package com.pinkertone.apiwrapper

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

internal class HeadersInterceptor constructor(
    private val authToken: String,
    private var language: String = "en",
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()
        if (authToken.isNotEmpty()) {
            builder
                .header("Accept", "application/json")
                .header("Authorization", "Token $authToken")
                .header("Accept-Language", language)
                .method(original.method, original.body)
        } else {
            builder
                .header("Accept", "application/json")
                .header("Accept-Language", language)
                .method(original.method, original.body)
        }
        val request = builder.build()
        return chain.proceed(request)
    }
}