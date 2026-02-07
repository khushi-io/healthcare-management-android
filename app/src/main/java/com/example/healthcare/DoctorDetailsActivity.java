package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] doctor_details1 =
            {
                    {"Doctor Name: Dr. Sujata Navalu", "Hospital Address: Khar East", "Qualification: B.A.M.S.", "Mobile No: 9321732684", "100"},
                    {"Doctor Name: Dr. Ajit Waghulkar", "Hospital Address: Bandra East", "Qualification: M.B.B.S.", "Mobile No: 9820951787", "200"},
                    {"Doctor Name: Dr. Sangeeta Chokhani", "Hospital Address: Andheri West", "Qualification: B.H.M.S.", "Mobile No: 8652390823", "600"},
                    {"Doctor Name: Dr. Anshudeep Dodake", "Hospital Address: Parel", "Qualification: M.B.B.S.","Mobile No: 7977608403", "500"},
                    {"Doctor Name: Dr. Kavita Agarwal", "Hospital Address: Kandivali East", "Qualification: M.B.B.S.", "Mobile No: 9819538541", "600"}
            };
    private String[][] doctor_details2 =
            {
                    {"Doctor Name: Ms. Geeta Shenoy", "Hospital Address: Chembur ", "Qualification: B.Sc in Dietitian", "Mobile No: 9869192070", "2000"},
                    {"Doctor Name: Dr. Saily Sanjay Patil", "Hospital Address: Malad West", "Qualification: D.N.H.E.", "Mobile No: 9029740321", "600"},
                    {"Doctor Name: Ms. Sonali Patil", "Hospital Address: Andheri West", "Qualification: B.Sc in Dietetics", "Mobile No: 7506397111", "1000"},
                    {"Doctor Name: Ms. Apeksha Thakkar", "Hospital Address: Mulund West", "Qualification: Diploma in Sports Nutrition", "Mobile No: 9821290011", "2000"},
                    {"Doctor Name: Dr. Karishma Shah", "Hospital Address: Vile Parle East", "Qualification: D.N.H.E.", "Mobile No: 8082230003", "950"}
            };
    private String[][] doctor_details3 =
            {
                    {"Doctor Name: Dr. Kshama Chandana", "Hospital Address: Andheri West", "Qualification: D.T.A.", "Mobile No: 9870433399", "500"},
                    {"Doctor Name: Dr. Gaurav Shetty", "Hospital Address: Kurla West", "Qualification: D.D.S.", "Mobile No: 9820985271", "450"},
                    {"Doctor Name: Dr. Rahul Kumar", "Hospital Address: Thane West", "Qualification: B.D.S.", "Mobile No: 02241318723", "500"},
                    {"Doctor Name: Dr. Niralee Shah", "Hospital Address: Mumbai Central", "Qualification: B.D.S., M.D.S.", "Mobile No: 9987998705", "1000"},
                    {"Doctor Name: Dr. Sanket Mehta", "Hospital Address: Matunga East", "Qualification: B.D.S.", "Mobile No: 9321993651", "500"}
            };
    private String[][] doctor_details4 =
            {
                    {"Doctor Name: Dr. Jiten Chowdhary", "Hospital Address: Mulund West", "Qualification: M.S., M.B.B.S.", "Mobile No: 7777044086", "1000"},
                    {"Doctor Name: Dr. Shrikant Badwe", "Hospital Address: Goregoan West", "Qualification: M.B.B.S., M.S., M.Ch", "Mobile No: 8104125687", "1500"},
                    {"Doctor Name: Dr. Sanjay Sharma", "Hospital Address: Grant Road", "Qualification: M.S., M.B.B.S.","Mobile No: 7506777111", "4000"},
                    {"Doctor Name: Dr. Viral Desai", "Hospital Address: Santacruz", "Qualification: M.B.B.S.", "Mobile No: 9833807001", "2000"},
                    {"Doctor Name: Dr. Pravin Shinde", "Hospital Address: Sion", "Qualification: M.B.B.S.","Mobile No: 9323035494", "3000"}
            };
    private String[][] doctor_details5 =
            {
                    {"Doctor Name: Dr. Sudhir Pillai", "Hospital Address: Mahim West", "Qualification: M.B.B.S.", "Mobile No: 2245108111", "1600"},
                    {"Doctor Name: Dr. Ankur Phatarpekar", "Hospital Address: Prabhadevi", "Qualification: M.D.","Mobile No: 8828473147", "1500"},
                    {"Doctor Name: Dr. Brijesh Agrawal", "Hospital Address: Chembur", "Qualification: M.B.B.S.", "Mobile No: 9321150416", "1000"},
                    {"Doctor Name: Dr. Nagesh Waghmare", "Hospital Address: Marine Lines", "Qualification: M.B.B.S., M.D.", "Mobile No: 02222067676", "500"},
                    {"Doctor Name: Dr. V. K. Shah", "Hospital Address: Sion", "Qualification: M.B.B.S.", "Mobile No: 9082360696", "3000"}
            };
    TextView tv;
    Button btn;
    String[][] doctor_details = {};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewDDTitle);
        btn = findViewById(R.id.buttonDDBack);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if("Family Physicians".equals(title))
            doctor_details = doctor_details1;
        else
        if(title.compareTo("Dietician")==0)
            doctor_details = doctor_details2;
        else
        if(title.compareTo("Dentist")==0)
            doctor_details = doctor_details3;
        else
        if(title.compareTo("Surgeon")==0)
                doctor_details = doctor_details4;
        else
                doctor_details = doctor_details5;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });

        list = new ArrayList();
        for (String[] doctorDetail : doctor_details) {
            item = new HashMap<String, String>();
            item.put("Line1", doctorDetail[0]);
            item.put("Line2", doctorDetail[1]);
            item.put("Line3", doctorDetail[2]);
            item.put("Line4", doctorDetail[3]);
            item.put("Line5", "Consultant Fee: " + doctorDetail[4] + "/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines, new String[]{"Line1","Line2","Line3","Line4","Line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );
        ListView lst = findViewById(R.id.listViewLabTest);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent (DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);
            }
        });
    }
}