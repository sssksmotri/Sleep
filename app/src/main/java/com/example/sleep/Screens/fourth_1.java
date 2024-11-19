package com.example.sleep.Screens;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TimePicker;
import java.util.Locale;
import android.content.Intent;
import android.widget.ImageView;

import com.example.sleep.R;

public class fourth_1 extends AppCompatActivity {

    private EditText edittextalarm;
    private TimePicker timePickeralarm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth1);

        ImageView chartBoldDefault = findViewById(R.id.chart_bold_default);
        chartBoldDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fourth_1.this, fifth.class);
                startActivity(intent);
            }
        });

        ImageView lotusdefault = findViewById(R.id.lotus_default);
        lotusdefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fourth_1.this, seventh_1.class);
                startActivity(intent);
            }
        });

        ImageView profiledefault = findViewById(R.id.profile_default);
        profiledefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(fourth_1.this, eighth.class);
                startActivity(intent);
            }
        });

        edittextalarm = findViewById(R.id.edittextalarm);
        timePickeralarm = findViewById(R.id.timePickeralarm);
        edittextalarm.setInputType(InputType.TYPE_NULL);



        // Set onClickListener to toggle visibility for weekend TimePicker
        edittextalarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timePickeralarm.getVisibility() == View.GONE) {
                    timePickeralarm.setVisibility(View.VISIBLE);
                } else {
                    timePickeralarm.setVisibility(View.GONE);
                }
            }
        });



        // Set onTimeChangedListener to update edittextweekend
        timePickeralarm.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                String amPm = (hourOfDay >= 12) ? "PM" : "AM";
                int hour12Format = (hourOfDay == 0) ? 12 : (hourOfDay > 12) ? hourOfDay - 12 : hourOfDay;
                String time = String.format(Locale.getDefault(), "Будильник сработает: %02d:%02d %s", hour12Format, minute, amPm);
                edittextalarm.setText(time);
            }
        });

        timePickeralarm.setVisibility(View.GONE);
    }
}