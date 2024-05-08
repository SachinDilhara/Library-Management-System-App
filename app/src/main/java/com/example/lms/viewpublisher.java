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

public class viewpublisher extends AppCompatActivity {

    ListView publisherlist;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_viewpublisher);

        SQLiteDatabase db = openOrCreateDatabase("bookmanagementdb", Context.MODE_PRIVATE, null);

        publisherlist = findViewById(R.id.publisherlist);
        final Cursor c = db.rawQuery("select * from publisher", null);
        int name = c.getColumnIndex("NAME");
        int address = c.getColumnIndex("ADDRESS");
        int phone = c.getColumnIndex("PHONE");

        titles.clear();
        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,titles);

        publisherlist.setAdapter(arrayAdapter);

        final ArrayList<publisher> publ = new ArrayList<publisher>();

        if(c.moveToFirst())
        {
            do {

                publisher publisher = new publisher();
                publisher.name = c.getString(name);
                publisher.address = c.getString(address);
                publisher.phone = c.getString(phone);

                publ.add(publisher);

                // Create a formatted string with consistent spacing between columns
                String formattedTitle = String.format("%-20s %-20s %-20s", c.getString(name), c.getString(address), c.getString(phone));
                titles.add(formattedTitle);

            }while (c.moveToNext());

            arrayAdapter.notifyDataSetChanged();
            publisherlist.invalidateViews();
        }

        publisherlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                publisher publisher = publ.get(position);
                Intent i = new Intent(getApplicationContext(), editpublisher.class);
                i.putExtra("NAME", publisher.name);
                i.putExtra("ADDRESS", publisher.address);
                i.putExtra("PHONE", publisher.phone);
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