package com.gureevinc.mvp_androidapplication.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.gureevinc.mvp_androidapplication.Repository;
import com.gureevinc.mvp_androidapplication._data.User;
import com.gureevinc.mvp_androidapplication.about.AboutUserActivity;
import com.gureevinc.mvp_androidapplication.registration.RegistrationActivity;

import java.util.Arrays;

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
        int permissionStatusWES = ContextCompat.checkSelfPermission(loginView.getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permissionStatusRES = ContextCompat.checkSelfPermission(loginView.getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionStatusWES == PackageManager.PERMISSION_GRANTED
                && permissionStatusRES == PackageManager.PERMISSION_GRANTED) {

            Intent intent = new Intent(loginView.getContext(), RegistrationActivity.class);
            loginView.getContext().startActivity(intent);

        } else {
            ActivityCompat.requestPermissions(loginView.getActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        }

    }

    @Override
    public void checkRequestPermissionsResult(int requestCode,  String[] permissions, int[] grantResults) {

        if (grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED || grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                doRegistration();
            }
        } else {
            ActivityCompat.requestPermissions(loginView.getActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        }
        Toast.makeText(loginView.getContext(), requestCode + " " + Arrays.toString(permissions) + " " + Arrays.toString(grantResults), Toast.LENGTH_LONG).show();
    }


}
