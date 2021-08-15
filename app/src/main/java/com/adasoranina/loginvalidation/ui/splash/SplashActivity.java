package com.adasoranina.loginvalidation.ui.splash;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.adasoranina.loginvalidation.R;
import com.adasoranina.loginvalidation.file.InternalFile;
import com.adasoranina.loginvalidation.ui.home.HomeActivity;
import com.adasoranina.loginvalidation.ui.login.LoginActivity;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            if (InternalFile.getIsLogin(this)) {
                HomeActivity.navigate(this);
            } else {
                LoginActivity.navigate(this);
            }

            finish();
        }, 2000);

    }
}