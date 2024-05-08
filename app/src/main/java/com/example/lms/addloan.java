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

public class addloan extends AppCompatActivity {

    EditText ed1, ed2, ed3, ed4, ed5, ed6;
    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_addloan); // Move setContentView here

        ed1 = findViewById(R.id.accessno);
        ed2 = findViewById(R.id.branchid);
        ed3 = findViewById(R.id.cardno);
        ed4 = findViewById(R.id.dateout);
        ed5 = findViewById(R.id.datedue);
        ed6 = findViewById(R.id.datereturned);

        b1 = findViewById(R.id.btninsertloan);
        b2 = findViewById(R.id.btnviewloan);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),viewloan.class);
                startActivity(i);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.addloan), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Move insert() method outside of onCreate()
    public void insert() {
        try {
            String accessno = ed1.getText().toString();
            String branchid = ed2.getText().toString();
            String cardno = ed3.getText().toString();
            String dateout = ed4.getText().toString();
            String datedue = ed5.getText().toString();
            String datereturned = ed6.getText().toString();

            SQLiteDatabase db = openOrCreateDatabase("bookmanagementdb", Context.MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS Book_Loan(ACCESS_NO VARCHAR , BRANCH_ID  VARCHAR,CARD_NO  VARCHAR, DATE_OUT, DATE_DUE, DATE_RETURNED, PRIMARY KEY (ACCESS_NO, BRANCH_ID, CARD_NO, DATE_OUT), FOREIGN KEY (ACCESS_NO, BRANCH_ID) REFERENCES Book_Copy(ACCESS_NO, BRANCH_ID),FOREIGN KEY (CARD_NO) REFERENCES Member(CARD_NO), FOREIGN KEY (BRANCH_ID) REFERENCES Branch(BRANCH_ID) )");

            String sql = "insert into Book_Loan(ACCESS_NO, BRANCH_ID,CARD_NO, DATE_OUT, DATE_DUE, DATE_RETURNED)values(?,?,?,?,?,?)";
            SQLiteStatement statement = db.compileStatement(sql);
            statement.bindString(1, accessno);
            statement.bindString(2, branchid);
            statement.bindString(3, cardno);
            statement.bindString(4, dateout);
            statement.bindString(5, datedue);
            statement.bindString(6, datereturned);
            statement.execute();
            Toast.makeText(this, "Record Added", Toast.LENGTH_LONG).show();

            ed1.setText("");
            ed2.setText("");
            ed3.setText("");
            ed4.setText("");
            ed5.setText("");
            ed6.setText("");
            ed1.requestFocus();




        } catch (Exception ex) {
            Toast.makeText(this, "Record Added Failed", Toast.LENGTH_LONG).show();
        }
    }
}
