package com.example.sleep.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.sleep.R;

public class seventh_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seventh1);


        FrameLayout forest = findViewById(R.id.forest);
        forest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(seventh_1.this, seventh_2.class);
                startActivity(intent);
            }
        });

        TextView breath = findViewById(R.id.breath);
        breath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(seventh_1.this, sixth.class);
                startActivity(intent);
            }
        });

        ImageView moondefault = findViewById(R.id.moon_default);
        moondefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(seventh_1.this, fourth_1.class);
                startActivity(intent);
            }
        });

        ImageView chartBoldDefault = findViewById(R.id.chart_bold_default);
        chartBoldDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(seventh_1.this, fifth.class);
                startActivity(intent);
            }
        });

        ImageView profiledefault = findViewById(R.id.profile_default);
        profiledefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(seventh_1.this, eighth.class);
                startActivity(intent);
            }
        });
    }
}