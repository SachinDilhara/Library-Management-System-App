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

public class editloan extends AppCompatActivity {

    EditText ed1, ed2, ed3, ed4, ed5, ed6;
    Button b1, b2, b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editloan);

        ed1 = findViewById(R.id.accessno);
        ed2 = findViewById(R.id.branchid);
        ed3 = findViewById(R.id.cardno);
        ed4 = findViewById(R.id.dateout);
        ed5 = findViewById(R.id.datedue);
        ed6 = findViewById(R.id.datereturned);

        b1 = findViewById(R.id.btnupdateloan);
        b2 = findViewById(R.id.btndeleteloan);
        b3 = findViewById(R.id.btnbackloan);

        Intent i = getIntent();
        String t1 = i.getStringExtra("ACCESS_NO").toString();
        String t2 = i.getStringExtra("BRANCH_ID").toString();
        String t3 = i.getStringExtra("CARD_NO").toString();
        String t4 = i.getStringExtra("DATE_OUT").toString();
        String t5 = i.getStringExtra("DATE_DUE").toString();
        String t6 = i.getStringExtra("DATE_RETURNED").toString();

        ed1.setText(t1);
        ed2.setText(t2);
        ed3.setText(t3);
        ed4.setText(t4);
        ed5.setText(t5);
        ed6.setText(t6);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), viewloan.class);
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
            String accessno = ed1.getText().toString();
            String branchid = ed2.getText().toString();
            String cardno = ed3.getText().toString();
            String dateout = ed4.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("bookmanagementdb", Context.MODE_PRIVATE, null);

            String sql = "DELETE FROM Book_Loan WHERE ACCESS_NO = ? AND BRANCH_ID = ? AND CARD_NO = ? AND DATE_OUT = ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, accessno);
            statement.bindString(2, branchid);
            statement.bindString(3, cardno);
            statement.bindString(4, dateout);
            statement.execute();
            Toast.makeText(this, "Record Deleted", Toast.LENGTH_LONG).show();

            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
            ed1.requestFocus();
        } catch (Exception ex) {
            Toast.makeText(this, "Record Deletion Failed", Toast.LENGTH_LONG).show();
        }
    }

    public void update() {
        try {
            String accessno = ed1.getText().toString();
            String branchid = ed2.getText().toString();
            String cardno = ed3.getText().toString();
            String dateout = ed4.getText().toString();
            String datedue = ed5.getText().toString();
            String datereturned = ed6.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("bookmanagementdb", Context.MODE_PRIVATE, null);

            String sql = "UPDATE Book_Loan SET DATE_DUE = ?, DATE_RETURNED = ? WHERE ACCESS_NO = ? AND BRANCH_ID = ? AND CARD_NO = ? AND DATE_OUT = ?";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, datedue);
            statement.bindString(2, datereturned);
            statement.bindString(3, accessno);
            statement.bindString(4, branchid);
            statement.bindString(5, cardno);
            statement.bindString(6, dateout);
            statement.execute();
            Toast.makeText(this, "Record Updated", Toast.LENGTH_LONG).show();

            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
            ed5.setText("");
            ed6.setText("");
            ed1.requestFocus();
        } catch (Exception ex) {
            Toast.makeText(this, "Record Update Failed", Toast.LENGTH_LONG).show();
        }
    }
}
