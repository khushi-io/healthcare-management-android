package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ForgotPassword extends AppCompatActivity {

    private EditText edtUsername, edtNewPassword, edtConfirmPassword;
    private Button btnResetPassword;
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        edtUsername = findViewById(R.id.editTextFPLoginUsername);
        edtNewPassword = findViewById(R.id.editTextFPNewPass);
        edtConfirmPassword = findViewById(R.id.editTextFPRetypePass);
        btnResetPassword = findViewById(R.id.buttonReset);
        db = new Database(this, "healthcare", null, 1);

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        String username = edtUsername.getText().toString().trim();
        String newPassword = edtNewPassword.getText().toString().trim();
        String confirmPassword = edtConfirmPassword.getText().toString().trim();

        if (username.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (db.updatePassword(username, newPassword)) {
            Toast.makeText(this, "Password Reset Successful!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(ForgotPassword.this, LoginActivity2.class));
            finish();
        } else {
            Toast.makeText(this, "Username not found!", Toast.LENGTH_SHORT).show();
        }
    }
}