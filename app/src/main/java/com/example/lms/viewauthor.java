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

public class viewauthor extends AppCompatActivity {

    ListView authorlist;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;
    ArrayList<author> bran = new ArrayList<author>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_viewauthor);

        SQLiteDatabase db = openOrCreateDatabase("bookmanagementdb", Context.MODE_PRIVATE, null);

        authorlist = findViewById(R.id.authorlist);
        final Cursor c = db.rawQuery("select * from Book_Author", null);
        int idIndex = c.getColumnIndex("BOOK_ID");
        int nameIndex = c.getColumnIndex("AUTHOR_NAME");

        titles.clear();
        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, titles);
        authorlist.setAdapter(arrayAdapter);

        bran.clear();

        if (c.moveToFirst()) {
            do {
                author auth = new author();
                auth.id = c.getString(idIndex);
                auth.name = c.getString(nameIndex);
                bran.add(auth);

                titles.add(c.getString(idIndex) + " \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + c.getString(nameIndex));

            } while (c.moveToNext());

            arrayAdapter.notifyDataSetChanged();
            authorlist.invalidateViews();
        }

        authorlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                author auth = bran.get(position);
                Intent i = new Intent(getApplicationContext(), editauthor.class);
                i.putExtra("BOOK_ID", auth.id);
                i.putExtra("AUTHOR_NAME", auth.name);
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
