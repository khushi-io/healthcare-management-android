package com.example.healthcare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DoctorDashboardActivity extends AppCompatActivity {

    ListView lvAppointments;
    Database db;
    String doctorName;
    Button btnBack;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctor_dashboard);

        btnBack = findViewById(R.id.buttonDocDBack);
        lvAppointments = findViewById(R.id.listViewAppointments);
        db = new Database(this, "healthcare", null, 1);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDashboardActivity.this, DoctorLoginActivity.class));
            }
        });

        // Get the doctor name from intent
        doctorName = getIntent().getStringExtra("doctorName");

        if (doctorName != null) {
            // Fetch patient details for this doctor
            ArrayList<String> appointments = db.getDoctorAppointments(doctorName);

            if (appointments.isEmpty()) {
                Toast.makeText(this, "No appointments found", Toast.LENGTH_SHORT).show();
            } else {
                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, appointments);
                lvAppointments.setAdapter(adapter);
            }
        } else {
            Toast.makeText(this, "Doctor name not found", Toast.LENGTH_SHORT).show();
        }
    }
}
