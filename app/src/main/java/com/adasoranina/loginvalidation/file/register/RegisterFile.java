package com.adasoranina.loginvalidation.file.register;

import android.content.Context;

import com.adasoranina.loginvalidation.R;
import com.adasoranina.loginvalidation.file.InternalFile;
import com.adasoranina.loginvalidation.file.MessageCallback;
import com.adasoranina.loginvalidation.file.StateFile;
import com.adasoranina.loginvalidation.model.User;

public class RegisterFile extends InternalFile {

    public RegisterFile(Context context) {
        super(context);
    }


    public void register(String username,
                         String password,
                         String email,
                         String fullName,
                         String school,
                         String address,
                         MessageCallback callback) {

        if (username == null || username.isEmpty() || username.contains(" ")) {
            callback.error(R.string.username_invalid_message);
            return;
        }

        if (password == null || password.isEmpty() || password.contains(" ")) {
            callback.error(R.string.password_invalid_message);
            return;
        }

        if (email == null || email.isEmpty() || email.contains(" ")) {
            callback.error(R.string.email_invalid_message);
            return;
        }

        if (fullName == null || fullName.isEmpty() || fullName.contains(" ")) {
            callback.error(R.string.fullname_invalid_message);
            return;
        }

        if (school == null || school.isEmpty() || school.contains(" ")) {
            callback.error(R.string.school_invalid_message);
            return;
        }

        if (address == null || address.isEmpty() || address.contains(" ")) {
            callback.error(R.string.address_invalid_message);
            return;
        }

        User user = new User(
                username,
                password,
                email,
                fullName,
                school,
                address
        );

        saveFile(user.getUserName(), user.getRegisterFileFormat(), state -> {
            if (state == StateFile.SUCCESS) {
                saveFile(RegisterFile.FILENAME, user.getLoginFileFormat(), stateLogin -> {
                    if (stateLogin == StateFile.SUCCESS) {
                        callback.success(R.string.register_success_message);
                    } else {
                        callback.error(R.string.register_error_message);
                    }
                });
            } else {
                callback.error(R.string.register_error_message);
            }
        });

    }

}
