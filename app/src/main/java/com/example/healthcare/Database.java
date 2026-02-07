package com.example.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1 = "create table users(username text, email text, password text)";
        sqLiteDatabase.execSQL(qry1);

        String qry2 = "create table cart(username text, product text, price float, otype text)";
        sqLiteDatabase.execSQL(qry2);

        String qry3 = "create table orderplace(username text, fullname text, address text, contactno text, pincode int, date text, time text, amount float, otype text)";
        sqLiteDatabase.execSQL(qry3);

        String qry4 = "CREATE TABLE doctorlogin(username TEXT PRIMARY KEY, password TEXT)";
        sqLiteDatabase.execSQL(qry4);

        String qry5 = "CREATE TABLE LabTests (packageName TEXT, price float)";
        sqLiteDatabase.execSQL(qry5);

        String qry6 = "CREATE TABLE Medicines (medicineName TEXT, price float)";
        sqLiteDatabase.execSQL(qry6);

        String qry7 = "CREATE TABLE Doctors (name TEXT, address text, qualification TEXT, contact text, consultant_fee float)";
        sqLiteDatabase.execSQL(qry7);

        sqLiteDatabase.execSQL("INSERT INTO LabTests (packageName, price) VALUES " +
                "('Full Body Checkup', 999.0), " +
                "('Blood Glucose Fasting', 299.0), " +
                "('COVID-19 Antibody - IgG', 899.0), " +
                "('Thyroid Check', 499.0), " +
                "('Immunity Check', 699.0)");

        sqLiteDatabase.execSQL("INSERT INTO Medicines (medicineName, price) VALUES " +
                "('Uprise-D3 1000IU Capsule', 50.0), " +
                "('HealthVit Chromium Picolinate 200mcg Capsule', 305.0), " +
                "('Vitamin B Complex Capsules', 448.0), " +
                "('Inlife Vitamin E Wheat Germ Oil Capsule', 539.0), " +
                "('Dolo 650 Tablet', 30.0), " +
                "('Crocin 650 Advance Tablet', 50.0), " +
                "('Strepsils Medicated Lozenges for Sore Throat', 40.0), " +
                "('Tata 1mg Calcium + Vitamin D3', 30.0), " +
                "('Feronia-XT Tablet', 130.0)");

        sqLiteDatabase.execSQL("INSERT INTO Doctors (name, address, qualification, contact, consultant_fee) VALUES " +
                "('Dr. Sujata Navalu', 'Khar East', 'B. A. M. S.', '9321732684', 100), " +
                "('Dr. Ajit Waghulkar', 'Bandra East', 'M. B. B. S.', '9820951787', 200), " +
                "('Dr. Sangeeta Chokhani', 'Andheri West', 'B. H. M. S.', '8652390823', 600), " +
                "('Dr. Anshudeep Dodake', 'Parel', 'M. B. B. S.', '7977608403', 600), " +
                "('Dr. Kavita Agarwal', 'Kandivali East', 'M. B. B. S.', '9819538541', 600), " +
                "('Dr. Geeta Shenoy', 'Chambur', 'B.Sc in Dietician', '9869192070', 2000), " +
                "('Dr. Saily Patil', 'Malad West', 'D. N. H. E.', '9029740321', 600), " +
                "('Dr. Sonali Patil', 'Andheri West', 'B.Sc in Dietics', '7506397111', 1000), " +
                "('Dr. Apeksha Thakkar', 'Mulund West', 'Diploma in Sports Nutrition', '9821290011', 2000), " +
                "('Dr. Karishma Shah', 'Vile Parle East', 'D. N. H. E.', '8082230003', 950), " +
                "('Dr. Kshama Chandana', 'Andheri West', 'D. T. A.', '9870433399', 500), " +
                "('Dr. Gaurav Shetty', 'Kurla West', 'D. D. S.', '9820985271', 450), " +
                "('Dr. Rahul Kumar', 'Thane West', 'B. D. S.', '8082230003', 950), " +
                "('Dr. Niralee Shah', 'Mumbai Central', 'M. D. S.', '9987998705', 1000), " +
                "('Dr. Sanket Mehta', 'Matunga East', 'B. D. S.', '9321993651', 600), " +
                "('Dr. Jiten Chowdhary', 'Mulund West', 'M. B. B. S.', '7777044086', 1000), " +
                "('Dr. Shrikant Badwe', 'Goregoan West', 'M. S.', '8104125687', 1500), " +
                "('Dr. Sanjay Sharma', 'Grant Road', 'M. B. B. S.', '7506777111', 4000), " +
                "('Dr. Viral Desai', 'Santacruz', 'M. B. B. S.', '9833807001', 2000), " +
                "('Dr. Pravin Shinde', 'Sion', 'M. B. B. S.', '8082230003', 950), " +
                "('Dr. Sudhir Pillai', 'Mahim West', 'M. B. B. S.', '2245108111', 1600), " +
                "('Dr. Ankur Phatarpekar', 'Prabhadevi', 'M. D.', '8828473147', 1500), " +
                "('Dr. Brijesh Agarwal', 'Chembur', 'M. B. B. S.', '9321150416', 1000), " +
                "('Dr. Nagesh Waghmare', 'Marine Lines', 'M. D.', '02222067676', 500), " +
                "('Dr. V.K. Shah', 'Sion', 'M. B. B. S.', '9082360693', 3000)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void register(String username, String email, String password) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("email", email);
        cv.put("password", password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users", null, cv);
        db.close();
    }

    public int login(String username, String password) {
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from users where username = ? and password = ?", str);
        if (c.moveToFirst()) {
            result = 1;
        }
        return result;
    }

    public int doctorLogin(String username, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM doctorlogin WHERE username = ? AND password = ?", new String[]{username, password});
        int result = c.moveToFirst() ? 1 : 0;
        c.close();
        db.close();
        return result;
    }

    public void registerDoctor(String username, String password) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("password", password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("doctorlogin", null, cv);
        db.close();
    }

    public boolean updatePassword(String username, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("password", newPassword);

        int rowsAffected = db.update("users", cv, "username=?", new String[]{username});
        db.close();

        return rowsAffected > 0;
    }

    public ArrayList<String> getDoctorAppointments(String doctorName) {
        ArrayList<String> appointments = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM orderplace WHERE fullname = ?", new String[]{doctorName});

        if (c.moveToFirst()) {
            do {
                String appointmentDetails = c.getString(1) + " - " + c.getString(2) + " - " + c.getString(3) + " - " + c.getString(4) + " - " + c.getString(5) + " - " + c.getString(6) + " - " + c.getString(7);
                appointments.add(appointmentDetails);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return appointments;
    }

    public void addCart(String username, String product, float price, String otype) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("product", product);
        cv.put("price", price);
        cv.put("otype", otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("cart", null, cv);
        db.close();
    }

    public int checkCart(String username, String product) {
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = product;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from cart where username = ? and product = ?", str);
        if (c.moveToFirst()) {
            result = 1;
        }
        db.close();
        return result;
    }

    public void removeCart(String username, String otype) {
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        SQLiteDatabase db = getWritableDatabase();
        db.delete("cart", "username=? and otype=?", str);
        db.close();
    }

    public ArrayList getCartData(String username, String otype) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        Cursor c = db.rawQuery("select * from cart where username = ? and otype = ?", str);
        if (c.moveToFirst()) {
            do {
                String product = c.getString(1);
                String price = c.getString(2);
                arr.add(product + "$" + price);
            } while (c.moveToNext());
        }
        db.close();
        return arr;
    }

    public void addOrder(String username, String fullname, String address, String contact, int pincode, String date, String time, float price, String otype) {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("fullname", fullname);
        cv.put("address", address);
        cv.put("contactno", contact);
        cv.put("pincode", pincode);
        cv.put("date", date);
        cv.put("time", time);
        cv.put("amount", price);
        cv.put("otype", otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("orderplace", null, cv);
        db.close();
    }

    public ArrayList getOrderData(String username) {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[1];
        str[0] = username;
        Cursor c = db.rawQuery("select * from orderplace where username = ?", str);
        if (c.moveToFirst()) {
            do {
                arr.add(c.getString(1) + "$" + c.getString(2) + "$" + c.getString(3) + "$" + c.getString(4) + "$" + c.getString(5) + "$" + c.getString(6) + "$" + c.getString(7) + "$" + c.getString(8));
            } while (c.moveToNext());
        }
        db.close();
        return arr;
    }

    public int checkAppointmentExists(String username, String fullname, String address, String contact, String date, String time) {
        int result=0;
        String str[] = new String[6];
        str[0] = username;
        str[1] = fullname;
        str[2] = address;
        str[3] = contact;
        str[4] = date;
        str[5] = time;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from orderplace where username = ? and fullname = ? and address = ? and contactno = ? and date = ? and time = ?",str);
        if (c.moveToFirst()) {
            result=1;
        }
        db.close();
        return result;
    }
}