package com.gureevinc.mvp_androidapplication._data;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String age;
    private String login;
    private String password;

    public User() {
    }

    public User(String name, String age, String login, String password) {
        this.name = name;
        this.age = age;
        this.login = login;
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
