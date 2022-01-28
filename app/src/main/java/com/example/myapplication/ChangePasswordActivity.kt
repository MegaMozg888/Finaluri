package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.FillEventHistory
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var newPass:EditText
    private lateinit var changePassBtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        init()
        updatePass()
    }

    private fun init(){
        newPass = findViewById(R.id.newPass)
        changePassBtn = findViewById(R.id.changePassBtn)

    }

    private fun updatePass(){
        changePassBtn.setOnClickListener {
            val newPassword = newPass.text.toString()

            if (newPassword.isEmpty()) {
                Toast.makeText(this, "Empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance()
                .currentUser
                ?.updatePassword(newPassword)
                ?.addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "Password Changed Successfully!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show()
                    }
                }

        }


    }

    }
