package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var etEmail : EditText
    private lateinit var etPassword : EditText
    private lateinit var loginBtn:Button
    private lateinit var registrationBtn:Button
    private lateinit var resetPasswordBtn : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        listeners()

    }

    private fun init(){
        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        loginBtn = findViewById(R.id.loginBtn)
        registrationBtn = findViewById(R.id.registrationBtn)
        resetPasswordBtn = findViewById(R.id.resetPasswordBtn)

    }

    private fun listeners(){

        registrationBtn.setOnClickListener {
            startActivity(Intent(this,RegistartionActivity::class.java))

        }
        resetPasswordBtn.setOnClickListener {
            startActivity(Intent(this,resetPasswordActivity::class.java))
        }


        loginBtn.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            if (email.isEmpty()|| password.isEmpty()){
                Toast.makeText(this, "Empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        startActivity(Intent(this,SecondActivity::class.java))
                        
                    }else{
                        Toast.makeText(this, "Something Went Wrong!", Toast.LENGTH_SHORT).show()
                    }
                }



        }

    }
}