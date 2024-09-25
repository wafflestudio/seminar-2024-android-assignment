package com.wafflestudio.waffleseminar2024

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.wafflestudio.waffleseminar2024.databinding.ActivityMainBinding
import com.wafflestudio.waffleseminar2024.databinding.ActivitySecond2Binding

class second_activity : AppCompatActivity() {
    lateinit var binding2: ActivitySecond2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding2 = ActivitySecond2Binding.inflate(layoutInflater)
        setContentView(binding2.root)
        val display_url = binding2.mainUrl
        val passed_url = intent.getStringExtra("slackurl")
        display_url.text = "(Entered URL: $passed_url)"

        val github_url = binding2.github
        github_url.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://github.com/wowsnu")  // 이동할 URL 설정
            startActivity(intent)
        }
    }
}