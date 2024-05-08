package com.example.lms;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class viewloan extends AppCompatActivity {

    ListView loanlist;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_viewloan);

        SQLiteDatabase db = openOrCreateDatabase("bookmanagementdb", Context.MODE_PRIVATE, null);

        loanlist = findViewById(R.id.loanlist);
        final Cursor c = db.rawQuery("select * from Book_Loan", null);
        int access = c.getColumnIndex("ACCESS_NO");
        int branchid = c.getColumnIndex("BRANCH_ID");
        int cardno = c.getColumnIndex("CARD_NO");
        int dateout = c.getColumnIndex("DATE_OUT");
        int datedue = c.getColumnIndex("DATE_DUE");
        int datereturned = c.getColumnIndex("DATE_RETURNED");

        titles.clear();
        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, titles);

        loanlist.setAdapter(arrayAdapter);

        final ArrayList<loan> loa = new ArrayList<loan>();

        if (c.moveToFirst()) {
            do {
                loan loan = new loan();
                loan.access = c.getString(access);
                loan.branchid = c.getString(branchid);
                loan.cardno = c.getString(cardno);
                loan.dateout = c.getString(dateout);
                loan.datedue = c.getString(datedue);
                loan.datereturned = c.getString(datereturned);

                loa.add(loan);

                // Adjust spacing between columns for consistent output
                titles.add(String.format("%-10s %-10s %-10s %-15s %-15s %-15s",
                        c.getString(access), c.getString(branchid), c.getString(cardno),
                        c.getString(dateout), c.getString(datedue), c.getString(datereturned)));

            } while (c.moveToNext());

            arrayAdapter.notifyDataSetChanged();
            loanlist.invalidateViews();
        }

        loanlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aa = titles.get(position).toString();
                loan loan = loa.get(position);
                Intent i = new Intent(getApplicationContext(), editloan.class);
                i.putExtra("ACCESS_NO", loan.access);
                i.putExtra("BRANCH_ID", loan.branchid);
                i.putExtra("CARD_NO", loan.cardno);
                i.putExtra("DATE_OUT", loan.dateout);
                i.putExtra("DATE_DUE", loan.datedue);
                i.putExtra("DATE_RETURNED", loan.datereturned);
                startActivity(i);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}