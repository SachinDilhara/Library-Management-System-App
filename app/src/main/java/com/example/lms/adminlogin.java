package com.example.lms;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Toast;

public class adminlogin extends AppCompatActivity {
    EditText username;
    EditText Password;
    Button loginbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_adminlogin);

        username = findViewById(R.id.username);
        Password = findViewById(R.id.password);
        loginbutton = findViewById(R.id.loginbutton);

        loginbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (username.getText().toString().equals("admin") && Password.getText().toString().equals("1234")) {
                    Toast.makeText(adminlogin.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(adminlogin.this, home.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(adminlogin.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}