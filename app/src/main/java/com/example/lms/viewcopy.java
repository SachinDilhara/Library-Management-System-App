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

public class viewcopy extends AppCompatActivity {

    ListView copylist;
    ArrayList<String> titles = new  ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_viewcopy);

        SQLiteDatabase db = openOrCreateDatabase("bookmanagementdb", Context.MODE_PRIVATE, null);

        copylist = findViewById(R.id.copylist);
        final Cursor c = db.rawQuery("select * from Book_Copy", null);
        int access = c.getColumnIndex("ACCESS_NO");
        int bookid = c.getColumnIndex("BOOK_ID");
        int branchid = c.getColumnIndex("BRANCH_ID");

        titles.clear();
        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, titles);

        copylist.setAdapter(arrayAdapter);

        final ArrayList<copy> cop = new ArrayList<copy>();

        if(c.moveToFirst()) {
            do {
                copy copy = new copy();
                copy.access = c.getString(access);
                copy.bookid = c.getString(bookid);
                copy.branchid = c.getString(branchid);

                cop.add(copy);

                // Adjust the format of the string to add padding between columns
                String title = String.format("%-30s %-30s %-30s", c.getString(access), c.getString(bookid), c.getString(branchid));
                titles.add(title);

            } while (c.moveToNext());

            arrayAdapter.notifyDataSetChanged();
            copylist.invalidateViews();
        }

        copylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aa = titles.get(position).toString();
                copy copy = cop.get(position);
                Intent i = new Intent(getApplicationContext(), editcopy.class);
                i.putExtra("ACCESS_NO",copy.access);
                i.putExtra("BOOK_ID",copy.bookid);
                i.putExtra("BRANCH_ID",copy.branchid);
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