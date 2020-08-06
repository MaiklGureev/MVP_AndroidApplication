package com.gureevinc.mvp_androidapplication.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gureevinc.mvp_androidapplication.R;

public class RegistrationActivity extends AppCompatActivity implements RegistrationContract.View {

    EditText nameFiled;
    EditText ageFiled;
    EditText loginFiled;
    EditText passwordFiled;

    RegistrationContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        presenter = new RegistrationPresenter(this);

        nameFiled = findViewById(R.id.edit_text_name);
        ageFiled = findViewById(R.id.edit_text_age);
        loginFiled = findViewById(R.id.edit_text_login);
        passwordFiled = findViewById(R.id.edit_text_password);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public String getName() {
        return nameFiled.getText().toString();
    }

    @Override
    public String getAge() {
        return ageFiled.getText().toString();
    }

    @Override
    public String getLogin() {
        return loginFiled.getText().toString();
    }

    @Override
    public String getPassword() {
        return passwordFiled.getText().toString();
    }

    @Override
    public Context getContext() {
        return RegistrationActivity.this;
    }

    public void onClick(View view) {
        if(view.getId() == R.id.button_registration){
            presenter.registerUser();
        }
    }
}
