package com.adasoranina.loginvalidation.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.adasoranina.loginvalidation.R;
import com.adasoranina.loginvalidation.file.login.LoginFile;
import com.adasoranina.loginvalidation.file.MessageCallback;
import com.adasoranina.loginvalidation.ui.home.HomeActivity;
import com.adasoranina.loginvalidation.ui.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText inputUsername;
    private EditText inputPassword;

    private final LoginFile loginFile = new LoginFile(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputUsername = findViewById(R.id.input_username);
        inputPassword = findViewById(R.id.input_password);
        Button buttonRegister = findViewById(R.id.button_register);
        Button buttonLogin = findViewById(R.id.button_login);

        buttonLogin.setOnClickListener(v -> {
            String username = inputUsername.getText().toString().trim();
            String password = inputPassword.getText().toString().trim();

            loginFile.login(username, password, new MessageCallback() {
                @Override
                public void success(int message) {
                    Toast.makeText(LoginActivity.this, getString(message), Toast.LENGTH_SHORT).show();
                    navigateHome();
                }

                @Override
                public void error(int message) {
                    Toast.makeText(LoginActivity.this, getString(message), Toast.LENGTH_SHORT).show();
                }
            });

        });

        buttonRegister.setOnClickListener(v -> RegisterActivity.navigate(this));
    }

    private void navigateHome() {
        HomeActivity.navigate(this);
        finishAffinity();
    }

    public static void navigate(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

}
