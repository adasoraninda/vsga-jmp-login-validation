package com.adasoranina.loginvalidation.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.adasoranina.loginvalidation.R;
import com.adasoranina.loginvalidation.file.InternalFile;
import com.adasoranina.loginvalidation.file.home.HomeFile;
import com.adasoranina.loginvalidation.ui.login.LoginActivity;

public class HomeActivity extends AppCompatActivity {
    private EditText inputUsername;
    private EditText inputPassword;
    private EditText inputEmail;
    private EditText inputFullName;
    private EditText inputSchool;
    private EditText inputAddress;

    private final HomeFile homeFile = new HomeFile(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        inputUsername = findViewById(R.id.input_username);
        inputPassword = findViewById(R.id.input_password);
        inputEmail = findViewById(R.id.input_email);
        inputFullName = findViewById(R.id.input_full_name);
        inputSchool = findViewById(R.id.input_school);
        inputAddress = findViewById(R.id.input_address);
        Button buttonDeleteAccount = findViewById(R.id.button_delete_account);

        setUpActionBar();

        homeFile.readLoginFile(data -> {
            if (data != null) {
                inputUsername.setText(data.getUserName());
                inputPassword.setText(data.getPassword());
                inputEmail.setText(data.getEmail());
                inputFullName.setText(data.getFullName());
                inputSchool.setText(data.getSchool());
                inputAddress.setText(data.getAddress());
            } else {
                Toast.makeText(this, getString(R.string.home_error_read_message), Toast.LENGTH_SHORT).show();
            }

        });

        buttonDeleteAccount.setOnClickListener(v -> showAlertDialog(
                R.string.delete_account,
                R.string.delete_account_message,
                false));
    }

    private void setUpActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.home);
        }
    }

    private void logoutHome(String username) {
        if (username != null) {
            if (!InternalFile.deleteLoginFile(username, this)) {
                Toast.makeText(
                        this,
                        getString(R.string.error_delete_account_message),
                        Toast.LENGTH_SHORT).show();
            }
        }

        if (InternalFile.deleteLoginFile(this)) {
            LoginActivity.navigate(this);
            finishAffinity();
        } else {
            Toast.makeText(this, getString(R.string.error_logout_message), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.menu_home, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            showAlertDialog(R.string.logout, R.string.logout_message, true);
        }

        return super.onOptionsItemSelected(item);
    }

    private void showAlertDialog(@StringRes int title, @StringRes int message, boolean isLogout) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.yes, (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    logoutHome(isLogout ? null : inputUsername.getText().toString());
                })
                .setNegativeButton(R.string.no, (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                })
                .setCancelable(false)
                .show();
    }

    public static void navigate(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }
}
