package com.wafflestudio.waffleseminar2024

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.wafflestudio.waffleseminar2024.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val url = binding.urlInput
        val button = binding.button

        url.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                val input = s.toString()
                button.isEnabled = input.isNotEmpty() && input.contains(".slack.com")
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        val buttonGotoSecond = binding.button
        buttonGotoSecond.setOnClickListener {
            val intent = Intent(this, second_activity::class.java)
            startActivity(intent)
        }
        }

    }
