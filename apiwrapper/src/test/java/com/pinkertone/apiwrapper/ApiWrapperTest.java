package com.pinkertone.apiwrapper;

import com.pinkertone.apiwrapper.types.Token;
import com.pinkertone.apiwrapper.types.UserProfile;

import static com.pinkertone.apiwrapper.Constants.BASE_URL;
import static com.pinkertone.apiwrapper.Constants.TEST_PASSWORD;
import static com.pinkertone.apiwrapper.Constants.TEST_USERNAME;
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


@SuppressWarnings("BusyWait")
public class ApiWrapperTest {
    private final ApiWrapper wrapper;

    public ApiWrapperTest() {
        wrapper = ApiWrapper.getInstance(BASE_URL, "uk");
    }

    private void loginWrapper() throws InterruptedException {
        if (!wrapper.isAuthorized()) {
            TokenCallback callback = new TokenCallback();
            assertFalse(wrapper.isAuthorized());
            wrapper.loginUser(callback, TEST_USERNAME, TEST_PASSWORD);
            while (!callback.isReady()) {
                Thread.sleep(5);
            }
        }
    }

    @Test
    public void loginUser() throws InterruptedException {
        loginWrapper();
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