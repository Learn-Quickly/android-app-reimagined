package com.pinkertone.apiwrapper

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.io.IOException

class ApiServiceTest {
    @Test
    @Throws(IOException::class)
    fun createWithoutAndSettingTokenTest() {
        val apiServiceSingleton = ApiService(Constants.BASE_URL, "uk")

        // Creating call for login
        runBlocking {
            val token = apiServiceSingleton.api
                .loginUser(Constants.TEST_USERNAME, Constants.TEST_PASSWORD)
            Assert.assertNotNull(token)
            apiServiceSingleton.setAuthToken(token.string)
        }
        runBlocking {
            // Creating call for getting user data
            val info = apiServiceSingleton.api
                .getUserInfo() // This endpoint was chosen just if auth interceptor works correctly
            Assert.assertNotNull(info)
        }
    }
}