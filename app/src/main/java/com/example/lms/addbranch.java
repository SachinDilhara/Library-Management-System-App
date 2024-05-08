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

public class addbranch extends AppCompatActivity {

    EditText ed1, ed2;
    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_addbranch); // Move setContentView here

        ed1 = findViewById(R.id.name);
        ed2 = findViewById(R.id.address);

        b1 = findViewById(R.id.btninsertbranch);
        b2 = findViewById(R.id.btnviewbranch);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), viewbranch.class);
                startActivity(i);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.addbranch), (v, insets) -> {
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

            SQLiteDatabase db = openOrCreateDatabase("bookmanagementdb", Context.MODE_PRIVATE, null);
            // Change table name to 'branch' and column names accordingly
            db.execSQL("CREATE TABLE IF NOT EXISTS branch(BRANCH_ID INTEGER PRIMARY KEY AUTOINCREMENT, BRANCH_NAME VARCHAR, ADDRESS VARCHAR)");

            String sql = "INSERT INTO branch(BRANCH_NAME, ADDRESS) VALUES(?, ?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, name);
            statement.bindString(2, address);
            statement.execute();
            Toast.makeText(this, "Record Added", Toast.LENGTH_LONG).show();

            ed1.setText("");
            ed2.setText("");
            ed1.requestFocus();

        } catch (Exception ex) {
            Toast.makeText(this, "Record Added Failed", Toast.LENGTH_LONG).show();
        }
    }
}
