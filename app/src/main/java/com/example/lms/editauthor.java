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

public class editauthor extends AppCompatActivity {

    EditText ed1, ed2;
    Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editauthor);

        ed1 = findViewById(R.id.id);
        ed2 = findViewById(R.id.name);

        b2 = findViewById(R.id.btndeleteauthor);
        b3 = findViewById(R.id.btnbackauthor);

        Intent i = getIntent();
        String t1 = i.getStringExtra("BOOK_ID").toString();
        String t2 = i.getStringExtra("AUTHOR_NAME").toString();



        ed1.setText(t1);
        ed2.setText(t2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();

            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), viewauthor.class);
                startActivity(i);
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

            String name = ed2.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("bookmanagementdb", Context.MODE_PRIVATE, null);


            String sql = "DELETE FROM BOOK_AUTHOR WHERE AUTHOR_NAME = ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, name);
            statement.execute();
            Toast.makeText(this, "Record Deleted", Toast.LENGTH_LONG).show();

            ed1.setText("");
            ed2.setText("");
            ed1.requestFocus();




        } catch (Exception ex) {
            Toast.makeText(this, "Record Deleted Failed", Toast.LENGTH_LONG).show();
        }
    }






}