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

public class memberbook extends AppCompatActivity {

    ListView booklist;
    ArrayList<String> titles = new ArrayList<String>();
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_memberbook);

        SQLiteDatabase db = openOrCreateDatabase("bookmanagementdb", Context.MODE_PRIVATE, null);

        booklist = findViewById(R.id.booklist);
        // Updated query to fetch book details along with author names
        final Cursor c = db.rawQuery("SELECT book.BOOK_ID, book.TITLE, book.PUBLISHER_NAME, GROUP_CONCAT(Book_Author.AUTHOR_NAME, ', ') AS authors " +
                "FROM book " +
                "LEFT JOIN Book_Author ON book.BOOK_ID = Book_Author.BOOK_ID " +
                "GROUP BY book.BOOK_ID", null);

        int id = c.getColumnIndex("BOOK_ID");
        int name = c.getColumnIndex("TITLE");
        int publisher = c.getColumnIndex("PUBLISHER_NAME");
        int authors = c.getColumnIndex("authors");

        titles.clear();
        arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, titles);

        booklist.setAdapter(arrayAdapter);

        final ArrayList<book> boo = new ArrayList<book>();

        if(c.moveToFirst()) {

            do {
                book book = new book();
                book.id = c.getString(id);
                book.name = c.getString(name);
                book.publisher = c.getString(publisher);
                // Get the concatenated author names from the cursor
                String authorNames = c.getString(authors);

                boo.add(book);
                // Display book details along with author names
                titles.add(String.format("%-10s%-20s%-20s%s", c.getString(id), c.getString(name), c.getString(publisher), authorNames));
            } while (c.moveToNext());

            arrayAdapter.notifyDataSetChanged();
            booklist.invalidateViews();
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
