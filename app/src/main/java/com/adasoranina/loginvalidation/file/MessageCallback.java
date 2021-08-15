package com.adasoranina.loginvalidation.file;

import androidx.annotation.StringRes;

public interface MessageCallback {
    void success(@StringRes int message);

    void error(@StringRes int message);
}
