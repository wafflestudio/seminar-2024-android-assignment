package com.wafflestudio.waffleseminar2024.viewPagerFragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.wafflestudio.waffleseminar2024.R
import com.wafflestudio.waffleseminar2024.databinding.ActivityUserInformationBinding
import com.wafflestudio.waffleseminar2024.databinding.FragmentSearchBinding

class UserInformationFragment : Fragment() {

    private var _binding: ActivityUserInformationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActivityUserInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val slackWorkspaceUrl = arguments?.getString("slackWorkspaceUrl").toString()

        addWorkspaceUrl(slackWorkspaceUrl)
        addToolbarOption()
        addGithubLink()

    }

    private fun addWorkspaceUrl(workspaceUrl: String) {
        val slackWorkspaceValueView: TextView = binding.slackWorkspaceValue
        slackWorkspaceValueView.text = workspaceUrl
    }

    private fun addToolbarOption() {
        val toolbar: Toolbar = binding.toolbarUserInformation
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)

        (activity as? AppCompatActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as? AppCompatActivity)?.supportActionBar?.title = "프로필"
        (activity as? AppCompatActivity)?.supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_new_24)
    }

    private fun addGithubLink() {
        val textView: TextView = binding.githubValue
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