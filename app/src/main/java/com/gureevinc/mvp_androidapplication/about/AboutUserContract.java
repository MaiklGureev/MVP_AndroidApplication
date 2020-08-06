package com.gureevinc.mvp_androidapplication.about;

import android.content.Context;

import com.gureevinc.mvp_androidapplication._data.User;

public interface AboutUserContract {

    interface View{
        void setUserData(User user);
        void showMessage(String message);
        void finishActivity();
        Context getContext();
    }

    interface Presenter{
        void loadUserData();
        void editUser();
        void deleteUser();
    }

}
