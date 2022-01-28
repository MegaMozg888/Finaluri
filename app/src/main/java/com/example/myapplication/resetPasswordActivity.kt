package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class resetPasswordActivity : AppCompatActivity() {
    private lateinit var emailEt : EditText
    private lateinit var resetBtn : Button
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        
        init()
        listeners()
        
    }
    
    private fun init(){
        emailEt = findViewById(R.id.emailEt)
        resetBtn = findViewById(R.id.resetBtn)
    }
    
    private fun listeners(){
        resetBtn.setOnClickListener { 
            val email = emailEt.text.toString()
            
            if (email.isEmpty()){
                Toast.makeText(this, "Empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance()
                .sendPasswordResetEmail(email)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "Reset Password Link Sent Successfully!", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Something Went Wrong!", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}