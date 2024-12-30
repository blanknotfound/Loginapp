package com.example.myfirstandroidapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class Register1 : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()
        val back = findViewById<Button>(R.id.bac)
        back.setOnClickListener {
//            Toast.makeText(this,"user registration started",Toast.LENGTH_SHORT).show()
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
        val sign = findViewById<Button>(R.id.signup)
        sign.setOnClickListener {
            if (validation()) {
                val email = findViewById<EditText>(R.id.email).text.toString()
                val name = findViewById<EditText>(R.id.regname).text.toString()
                val number = findViewById<EditText>(R.id.number).text.toString()
                val password = findViewById<EditText>(R.id.regpassword).text.toString()

                // Firebase Authentication Logic
                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(baseContext, "Authentication success.",
                                Toast.LENGTH_SHORT).show()
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Enter the registration details", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun validation(): Boolean {
        val email = findViewById<EditText>(R.id.email)
        val name = findViewById<EditText>(R.id.regname)
        val number = findViewById<EditText>(R.id.number)
        val password = findViewById<EditText>(R.id.regpassword)

        if (name.text.toString().isNotEmpty() &&
            number.text.toString().isNotEmpty() &&
            email.text.toString().isNotEmpty() &&
            password.text.toString().isNotEmpty()
        ) {
            return true
        }
        return false
    }
}