package com.example.sleep.Screens

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sleep.Class.User
import com.example.sleep.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.Calendar


class second : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Инициализация Firebase
        firebaseAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference("users")

        val arrowlargeleft = findViewById<ImageView>(R.id.arrow_large_left)
        arrowlargeleft.setOnClickListener {
            val intent = Intent(this@second, Login::class.java)
            startActivity(intent)
        }

        val continueButton = findViewById<Button>(R.id.continueButton)
        val emailEditText = findViewById<EditText>(R.id.etEmail)
        val passwordEditText = findViewById<EditText>(R.id.etPassword)
        val nameEditText = findViewById<EditText>(R.id.etName)
        val birhdayDatePicker = findViewById<DatePicker>(R.id.datePicker)

        val calendar = Calendar.getInstance()


        calendar.set(1968, 0, 1)
        val minDate = calendar.timeInMillis


        calendar.set(2014, 11, 31)
        val maxDate = calendar.timeInMillis

        birhdayDatePicker.minDate = minDate
        birhdayDatePicker.maxDate = maxDate

        continueButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val birthday = getBirthday(birhdayDatePicker)


            if (name.isEmpty()) {
                Toast.makeText(this, "Введите имя", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                Toast.makeText(this, "Введите email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Проверка на корректность email
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Введите правильный email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if (password.length < 6) {
                Toast.makeText(
                    this,
                    "Пароль должен содержать хотя бы 6 символов",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (birthday.isEmpty()) {
                Toast.makeText(this, "Выберите дату рождения", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            registerUser(name, email, password, birthday)
        }
    }


        private fun getBirthday(datePicker: DatePicker): String {
            val day = datePicker.dayOfMonth
            val month = datePicker.month + 1
            val year = datePicker.year
            return "$day/$month/$year"
        }

    private fun storeUserData(email: String?, uid: String?) {
        if (email != null && uid != null) {
            val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)
            val editor = sharedPref.edit()


            editor.putString("userId_$uid", uid)
            editor.putString("userEmail_$uid", email)


            firebaseAuth.currentUser?.getIdToken(true)?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val token = task.result?.token
                    editor.putString("authToken_$uid", token)
                } else {

                    Log.e("storeUserData", "Не удалось получить authToken", task.exception)
                }
                editor.apply()
            }
        }
    }

    private fun registerUser(name: String, email: String, password: String, birthday: String,) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    user?.let {
                        val uid = it.uid
                        saveUserToDatabase(uid, name, email,birthday)
                        storeUserData(user.email, user.uid)
                    }
                } else {
                    Log.e("FirebaseAuth", "Registration failed", task.exception)
                    Toast.makeText(
                        this,
                        "Ошибка регистрации: ${task.exception?.localizedMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun saveUserToDatabase(uid: String, name: String, email: String,birthday: String) {
        val user = User(uid, name, email,birthday)
        database.child(uid).setValue(user)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("FirebaseDatabase", "User data saved successfully")
                    Toast.makeText(this, "Успешно!", Toast.LENGTH_SHORT).show()

                    val intent = Intent(this@second, ThirdActivity::class.java)
                    startActivity(intent)
                } else {
                    Log.e("FirebaseDatabase", "Failed to save user data", task.exception)
                    Toast.makeText(this, "Ошибка сохранения данных", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
