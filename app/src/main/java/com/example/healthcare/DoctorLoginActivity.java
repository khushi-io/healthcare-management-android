package com.example.healthcare;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DoctorLoginActivity extends AppCompatActivity {

    EditText DoctorUsername, DoctorPassword;
    Button btnDocLogin;
    TextView tvRegister;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctor_login);

        DoctorUsername = findViewById(R.id.editTextDocLoginUsername);
        DoctorPassword = findViewById(R.id.editTextDocLoginPassword);
        btnDocLogin = findViewById(R.id.buttonDocLogin);
        tvRegister = findViewById(R.id.tvRegister);
        db = new Database(this, "healthcare", null, 1);

        btnDocLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = DoctorUsername.getText().toString().trim();
                String password = DoctorPassword.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(DoctorLoginActivity.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (db.doctorLogin(username, password) == 1) {
                        Toast.makeText(DoctorLoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DoctorLoginActivity.this, DoctorDashboardActivity.class);
                        intent.putExtra("doctorName", username);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(DoctorLoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        tvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(DoctorLoginActivity.this, DoctorRegisterActivity.class);
            startActivity(intent);
        });
    }
}

