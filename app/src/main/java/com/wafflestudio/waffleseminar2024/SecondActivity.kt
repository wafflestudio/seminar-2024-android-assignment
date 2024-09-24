package com.wafflestudio.waffleseminar2024

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.wafflestudio.waffleseminar2024.R

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val textView: TextView = findViewById(R.id.text_input)

        // 인텐트에서 데이터 추출
        val receivedText = intent.getStringExtra("EXTRA_TEXT")

        // TextView에 데이터 설정
        textView.text = "내 Slack 주소 : $receivedText"
        //"내 Slack 주소 : $receivedText" 이걸로 하면 잘됨.

        // 하이퍼링크 설정
        val hyperlinkTextView: TextView = findViewById(R.id.hyperlinkTextView)
        hyperlinkTextView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://github.com/LeeGeonMoo")
            }
            startActivity(intent)
        }
    }
}
