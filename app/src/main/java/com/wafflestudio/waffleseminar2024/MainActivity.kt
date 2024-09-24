package com.wafflestudio.waffleseminar2024

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val url = findViewById<EditText>(R.id.workspace_url)
        val cButton = findViewById<Button>(R.id.continue_button)
        cButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("url", url.text.toString())
            startActivity(intent)
        }
    }
}