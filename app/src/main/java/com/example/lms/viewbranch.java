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

public class viewbranch extends AppCompatActivity {

    ListView branchlist;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_viewbranch);

        SQLiteDatabase db = openOrCreateDatabase("bookmanagementdb", Context.MODE_PRIVATE, null);

        branchlist = findViewById(R.id.branchlist);
        final Cursor c = db.rawQuery("SELECT * FROM branch", null);
        int id = c.getColumnIndex("BRANCH_ID");
        int name = c.getColumnIndex("BRANCH_NAME");
        int address = c.getColumnIndex("ADDRESS");

        titles.clear();
        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, titles);

        branchlist.setAdapter(arrayAdapter);

        final ArrayList<branch> bran = new ArrayList<branch>();

        if(c.moveToFirst()) {
            do {
                branch branch = new branch();
                branch.id = c.getString(id);
                branch.name = c.getString(name);
                branch.address = c.getString(address);
                bran.add(branch);

                // Adjust the format of the displayed string to ensure consistent spacing
                String formattedString = String.format("%-10s %-20s %-30s", c.getString(id), c.getString(name), c.getString(address));
                titles.add(formattedString);
            } while (c.moveToNext());

            arrayAdapter.notifyDataSetChanged();
            branchlist.invalidateViews();
        }

        branchlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                branch branch = bran.get(position);
                Intent i = new Intent(getApplicationContext(), editbranch.class);
                i.putExtra("id", branch.id);
                i.putExtra("name", branch.name);
                i.putExtra("address", branch.address);
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