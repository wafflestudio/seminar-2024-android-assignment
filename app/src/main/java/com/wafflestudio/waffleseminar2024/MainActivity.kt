package com.wafflestudio.waffleseminar2024

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val button = findViewById<TextView>(R.id.myButton)
        val editTextUrl = findViewById<EditText>(R.id.urlEditText)

        button.setOnClickListener {
            val url = editTextUrl.text.toString()

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("url_key", url)
            startActivity(intent)
        }
    }
}