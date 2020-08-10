package com.gureevinc.mvp_androidapplication.login;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.gureevinc.mvp_androidapplication._data.User;

public interface LoginContract {

    interface View{
        void showMessage(String message);
        String getUserLogin();
        String getUserPassword();
        Context getContext();
        Activity getActivity();
    }
    interface Presenter{
        void confirmUserLogin();
        void doRegistration();

        void checkRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);
    }


}
