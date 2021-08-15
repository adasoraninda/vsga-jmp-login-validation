package com.adasoranina.loginvalidation.file.login;

import android.content.Context;

import com.adasoranina.loginvalidation.R;
import com.adasoranina.loginvalidation.file.InternalFile;
import com.adasoranina.loginvalidation.file.MessageCallback;
import com.adasoranina.loginvalidation.file.StateFile;
import com.adasoranina.loginvalidation.model.User;

public class LoginFile extends InternalFile {

    public LoginFile(Context context) {
        super(context);
    }

    public void login(String username, String password, MessageCallback callback) {
        if (username == null || username.isEmpty() || username.contains(" ")) {
            callback.error(R.string.username_invalid_message);
            return;
        }

        if (password == null || password.isEmpty() || password.contains(" ")) {
            callback.error(R.string.password_invalid_message);
            return;
        }

        User user = new User(username, password);

        readFile(user.getUserName(), data -> {
            if (data == null) {
                callback.error(R.string.login_error_user_message);
                return;
            }

            String[] dataUser = data.split(";");

            if (!dataUser[1].equals(user.getPassword())) {
                callback.error(R.string.login_error_password_message);
                return;
            }

            saveFile(FILENAME, user.getLoginFileFormat(), state -> {
                if (state == StateFile.SUCCESS) {
                    callback.success(R.string.login_success_message);
                } else {
                    callback.error(R.string.login_error_message);
                }
            });
        });

    }

}
