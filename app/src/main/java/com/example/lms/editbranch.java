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

public class editbranch extends AppCompatActivity {

    EditText ed1, ed2, ed3;
    Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editbranch);

        ed1 = findViewById(R.id.name);
        ed2 = findViewById(R.id.address);
        ed3 = findViewById(R.id.id);

        b1 = findViewById(R.id.btnupdatebranch);
        b2 = findViewById(R.id.btndeletebranch);
        b3 = findViewById(R.id.btnbackbranch);

        Intent i = getIntent();
        String t1 = i.getStringExtra("id").toString();
        String t2 = i.getStringExtra("name").toString();
        String t3 = i.getStringExtra("address").toString();

        ed3.setText(t1);
        ed1.setText(t2);
        ed2.setText(t3);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), viewbranch.class);
                startActivity(i);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void delete() {
        try {
            String id = ed3.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("bookmanagementdb", Context.MODE_PRIVATE, null);
            String sql = "DELETE FROM Branch WHERE BRANCH_ID = ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, id);
            statement.execute();
            Toast.makeText(this, "Record Deleted", Toast.LENGTH_LONG).show();
            ed1.setText("");
            ed2.setText("");
            ed1.requestFocus();
        } catch (Exception ex) {
            Toast.makeText(this, "Record Deletion Failed", Toast.LENGTH_LONG).show();
        }
    }

    public void update() {
        try {
            String name = ed1.getText().toString();
            String address = ed2.getText().toString();
            String id = ed3.getText().toString();
            SQLiteDatabase db = openOrCreateDatabase("bookmanagementdb", Context.MODE_PRIVATE, null);
            String sql = "UPDATE Branch SET BRANCH_NAME = ?, ADDRESS = ? WHERE BRANCH_ID = ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, name);
            statement.bindString(2, address);
            statement.bindString(3, id);
            statement.execute();
            Toast.makeText(this, "Record Updated", Toast.LENGTH_LONG).show();
            ed1.setText("");
            ed2.setText("");
            ed1.requestFocus();
        } catch (Exception ex) {
            Toast.makeText(this, "Record Update Failed", Toast.LENGTH_LONG).show();
        }
    }
}