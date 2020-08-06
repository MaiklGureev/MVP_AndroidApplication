package com.gureevinc.mvp_androidapplication.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gureevinc.mvp_androidapplication.R;
import com.gureevinc.mvp_androidapplication._data.User;
import com.gureevinc.mvp_androidapplication.registration.RegistrationActivity;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LoginContract.Presenter presenter;
    private EditText loginField;
    private EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenter(this);
        loginField = findViewById(R.id.edit_text_login);
        passwordField = findViewById(R.id.edit_text_password);
    }

    @Override
    protected void onResume() {
        super.onResume();
        passwordField.setText("");
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public String getUserLogin() {
        return loginField.getText().toString();
    }

    @Override
    public String getUserPassword() {
        return passwordField.getText().toString();
    }

    @Override
    public Context getContext() {
        return LoginActivity.this;
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_authorization: {
                presenter.confirmUserLogin();
                break;
            }
            case R.id.button_registration: {
                presenter.doRegistration();
                break;
            }
        }
    }

}
