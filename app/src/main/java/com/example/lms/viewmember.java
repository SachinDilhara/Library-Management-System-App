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

public class viewmember extends AppCompatActivity {

    ListView memberlist;
    ArrayList<String> titles =  new ArrayList<String>();
    ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_viewmember);

        SQLiteDatabase db = openOrCreateDatabase("bookmanagementdb", Context.MODE_PRIVATE, null);

        memberlist = findViewById(R.id.memberlist);
        final Cursor c = db.rawQuery("SELECT * FROM Member", null);
        int cardNoIndex = c.getColumnIndex("CARD_NO");
        int nameIndex = c.getColumnIndex("NAME");
        int addressIndex = c.getColumnIndex("ADDRESS");
        int phoneIndex = c.getColumnIndex("PHONE");
        int duesIndex = c.getColumnIndex("UNPAID_DUES");

        titles.clear();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, titles); // Changed layout

        memberlist.setAdapter(arrayAdapter);

        final ArrayList<member> memb = new ArrayList<member>();

        if (c.moveToFirst()) {
            do {

                member member = new member();
                member.id = c.getString(cardNoIndex);
                member.name = c.getString(nameIndex);
                member.address = c.getString(addressIndex);
                member.phone = c.getString(phoneIndex);
                member.dues = c.getString(duesIndex);

                memb.add(member);

                // Pad columns with spaces to ensure consistent width
                String paddedString = String.format("%-15s %-10s %-10s %-15s %-10s",
                        c.getString(cardNoIndex), c.getString(nameIndex), c.getString(addressIndex),
                        c.getString(phoneIndex), c.getString(duesIndex));
                titles.add(paddedString);

            } while (c.moveToNext());

            arrayAdapter.notifyDataSetChanged();
            memberlist.invalidateViews();
        }

        memberlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String aa = titles.get(position).toString();

                member member = memb.get(position);
                Intent i = new Intent(getApplicationContext(), editmember.class);
                i.putExtra("id", member.id);
                i.putExtra("name", member.name);
                i.putExtra("address", member.address);
                i.putExtra("phone", member.phone);
                i.putExtra("dues", member.dues);
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