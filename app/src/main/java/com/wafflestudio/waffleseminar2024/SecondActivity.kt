package com.wafflestudio.waffleseminar2024

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.log

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)

        val toGithub = findViewById<Button>(R.id.github_button)
        toGithub.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/KMSstudio"))
            startActivity(intent)
        }

        val intent = intent
        val url_string = intent.getStringExtra("url")
        val workspaceUrl = findViewById<EditText>(R.id.workspace_url)
        workspaceUrl.setText(url_string ?: "Your workspace empty!")
    }
}