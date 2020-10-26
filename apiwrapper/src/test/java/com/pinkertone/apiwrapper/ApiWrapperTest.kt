package com.pinkertone.apiwrapper

import com.pinkertone.apiwrapper.types.UserProfile
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class ApiWrapperTest {
    private val wrapper: ApiWrapper = ApiWrapper.getInstance(Constants.BASE_URL, "uk")

    @Throws(InterruptedException::class)
    private fun loginWrapper() = runBlocking {
        if (!wrapper.isAuthorized) {
            Assert.assertFalse(wrapper.isAuthorized)
            launch {
                wrapper.loginUser(Constants.TEST_USERNAME, Constants.TEST_PASSWORD)
            }.join()
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
    fun userInfo() = runBlocking {
        var userProfile: UserProfile? = null
        loginWrapper()
        launch {
            userProfile = wrapper.getUserInfo().data
        }.join()
        Assert.assertNotNull(userProfile)
    }
}