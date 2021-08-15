package com.adasoranina.loginvalidation.file.home;

import android.content.Context;

import com.adasoranina.loginvalidation.file.ConsumeResultCallback;
import com.adasoranina.loginvalidation.file.InternalFile;
import com.adasoranina.loginvalidation.model.User;

public class HomeFile extends InternalFile {

    public HomeFile(Context context) {
        super(context);
    }

    public void readLoginFile(ConsumeResultCallback<User> resultCallback) {
        readFile(InternalFile.FILENAME, data -> {
            if (data == null) {
                resultCallback.result(null);
                return;
            }

            String[] dataLoginUser = data.split(";");

            readDataUser(dataLoginUser[0], resultCallback);

        });
    }

    private void readDataUser(String username, ConsumeResultCallback<User> resultCallback) {
        readFile(username, data -> {
            String[] dataUser = data.split(";");

            resultCallback.result(new User(
                    dataUser[0],
                    dataUser[1],
                    dataUser[2],
                    dataUser[3],
                    dataUser[4],
                    dataUser[5]
            ));
        });
    }

}
