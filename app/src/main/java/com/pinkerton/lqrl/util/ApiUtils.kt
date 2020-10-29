package com.pinkerton.lqrl.util

import com.pinkertone.apiwrapper.ApiWrapper
import com.pinkertone.apiwrapper.Resource
import com.pinkertone.apiwrapper.types.Token
import com.pinkertone.apiwrapper.types.UserProfile

public class ApiUtils(
    private var apiWrapper: ApiWrapper = ApiWrapper.getInstance()
) {

    suspend fun checkToken(
        token: Token
    ): Resource<UserProfile> {
        apiWrapper.setAuthToken(token.string)
        return apiWrapper.getUserInfo()
    }

    suspend fun loginUser(uname: String, pass: String): Resource<Token> {
        return apiWrapper.loginUser(uname, pass)
    }

    suspend fun createUser(nickname: String, email: String, password: String): Resource<Token> {
        return apiWrapper.createUser(nickname, email, password)
    }
}