package com.gureevinc.mvp_androidapplication.about;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.gureevinc.mvp_androidapplication.R;
import com.gureevinc.mvp_androidapplication.Repository;
import com.gureevinc.mvp_androidapplication._data.User;

public class AboutUserPresenter implements AboutUserContract.Presenter {

    private AboutUserContract.View aboutUserView;
    private Repository repository;
    private User currentUser;


    public AboutUserPresenter(AboutUserContract.View view) {
        this.aboutUserView = view;
        repository = Repository.getInstance();
    }

    @Override
    public void loadUserData() {
        currentUser = repository.getDbHelper().readFromFile();
        if (currentUser != null) {
            aboutUserView.setUserData(currentUser);
        } else {
            aboutUserView.showMessage("File not found!");
        }
    }

    @Override
    public void editUser() {
        LayoutInflater layoutInflater = ((Activity) (aboutUserView.getContext())).getLayoutInflater();
        final View view = layoutInflater.inflate(R.layout.registration_fields, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

        final EditText name = view.findViewById(R.id.edit_text_name);
        final EditText age = view.findViewById(R.id.edit_text_age);
        final EditText login = view.findViewById(R.id.edit_text_login);
        final EditText password = view.findViewById(R.id.edit_text_password);

        name.setText(currentUser.getName());
        age.setText(currentUser.getAge());
        login.setText(currentUser.getLogin());
        password.setText(currentUser.getPassword());

        DialogInterface.OnClickListener onClickListenerPositive = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                User editedUser = new User();
                editedUser.setName(name.getText().toString());
                editedUser.setAge(age.getText().toString());
                editedUser.setLogin(login.getText().toString());
                editedUser.setPassword(password.getText().toString());
                repository.getDbHelper().writeToFile(editedUser);
                currentUser = editedUser;
                aboutUserView.setUserData(editedUser);
                aboutUserView.showMessage("User edited!");
            }
        };

        builder.setTitle("Edit user").
                setView(view)
                .setPositiveButton("Ok", onClickListenerPositive)
                .setNegativeButton("Cancel", null)
                .setCancelable(false);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    @Override
    public void deleteUser() {
        repository.getDbHelper().deleteFile();
        aboutUserView.showMessage("Removal was successful!");
        aboutUserView.finishActivity();
    }
}
