package com.gureevinc.mvp_androidapplication.login;

import android.content.Context;

import com.gureevinc.mvp_androidapplication._data.User;

public interface LoginContract {

    interface View{
        void showMessage(String message);
        String getUserLogin();
        String getUserPassword();
        Context getContext();
    }
    interface Presenter{
        void confirmUserLogin();
        void doRegistration();
    }


}
