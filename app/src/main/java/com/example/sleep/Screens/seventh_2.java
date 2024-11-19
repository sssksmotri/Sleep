package com.example.sleep.Screens;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sleep.R;

public class seventh_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seventh2);

        ImageView arrowlargeleft = findViewById(R.id.arrow_large_left);
        arrowlargeleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(seventh_2.this, seventh_1.class);
                startActivity(intent);
            }
        });


        ImageView stopImage = findViewById(R.id.stop_image);
        stopImage.setOnClickListener(new View.OnClickListener() {
            boolean isPlaying = false;

            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    stopImage.setImageResource(R.drawable.play);
                } else {
                    stopImage.setImageResource(R.drawable.stop);
                }
                isPlaying = !isPlaying;
            }
        });
    }
}