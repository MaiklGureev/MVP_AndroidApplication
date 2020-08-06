package com.gureevinc.mvp_androidapplication.about;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.gureevinc.mvp_androidapplication.R;
import com.gureevinc.mvp_androidapplication._data.User;

public class AboutUserActivity extends AppCompatActivity implements AboutUserContract.View {

    private TextView name;
    private TextView age;
    private TextView login;
    private TextView password;

    private AboutUserContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_user);

        name = findViewById(R.id.text_name);
        age = findViewById(R.id.text_age);
        login = findViewById(R.id.text_login);
        password = findViewById(R.id.text_password);

        presenter = new AboutUserPresenter(this);
        presenter.loadUserData();
    }

    @Override
    public void setUserData(User user) {
        name.setText("Name: " + user.getName());
        age.setText("Age: " + user.getAge());
        login.setText("Login: " + user.getLogin());
        password.setText("Password: " + user.getPassword());
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public Context getContext() {
        return AboutUserActivity.this;
    }


    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_delete_user:{
                presenter.deleteUser();
                break;
            }case R.id.button_edit_information:{
                presenter.editUser();
                break;
            }
            case R.id.button_exit: {
                finish();
                break;
            }
        }
    }
}
