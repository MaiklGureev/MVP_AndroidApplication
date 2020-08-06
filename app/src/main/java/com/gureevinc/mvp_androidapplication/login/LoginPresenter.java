package com.gureevinc.mvp_androidapplication.login;

import android.content.Intent;

import com.gureevinc.mvp_androidapplication.Repository;
import com.gureevinc.mvp_androidapplication._data.User;
import com.gureevinc.mvp_androidapplication.about.AboutUserActivity;
import com.gureevinc.mvp_androidapplication.registration.RegistrationActivity;

public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View loginView;
    private Repository repository;

    public LoginPresenter(LoginContract.View view) {
        this.loginView = view;
        repository = Repository.getInstance();
    }

    @Override
    public void confirmUserLogin() {
        String login = loginView.getUserLogin();
        String password = loginView.getUserPassword();
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);

        User trueUser = repository.getDbHelper().readFromFile();

        if (trueUser != null) {
            boolean loginIsRight = user.getLogin().equals(trueUser.getLogin());
            boolean passwordIsRight = user.getPassword().equals(trueUser.getPassword());
            if (loginIsRight && passwordIsRight) {
                Intent intent = new Intent(loginView.getContext(), AboutUserActivity.class);
                loginView.getContext().startActivity(intent);
            } else {
                if(!(loginIsRight&&passwordIsRight)) {
                    loginView.showMessage("Incorrect login or password!");
                }
            }
        } else {
            loginView.showMessage("File not exist!");
        }
    }

    @Override
    public void doRegistration() {
        Intent intent = new Intent(loginView.getContext(), RegistrationActivity.class);
        loginView.getContext().startActivity(intent);
    }
}
