package com.example.sleep.Screens;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TimePicker;
import java.util.Locale;
import android.content.Intent;
import android.widget.ImageView;

import com.example.sleep.R;

public class sixth extends AppCompatActivity {
    private TextView textMinutes;
    private int minutes = 2;
    private EditText edittextalarm;
    private TimePicker timePickeralarm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);

        ImageView arrowlargeleft = findViewById(R.id.arrow_large_left);
        arrowlargeleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sixth.this, seventh_1.class);
                startActivity(intent);
            }
        });



        edittextalarm = findViewById(R.id.edittextalarm);
        timePickeralarm = findViewById(R.id.timePickeralarm);
        edittextalarm.setInputType(InputType.TYPE_NULL);

        ImageButton buttonMinus = findViewById(R.id.button_minus);
        ImageButton buttonPlus = findViewById(R.id.button_plus);
        textMinutes = findViewById(R.id.text_minutes);

        updateMinutesText();

        buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (minutes > 1) {
                    minutes--;
                } else {
                    minutes = 59; // Если минуты = 1, устанавливаем 59
                }
                updateMinutesText();
            }
        });

        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (minutes < 59) {
                    minutes++;
                } else {
                    minutes = 1; // Если минуты = 59, устанавливаем 1
                }
                updateMinutesText();
            }
        });

        //TimePicker
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
    private void updateMinutesText() {
        textMinutes.setText(minutes + " минуты");
    }
}