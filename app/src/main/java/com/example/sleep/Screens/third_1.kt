package com.example.sleep.Screens

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.sleep.R
import java.util.Locale

class ThirdActivity : AppCompatActivity() {
    private lateinit var edittextweekdays: EditText
    private lateinit var timePickerweekdays: TimePicker
    private lateinit var edittextweekend: EditText
    private lateinit var timePickerweekend: TimePicker
    private lateinit var layoutweekend: LinearLayout
    private lateinit var horizontalScrollView: HorizontalScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third1)

        // Initialize views
        val arrowlargeleft = findViewById<ImageView>(R.id.arrow_large_left)
        val bottomButton = findViewById<Button>(R.id.bottom_button)
        horizontalScrollView = findViewById(R.id.horizontalScrollView)
        edittextweekdays = findViewById(R.id.edittextweekdays)
        timePickerweekdays = findViewById(R.id.timePickerweekdays)
        edittextweekend = findViewById(R.id.edittextweekend)
        timePickerweekend = findViewById(R.id.timePickerweekend)
        layoutweekend = findViewById(R.id.layoutweekend)

        val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)
        val userUid = sharedPref.getString("user_uid", null)
        if (userUid != null) {

        } else {
            Log.e("eighth", "UID не найден в SharedPreferences")
        }
        // Arrow navigation
        arrowlargeleft.setOnClickListener {
            startActivity(Intent(this, second::class.java))
        }

        bottomButton.setOnClickListener {
            startActivity(Intent(this, eighth::class.java))
        }

        // Scroll view navigation
        findViewById<View>(R.id.arrow_small_left).setOnClickListener {
            scrollHorizontalScrollView(-1)
        }

        findViewById<View>(R.id.arrow_small_right).setOnClickListener {
            scrollHorizontalScrollView(1)
        }

        // Disable keyboard input for both EditTexts
        edittextweekdays.inputType = InputType.TYPE_NULL
        edittextweekend.inputType = InputType.TYPE_NULL

        // Toggle visibility for weekdays TimePicker
        edittextweekdays.setOnClickListener {
            toggleTimePickerVisibility(timePickerweekdays, layoutweekend)
        }

        // Toggle visibility for weekend TimePicker
        edittextweekend.setOnClickListener {
            timePickerweekend.visibility = if (timePickerweekend.visibility == View.GONE) View.VISIBLE else View.GONE
        }

        // Update edittextweekdays on time change
        timePickerweekdays.setOnTimeChangedListener { _, hourOfDay, minute ->
            val time = formatTime(hourOfDay, minute, "В будни")
            edittextweekdays.setText(time)
        }

        // Update edittextweekend on time change
        timePickerweekend.setOnTimeChangedListener { _, hourOfDay, minute ->
            val time = formatTime(hourOfDay, minute, "В выходные")
            edittextweekend.setText(time)
        }

        // Initially hide the timePickers
        timePickerweekdays.visibility = View.GONE
        timePickerweekend.visibility = View.GONE
    }

    private fun scrollHorizontalScrollView(direction: Int) {
        val scrollAmount = (horizontalScrollView.width * 0.8).toInt()
        horizontalScrollView.smoothScrollBy(if (direction == -1) -scrollAmount else scrollAmount, 0)
    }

    private fun toggleTimePickerVisibility(timePicker: TimePicker, layout: LinearLayout) {
        if (timePicker.visibility == View.GONE) {
            timePicker.visibility = View.VISIBLE
            val params = layout.layoutParams as LinearLayout.LayoutParams
            params.topMargin = TIME_PICKER_HEIGHT
            layout.layoutParams = params
        } else {
            timePicker.visibility = View.GONE
            val params = layout.layoutParams as LinearLayout.LayoutParams
            params.topMargin = 0
            layout.layoutParams = params
        }
    }

    private fun formatTime(hourOfDay: Int, minute: Int, label: String): String {
        val amPm = if (hourOfDay >= 12) "PM" else "AM"
        val hour12Format = when {
            hourOfDay == 0 -> 12
            hourOfDay > 12 -> hourOfDay - 12
            else -> hourOfDay
        }
        return String.format(Locale.getDefault(), "%s: %02d:%02d %s", label, hour12Format, minute, amPm)
    }

    companion object {
        private const val TIME_PICKER_HEIGHT = 0
    }
}
