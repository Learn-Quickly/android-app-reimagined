package com.pinkertone.apiwrapper

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService constructor(
    private val baseUrl: String,
    private val language: String,
) {
    private var authToken: String = ""

    lateinit var api: IApiEndpoints

    constructor(
        baseUrl: String,
        authToken: String,
        language: String
    ) : this(baseUrl, language) {
        this.authToken = authToken
    }

    init {
        initApiService()
    }

    fun setAuthToken(token: String?) {
        this.authToken = token ?: ""
        initApiService()
    }

    fun getAuthToken(): String {
        return authToken
    }

    // TODO: test with android
    private fun initApiService() {
        val httpClient =
            OkHttpClient.Builder().addInterceptor(HeadersInterceptor(authToken ?: "", language))
                .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
        api = retrofit.create(IApiEndpoints::class.java)
    }
}