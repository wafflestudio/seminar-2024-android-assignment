package com.wafflestudio.waffleseminar2024

import android.content.Intent
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val url = intent.getStringExtra("SlackURL")

        val slackURL = findViewById<TextView>(R.id.SlackURL)
        Log.d("string",url.toString())
        slackURL.text = url

        val backButton = findViewById<ImageView>(R.id.BackButton)
        backButton.setOnClickListener {
            finish()
        }


        val githubHyperLink = findViewById<TextView>(R.id.Github)
        githubHyperLink.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/qdrptd"))
            startActivity(intent)
        }

    }
}