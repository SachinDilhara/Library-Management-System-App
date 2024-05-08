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

public class memberhome extends AppCompatActivity {

    private CardView memberBookCardView; // Descriptive variable names
    private CardView memberBranchCardView;
    private CardView memberlogCardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_memberhome);

        memberBookCardView = findViewById(R.id.memberbookcard);
        memberBranchCardView = findViewById(R.id.memberbranchcard);
        memberlogCardView = findViewById(R.id.memberloghcard);


        // Click listener for Book Management CardView (existing logic)
        memberBookCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(memberhome.this, memberbook.class);
                startActivity(intent);
            }
        });

        // Click listener for Member Management CardView
        memberBranchCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(memberhome.this, memberbranch.class);
                startActivity(intent);
            }
        });

        // Click listener for Branch Management CardView
        memberlogCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(memberhome.this, adminlogin.class);
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
