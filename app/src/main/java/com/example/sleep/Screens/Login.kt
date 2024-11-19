package com.example.sleep.Screens

import android.content.Intent
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sleep.R
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.SignInButton

private lateinit var googleSignInClient: GoogleSignInClient
private lateinit var firebaseAuth: FirebaseAuth
private val RC_SIGN_IN = 1001

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        firebaseAuth = FirebaseAuth.getInstance()
        val emailEditText = findViewById<EditText>(R.id.logEmail)
        val passwordEditText = findViewById<EditText>(R.id.logPassword)
        val  btnGooglelog = findViewById<SignInButton>(R.id.sigin_google)
        val tvRegister = findViewById<TextView>(R.id.tvRegister)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        firebaseAuth = FirebaseAuth.getInstance()

        val sharedPref = getSharedPreferences("UserData", MODE_PRIVATE)
        val userUid = sharedPref.getString("user_uid", null)
        if (userUid != null) {
            navigateToNextScreen()
        } else {
            Log.e("eighth", "UID не найден в SharedPreferences")
        }
        btnLogin.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUserWithEmail(email, password)
            } else {
                Toast.makeText(this, "Пожалуйста, введите email и пароль", Toast.LENGTH_SHORT).show()
            }

        }


        tvRegister.setOnClickListener {
            val intent = Intent(
                this@Login,
                second::class.java
            )
            startActivity(intent)
        }

        btnGooglelog.setOnClickListener{
                val signInIntent = googleSignInClient.signInIntent
                startActivityForResult(signInIntent, RC_SIGN_IN)
        }

    }
    private fun firebaseAuthWithGoogle(idToken: String?) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    user?.let {
                        storeUserData(user.email, user.uid)
                    }
                    navigateToNextScreen()
                } else {
                    Log.e("FirebaseAuth", "Sign-in failed", task.exception)
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account.idToken)
            } catch (e: ApiException) {
                Log.e("GoogleSignIn", "Google sign-in failed", e)
            }
        }
    }

    private fun navigateToNextScreen() {
        val intent = Intent(this@Login, eighth::class.java)
        startActivity(intent)
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

    private fun loginUserWithEmail(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    user?.let {
                        storeUserData(user.email, user.uid)
                    }
                    navigateToNextScreen()
                } else {
                    Log.e("FirebaseAuth", "Sign-in failed", task.exception)
                    Toast.makeText(this, "Ошибка входа: ${task.exception?.localizedMessage}", Toast.LENGTH_SHORT).show()
                }
            }
    }

}