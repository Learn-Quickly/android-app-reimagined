package com.pinkertone.apiwrapper

import com.pinkertone.apiwrapper.types.Token
import com.pinkertone.apiwrapper.types.UserProfile
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.io.IOException

class ApiServiceTest {
    @Test
    @Throws(IOException::class)
    fun createWithoutAndSettingTokenTest() = runBlocking {
        val apiServiceSingleton = ApiService(Constants.BASE_URL, "uk")

        var token: Token = Token("")
        // Creating call for login
        launch {
            token = apiServiceSingleton.api
                .loginUser(Constants.TEST_USERNAME, Constants.TEST_PASSWORD)
        }.join()
        Assert.assertNotNull(token)
        apiServiceSingleton.setAuthToken(token.string)

        // Creating call for getting user data
        var info: UserProfile = UserProfile()
        launch {
            info = apiServiceSingleton.api
                .getUserInfo() // This endpoint was chosen just if auth interceptor works correctly
        }.join()
        Assert.assertNotNull(info)
    }
}