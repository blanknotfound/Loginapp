package com.example.myfirstandroidapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.loginhp)
        auth = FirebaseAuth.getInstance()
        val reg = findViewById<Button>(R.id.register)
        reg.setOnClickListener {
//            Toast.makeText(this,"user registration started",Toast.LENGTH_SHORT).show()
            val i = Intent(this, Register1::class.java)
            startActivity(i)
        }
        val tes = findViewById<Button>(R.id.tes)
        tes.setOnClickListener {
//            Toast.makeText(this,"user registration started",Toast.LENGTH_SHORT).show()
            val i = Intent(this, profile::class.java)
            startActivity(i)
        }
        val login = findViewById<Button>(R.id.login)
        login.setOnClickListener{
            if(validation())
            {
                val email= findViewById<EditText>(R.id.loginmail).text.toString()
                val password= findViewById<EditText>(R.id.password).text.toString()
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if(task.isSuccessful)
                        {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(this,"user logged in",Toast.LENGTH_SHORT).show()
                        }
                        else
                        {
                            Toast.makeText(this,"Enter Correct details",Toast.LENGTH_SHORT).show()
                        }
                    }
            }
            else
            {
                Toast.makeText(this,"Enter the login details",Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun validation() :Boolean
    {
        val email= findViewById<EditText>(R.id.loginmail).text.toString()
        val password= findViewById<EditText>(R.id.password).text.toString()
        if(email.trim{it<=' '}.isNotEmpty()
            && password.trim{it<=' '}.isNotEmpty())
        {
            return true
        }
        return false
    }
}

