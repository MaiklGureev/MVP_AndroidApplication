package com.gureevinc.mvp_androidapplication.registration;

import com.gureevinc.mvp_androidapplication.Repository;
import com.gureevinc.mvp_androidapplication._data.User;

public class RegistrationPresenter implements RegistrationContract.Presenter {

    private RegistrationContract.View registrationView;
    private Repository repository;

    public RegistrationPresenter(RegistrationContract.View view) {
        this.registrationView = view;
        repository = Repository.getInstance();
    }

    @Override
    public void registerUser() {
        String name = registrationView.getName();
        String age = registrationView.getAge();
        String login = registrationView.getLogin();
        String password = registrationView.getPassword();

        boolean fieldIsNotEmpty = !name.isEmpty() && !age.isEmpty() && !login.isEmpty() && !password.isEmpty();
        if (fieldIsNotEmpty) {
            User user = new User(name, age, login, password);
            repository.getDbHelper().writeToFile(user);
            registrationView.finishActivity();
            registrationView.showMessage("Registration completed successfully!");
        } else {
            registrationView.showMessage("Fill in all the fields!");
        }

    }
}
