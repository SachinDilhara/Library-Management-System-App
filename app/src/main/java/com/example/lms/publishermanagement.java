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

public class publishermanagement extends AppCompatActivity {

    private CardView addNewpublisherCardView;
    private CardView viewpublisherCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_publishermanagement);

        addNewpublisherCardView = findViewById(R.id.addpublishercard);
        viewpublisherCardView = findViewById(R.id.viewpublishercard);

        // Click listener for Book Management CardView (existing logic)
        addNewpublisherCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(publishermanagement.this, addpublisher.class);
                startActivity(intent);
            }
        });

        // Click listener for Book Management CardView (existing logic)
        viewpublisherCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(publishermanagement.this, viewpublisher.class);
                startActivity(intent);
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.publishermanagement), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}