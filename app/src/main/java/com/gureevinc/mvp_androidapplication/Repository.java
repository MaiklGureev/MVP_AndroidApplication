package com.gureevinc.mvp_androidapplication;

import com.gureevinc.mvp_androidapplication._data.DbHelper;

public class Repository {

    private static volatile Repository REPOSITORY;
    private DbHelper dbHelper;

    private Repository() {
        dbHelper = new DbHelper();
    }

    public static Repository getInstance() {
        Repository result = REPOSITORY;
        if(result!=null){
            return result;
        }
        synchronized (Repository.class){
            if(result==null){
                REPOSITORY = new Repository();
            }
            return REPOSITORY;
        }
    }

    public DbHelper getDbHelper() {
        return dbHelper;
    }
}
