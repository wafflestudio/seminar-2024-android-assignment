package com.wafflestudio.waffleseminar2024

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val email = intent.getStringExtra("inputText")
        val displayTextView = findViewById<TextView>(R.id.displayTextView)
        displayTextView.text = email
        // 버튼 클릭 시 MainActivity로 복귀
        val button = findViewById<Button>(R.id.button_go_back)
        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val githubButton = findViewById<TextView>(R.id.github_button)
        githubButton.setOnClickListener { // GitHub 페이지 URL
            val url = "https://github.com/junghyunpark2001"
            // 웹 브라우저 열기
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(url))
            startActivity(intent)
        }

    }
}
