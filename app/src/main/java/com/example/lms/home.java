package com.example.lms;

import android.os.Bundle;
import android.view.View;
import android.content.Intent; // Import for Intent class

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.cardview.widget.CardView; // Import for CardView

public class home extends AppCompatActivity {

    private CardView bookManagementCardView; // Descriptive variable names
    private CardView memberManagementCardView;
    private CardView branchManagementCardView;
    private CardView publisherManagementCardView;
    private CardView authorManagementCardView;

    private CardView signOutCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        bookManagementCardView = findViewById(R.id.bookcard);
        memberManagementCardView = findViewById(R.id.membercard);
        branchManagementCardView = findViewById(R.id.branchcard);
        publisherManagementCardView = findViewById(R.id.publishercard);
        authorManagementCardView = findViewById(R.id.authorcard);
        signOutCardView = findViewById(R.id.signoutcard);

        // Click listener for Book Management CardView (existing logic)
        bookManagementCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this, bookmanagement.class);
                startActivity(intent);
            }
        });

        // Click listener for Member Management CardView
        memberManagementCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this, membermanagement.class);
                startActivity(intent);
            }
        });

        // Click listener for Branch Management CardView
        branchManagementCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this, branchmanagement.class);
                startActivity(intent);
            }
        });

        publisherManagementCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this, publishermanagement.class);
                startActivity(intent);
            }
        });

        authorManagementCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this, authormanagement.class);
                startActivity(intent);
            }
        });

        signOutCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this, memberhome.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
