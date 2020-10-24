package com.pinkertone.test.java;

import com.pinkertone.apiwrapper.ApiWrapper;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.BeforeClass;


public class ApiWrapperTest {
    private final String BASE_URL = "http://lqrl.tk/";
    private ApiWrapper wrapper;

    public ApiWrapperTest() {
        wrapper = new ApiWrapper(BASE_URL, "uk");
    }

    @Test
    public void loginUser() {
        assertFalse(wrapper.isAuthorized());

        wrapper.loginUser("let45fc", "let45fclet45fc");
        assertTrue(wrapper.isAuthorized());

    }

    @Test
    public void createUser() {  // TODO: write this test with mock
    }

    @Test
    public void getUserInfo() {
    }
}