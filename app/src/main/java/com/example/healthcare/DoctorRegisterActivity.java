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

public class DoctorRegisterActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnRegisterDoctor;
    Database db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctor_register);

        etUsername = findViewById(R.id.editTextUsername);
        etPassword = findViewById(R.id.editTextPassword);
        btnRegisterDoctor = findViewById(R.id.btnRegisterDoctor);

        db = new Database(this, "healthcare", null, 1);

        btnRegisterDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(DoctorRegisterActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                } else {
                    db.registerDoctor(username, password);
                    Toast.makeText(DoctorRegisterActivity.this, "Doctor Registered Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DoctorRegisterActivity.this, DoctorLoginActivity.class));
                    finish();
                }
            }
        });
    }
}