package com.gureevinc.mvp_androidapplication._data;


import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DbHelper {

    private static final String FILE_NAME = "/myFile.txt";

    public void writeToFile(User user) {
        try {
            File file = new File(Environment.getExternalStorageDirectory().toString() + FILE_NAME);
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(user);
            fileOutputStream.close();
            objectOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User readFromFile() {
        try {
            User user;
            File file = new File(Environment.getExternalStorageDirectory().toString() + FILE_NAME);
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            user = (User) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
            return user;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteFile() {
            File file = new File(Environment.getExternalStorageDirectory().toString() + FILE_NAME);
            file.delete();
    }

}