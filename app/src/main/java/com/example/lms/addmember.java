package com.example.lms;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
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

public class addmember extends AppCompatActivity {

    EditText ed1, ed2, ed3, ed4;
    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_addmember); // Move setContentView here

        ed1 = findViewById(R.id.name);
        ed2 = findViewById(R.id.address);
        ed3 = findViewById(R.id.phone);
        ed4 = findViewById(R.id.dues);

        b1 = findViewById(R.id.btninsertmember);
        b2 = findViewById(R.id.btnviewmember);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), viewmember.class);
                startActivity(i);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.addmember), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Move insert() method outside of onCreate()
    public void insert() {
        try {
            String name = ed1.getText().toString();
            String address = ed2.getText().toString();
            String phone = ed3.getText().toString();
            String dues = ed4.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("bookmanagementdb", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS Member(CARD_NO VARCHAR PRIMARY KEY, NAME VARCHAR, ADDRESS VARCHAR, PHONE VARCHAR, UNPAID_DUES VARCHAR)");

            String sql = "INSERT INTO Member(CARD_NO, NAME, ADDRESS, PHONE, UNPAID_DUES) VALUES (?, ?, ?, ?, ?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, phone);  // Assuming phone is used as CARD_NO
            statement.bindString(2, name);
            statement.bindString(3, address);
            statement.bindString(4, phone);  // Assuming phone is also stored in PHONE
            statement.bindString(5, dues);
            statement.execute();
            Toast.makeText(this, "Record Added", Toast.LENGTH_LONG).show();

            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
            ed1.requestFocus();

        } catch (Exception ex) {
            Toast.makeText(this, "Record Added Failed", Toast.LENGTH_LONG).show();
        }
    }
}