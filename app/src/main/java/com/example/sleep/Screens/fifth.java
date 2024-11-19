package com.example.sleep.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.sleep.R;

public class fifth extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth);

        ImageView moondefault = findViewById(R.id.moon_default);
        moondefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fifth.this, fourth_1.class);
                startActivity(intent);
            }
        });

        ImageView lotusdefault = findViewById(R.id.lotus_default);
        lotusdefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fifth.this, seventh_1.class);
                startActivity(intent);
            }
        });

        ImageView profiledefault = findViewById(R.id.profile_default);
        profiledefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fifth.this, eighth.class);
                startActivity(intent);
            }
        });
    }
}