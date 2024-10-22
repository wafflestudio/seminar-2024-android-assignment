package com.wafflestudio.waffleseminar2024

import android.content.Intent
import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
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
        addToolbarOption()
        addWorkspaceUrlEditTextOption()
        addContinueButtonOption()
    }

    private fun addToolbarOption() {
        val toolbar: Toolbar = findViewById(R.id.toolbarMain)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "로그인"
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_new_24)
    }

    private fun addWorkspaceUrlEditTextOption() {
        val editText: EditText = findViewById(R.id.workspaceUrl)
        val button: Button = findViewById(R.id.buttonContinue)
        editText.hint = "workspace-url.slack.com"
        editText.addTextChangedListener(WorkspaceUrlTextWatcher(editText, button))
    }

    private fun addContinueButtonOption() {
        val editText: EditText = findViewById(R.id.workspaceUrl)
        val button: Button = findViewById(R.id.buttonContinue)

        button.setOnClickListener {
            val workspaceUrl = editText.text.toString()
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("WORKSPACE_URL", workspaceUrl)
            startActivity(intent)
        }


    }
}