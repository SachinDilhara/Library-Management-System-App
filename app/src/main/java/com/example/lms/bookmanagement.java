package com.example.lms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class bookmanagement extends AppCompatActivity {

    private CardView addNewBookCardView;
    private CardView viewBookCardView;
    private CardView MakeaLoanCardView;
    private CardView BookCopyCardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bookmanagement);

        addNewBookCardView = findViewById(R.id.addbookcard);
        viewBookCardView = findViewById(R.id.viewbookcard);
        MakeaLoanCardView = findViewById(R.id.makeloancard);
        BookCopyCardView = findViewById(R.id.bookcopycard);


        // Click listener for Book Management CardView (existing logic)
        addNewBookCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bookmanagement.this, addbook.class);
                startActivity(intent);
            }
        });

        // Click listener for Book Management CardView (existing logic)
        viewBookCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bookmanagement.this, viewbook.class);
                startActivity(intent);
            }
        });

        // Click listener for Book Management CardView (existing logic)
        MakeaLoanCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bookmanagement.this, loanmanagement.class);
                startActivity(intent);
            }
        });

        BookCopyCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bookmanagement.this, bookcopymanagement.class);
                startActivity(intent);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.bookmanagement), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}