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

public class authormanagement extends AppCompatActivity {

    private CardView addNewAuthorCardView;
    private CardView viewAuthorCardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_authormanagement);

        addNewAuthorCardView = findViewById(R.id.addauthorcard);
        viewAuthorCardView = findViewById(R.id.viewauthorcard);


        // Click listener for Book Management CardView (existing logic)
        addNewAuthorCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(authormanagement.this, addauthor.class);
                startActivity(intent);
            }
        });

        // Click listener for Book Management CardView (existing logic)
        viewAuthorCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(authormanagement.this, viewauthor.class);
                startActivity(intent);
            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.authormanagement), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}