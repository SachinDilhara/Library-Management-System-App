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

public class editcopy extends AppCompatActivity {

    EditText ed1, ed2, ed3;
    Button b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editcopy);

        ed1 = findViewById(R.id.branchid);
        ed2 = findViewById(R.id.bookid);
        ed3 = findViewById(R.id.access);


        b2 = findViewById(R.id.btndeletecopy);
        b3 = findViewById(R.id.btnbackcopy);

        Intent i = getIntent();
        String t1 = i.getStringExtra("ACCESS_NO").toString();
        String t2 = i.getStringExtra("BOOK_ID").toString();
        String t3 = i.getStringExtra("BRANCH_ID").toString();


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
                Intent i = new Intent(getApplicationContext(), viewcopy.class);
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

            String access = ed3.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("bookmanagementdb", Context.MODE_PRIVATE, null);


            String sql = "delete from Book_Copy where ACCESS_NO = ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, access);
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