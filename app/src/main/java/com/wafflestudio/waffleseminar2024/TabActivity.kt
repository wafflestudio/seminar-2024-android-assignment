package com.wafflestudio.waffleseminar2024

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.wafflestudio.waffleseminar2024.databinding.ActivityTabBinding
import com.wafflestudio.waffleseminar2024.databinding.ActivityUserInformationBinding

class TabActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTabBinding
    private val userInformationBinding by lazy {
        ActivityUserInformationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTabBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //userInformationBinding = ActivityUserInformationBinding.inflate(layoutInflater)

        val viewPager: ViewPager2 = binding.viewPager
        val tabLayout: TabLayout = binding.tabLayout

        val items = listOf("Page 1", "Page 2", "Page 3", "Page 4")
        val tabIcons = listOf(
            R.drawable.ic_game,
            R.drawable.ic_app,
            R.drawable.ic_search,
            R.drawable.ic_book
        )
        val tabTexts = listOf("게임", "앱", "검색", "프로필")

        val adapter = TabAdapter(items, this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTexts[position]
            tab.setIcon(tabIcons[position])
        }.attach()
    }

    fun addToolbarOption() {
        val toolbar: Toolbar = userInformationBinding.toolbarUserInformation
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "프로필"
        supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_new_24)
    }

    fun addWorkspaceUrl() {
        val slackWorkspaceValueView: TextView = userInformationBinding.slackWorkspaceValue
        val workspaceUrl = intent.getStringExtra("WORKSPACE_URL")

        Log.d("TabActivity", "url: $workspaceUrl")
        slackWorkspaceValueView.text = workspaceUrl

    }

    fun addGithubLink() {
        val textView: TextView = userInformationBinding.githubValue
        val text = "hjlim7831"
        val spannableString = SpannableString(text)

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: android.view.View) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/hjlim7831"))
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.isUnderlineText = false
            }
        }

        spannableString.setSpan(clickableSpan, 0, text.length, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)

        textView.text = spannableString
        textView.movementMethod = LinkMovementMethod.getInstance()

    }
}