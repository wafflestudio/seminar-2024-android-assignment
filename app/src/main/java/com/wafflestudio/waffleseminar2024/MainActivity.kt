package com.wafflestudio.waffleseminar2024

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val slackURL = findViewById<EditText>(R.id.WorkspaceURLInput)


        val myButton: Button = findViewById(R.id.ContinueButton)
        // 버튼 클릭 이벤트 처리
        myButton.setOnClickListener {
            // Log.d를 사용해 디버그 메시지 출력
            Log.d("ButtonClick", slackURL.text.toString())
            val intent = Intent(this, SecondActivity::class.java)
            val toSend = if (slackURL.text.toString() != "") slackURL.text.toString() else "workspace-url.slack.com"
            intent.putExtra("SlackURL", toSend)
            startActivity(intent)
        }

        val backButton = findViewById<ImageView>(R.id.BackButton)
        backButton.setOnClickListener{
            finish()
        }

    }

}