package com.pinkertone.apiwrapper;

public interface ICallback<T> {
    void onSuccess(T object);
    void onFail(Throwable t);
}
