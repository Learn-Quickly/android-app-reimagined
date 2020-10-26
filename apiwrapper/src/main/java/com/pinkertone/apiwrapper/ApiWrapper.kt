package com.pinkertone.apiwrapper

import com.pinkertone.apiwrapper.types.Token
import com.pinkertone.apiwrapper.types.UserProfile

// Singleton for client to make requests
class ApiWrapper private constructor(
    private val baseUrl: String,
    private val language: String,
) {
    private val apiService: ApiService = ApiService(baseUrl, language)
    private val responseHandler: ResponseHandler = ResponseHandler()

    fun setAuthToken(authToken: String?) {
        apiService.setAuthToken(authToken ?: "")
    }

    private constructor(
        baseUrl: String,
        authToken: String = "",
        language: String
    ) : this(baseUrl, language) {
        apiService.setAuthToken(authToken)
    }

    suspend fun loginUser(username: String, password: String): Resource<Token> {
        return try {
            val token = apiService.api.loginUser(username, password)
            apiService.setAuthToken(token.string)
            responseHandler.handleSuccess(token)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    suspend fun createUser(
        username: String,
        email: String,
        password: String
    ): Resource<Token> {
        return try {
            responseHandler.handleSuccess(
                apiService.api.createUser(username, email, password)
            )
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    suspend fun getUserInfo(): Resource<UserProfile> {
        return try {
            responseHandler.handleSuccess(apiService.api.getUserInfo())
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    val isAuthorized: Boolean
        get() = apiService.getAuthToken().isNotEmpty()

    companion object {
        private var instance: ApiWrapper? = null

        @Synchronized
        fun getInstance(baseUrl: String, authToken: String, language: String): ApiWrapper {
            if (instance == null) {
                instance = ApiWrapper(baseUrl, authToken, language)
            }
            return instance!!
        }

        @Synchronized
        fun getInstance(baseUrl: String, language: String): ApiWrapper {
            if (instance == null) {
                instance = ApiWrapper(baseUrl, language)
            }
            return instance!!
        }

        @Synchronized
        @Throws(RuntimeException::class)
        fun getInstance(): ApiWrapper {
            if (instance == null) {
                throw RuntimeException("ApiService was not initialized before")
            }
            return instance!!
        }
    }

}