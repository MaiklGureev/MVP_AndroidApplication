package com.gureevinc.mvp_androidapplication.registration;

import android.content.Context;

import com.gureevinc.mvp_androidapplication._data.User;

public interface RegistrationContract {
    interface View{
        void showMessage(String message);
        void finishActivity();
        String getName();
        String getAge();
        String getLogin();
        String getPassword();
        Context getContext();
    }
    interface Presenter{
        void registerUser();
    }
}
