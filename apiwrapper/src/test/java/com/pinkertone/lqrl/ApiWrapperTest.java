package com.pinkertone.lqrl;

import com.pinkertone.apiwrapper.ApiWrapper;
import com.pinkertone.apiwrapper.ICallback;
import com.pinkertone.apiwrapper.types.Token;
import com.pinkertone.apiwrapper.types.UserProfile;

import static org.junit.Assert.*;
import org.junit.Test;


class TokenCallback implements ICallback<Token> {
    Token token;

    @Override
    public void onSuccess(Token token) {
        this.token = token;
    }

    @Override
    public void onFail(Throwable t) {
        t.printStackTrace();
    }

    public boolean isReady() {
        return token != null;
    }
}

class UserProfileCallback implements ICallback<UserProfile> {
    public UserProfile userProfile;

    @Override
    public void onSuccess(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @Override
    public void onFail(Throwable t) {
        t.printStackTrace();
    }

    public boolean isReady() {
        return userProfile != null;
    }
}


public class ApiWrapperTest {
    private final String BASE_URL = "http://lqrl.tk/";
    private ApiWrapper wrapper;

    public ApiWrapperTest() {
        wrapper = new ApiWrapper(BASE_URL, "uk");
    }

    private void loginWrapper() throws InterruptedException {
        TokenCallback callback = new TokenCallback();
        wrapper.loginUser(callback, "let45fc", "let45fclet45fc");
        while (!callback.isReady()) {
            Thread.sleep(5);
        }
    }

    @Test
    public void loginUser() throws InterruptedException {
        assertFalse(wrapper.isAuthorized());
        TokenCallback callback = new TokenCallback();
        wrapper.loginUser(callback, "let45fc", "let45fclet45fc");
        while (!callback.isReady()) {
            Thread.sleep(5);
        }
        assertTrue(wrapper.isAuthorized());
    }

    @Test
    public void createUser() {  // TODO: write this test with mock
    }

    @Test
    public void getUserInfo() throws InterruptedException {

        loginWrapper();

        UserProfileCallback callback = new UserProfileCallback();
        wrapper.getUserInfo(callback);
        while (!callback.isReady()) {
            Thread.sleep(5);
        }
        UserProfile userProfile = callback.userProfile;
        assertNotNull(userProfile);
    }
}