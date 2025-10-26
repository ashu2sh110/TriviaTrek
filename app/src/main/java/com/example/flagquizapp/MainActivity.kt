package com.example.flagquizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.jvm.java
import com.example.flagquizapp.QuestionActivity
import com.example.flagquizapp.utils.Constants


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton: Button = findViewById(R.id.btnStart)
        val editTextName: EditText = findViewById(R.id.etName)
        startButton.setOnClickListener {
            if (!editTextName.text.isEmpty()) {
                Intent(this@MainActivity, QuestionActivity::class.java).also {
                    it.putExtra(Constants.USER_NAME, editTextName.text.toString())
                    startActivity(it)
                    finish()
                }
            } else {
                    Toast.makeText(this@MainActivity, "please enter your name", Toast.LENGTH_SHORT).show()
                }

            }
       
        }
    }