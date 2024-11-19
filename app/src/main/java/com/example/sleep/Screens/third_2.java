package com.example.sleep.Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.sleep.R;

import java.util.Locale;

public class third_2 extends AppCompatActivity {
    private EditText edittextweekdays;
    private TimePicker timePickerweekdays;
    private EditText edittextweekend;
    private TimePicker timePickerweekend;
    private LinearLayout layoutweekend;
    private static final int TIME_PICKER_HEIGHT = 0;
    private HorizontalScrollView horizontalScrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third2);

        ImageView arrowlargeleft = findViewById(R.id.arrow_large_left);
        arrowlargeleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(third_2.this, eighth.class);
                startActivity(intent);
            }
        });


        horizontalScrollView = findViewById(R.id.horizontalScrollView);

        findViewById(R.id.arrow_small_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollHorizontalScrollView(-1); // Прокрутка влево
            }
        });

        findViewById(R.id.arrow_small_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollHorizontalScrollView(1); // Прокрутка вправо
            }
        });
        //Timepickerweekend
        edittextweekdays = findViewById(R.id.edittextweekdays);
        timePickerweekdays = findViewById(R.id.timePickerweekdays);
        edittextweekend = findViewById(R.id.edittextweekend);
        timePickerweekend = findViewById(R.id.timePickerweekend);
        layoutweekend = findViewById(R.id.layoutweekend);

        // Disable keyboard input for both EditTexts
        edittextweekdays.setInputType(InputType.TYPE_NULL);
        edittextweekend.setInputType(InputType.TYPE_NULL);

        // Set onClickListener to toggle visibility for weekdays TimePicker
        edittextweekdays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timePickerweekdays.getVisibility() == View.GONE) {
                    timePickerweekdays.setVisibility(View.VISIBLE);
                    // Add margin to layoutweekend to move it down
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) layoutweekend.getLayoutParams();
                    params.topMargin = TIME_PICKER_HEIGHT;
                    layoutweekend.setLayoutParams(params);
                } else {
                    timePickerweekdays.setVisibility(View.GONE);
                    // Reset margin to layoutweekend
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) layoutweekend.getLayoutParams();
                    params.topMargin = 0;
                    layoutweekend.setLayoutParams(params);
                }
            }
        });

        // Set onClickListener to toggle visibility for weekend TimePicker
        edittextweekend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timePickerweekend.getVisibility() == View.GONE) {
                    timePickerweekend.setVisibility(View.VISIBLE);
                } else {
                    timePickerweekend.setVisibility(View.GONE);
                }
            }
        });

        // Set onTimeChangedListener to update edittextweekdays
        timePickerweekdays.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                String amPm = (hourOfDay >= 12) ? "PM" : "AM";
                int hour12Format = (hourOfDay == 0) ? 12 : (hourOfDay > 12) ? hourOfDay - 12 : hourOfDay;
                String time = String.format(Locale.getDefault(), "В будни: %02d:%02d %s", hour12Format, minute, amPm);
                edittextweekdays.setText(time);
            }
        });

        // Set onTimeChangedListener to update edittextweekend
        timePickerweekend.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                String amPm = (hourOfDay >= 12) ? "PM" : "AM";
                int hour12Format = (hourOfDay == 0) ? 12 : (hourOfDay > 12) ? hourOfDay - 12 : hourOfDay;
                String time = String.format(Locale.getDefault(), "В выходные: %02d:%02d %s", hour12Format, minute, amPm);
                edittextweekend.setText(time);
            }
        });

        // Initially hide the timePickers
        timePickerweekdays.setVisibility(View.GONE);
        timePickerweekend.setVisibility(View.GONE);
    }
    private void scrollHorizontalScrollView(int direction) {
        int scrollAmount = (int) (horizontalScrollView.getWidth() * 0.8); // Измените величину прокрутки по вашему усмотрению
        if (direction == -1) {
            horizontalScrollView.smoothScrollBy(-scrollAmount, 0);
        } else {
            horizontalScrollView.smoothScrollBy(scrollAmount, 0);
        }
    }
}