package com.pinkertone.apiwrapper

import com.pinkertone.apiwrapper.types.UserProfile
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class ApiWrapperTest {
    private val wrapper: ApiWrapper = ApiWrapper.getInstance(Constants.BASE_URL, "uk")

    @Throws(InterruptedException::class)
    private fun loginWrapper() {
        if (!wrapper.isAuthorized) {
            Assert.assertFalse(wrapper.isAuthorized)
            runBlocking {
                wrapper.loginUser(Constants.TEST_USERNAME, Constants.TEST_PASSWORD)
            }
        }
    }

    @Test
    @Throws(InterruptedException::class)
    fun loginUser() {
        loginWrapper()
        Assert.assertTrue(wrapper.isAuthorized)
    }

    @Test
    fun createUser() {  // TODO: write this test with mock
    }

    @Throws(InterruptedException::class)
    @Test
    fun userInfo() {
        var userProfile: UserProfile?
        loginWrapper()
        runBlocking {
            userProfile = wrapper.getUserInfo().data
        }
        Assert.assertNotNull(userProfile)
    }
}