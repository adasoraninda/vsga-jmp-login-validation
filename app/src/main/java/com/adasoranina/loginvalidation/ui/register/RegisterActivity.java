package com.adasoranina.loginvalidation.ui.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.adasoranina.loginvalidation.R;
import com.adasoranina.loginvalidation.file.MessageCallback;
import com.adasoranina.loginvalidation.file.register.RegisterFile;
import com.adasoranina.loginvalidation.ui.home.HomeActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText inputUsername;
    private EditText inputPassword;
    private EditText inputEmail;
    private EditText inputFullName;
    private EditText inputSchool;
    private EditText inputAddresses;

    private final RegisterFile registerFile = new RegisterFile(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputUsername = findViewById(R.id.input_username);
        inputPassword = findViewById(R.id.input_password);
        inputEmail = findViewById(R.id.input_email);

        inputFullName = findViewById(R.id.input_full_name);
        inputSchool = findViewById(R.id.input_school);
        inputAddresses = findViewById(R.id.input_address);
        Button buttonSave = findViewById(R.id.button_save);

        setUpActionBar();

        buttonSave.setOnClickListener(v -> {
            String username = inputUsername.getText().toString().trim();
            String password = inputPassword.getText().toString().trim();
            String email = inputEmail.getText().toString().trim();
            String fullName = inputFullName.getText().toString().trim();
            String school = inputSchool.getText().toString().trim();
            String address = inputAddresses.getText().toString().trim();

            registerFile.register(username, password,
                    email, fullName, school, address,
                    new MessageCallback() {
                        @Override
                        public void success(int message) {
                            Toast.makeText(RegisterActivity.this,
                                    getString(message),
                                    Toast.LENGTH_SHORT).show();

                            navigateToHome();
                        }

                        @Override
                        public void error(int message) {
                            Toast.makeText(
                                    RegisterActivity.this,
                                    getString(message),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
            );
        });
    }

    private void navigateToHome() {
        HomeActivity.navigate(this);
        finishAffinity();
    }

    private void setUpActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.register);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    public static void navigate(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

}
