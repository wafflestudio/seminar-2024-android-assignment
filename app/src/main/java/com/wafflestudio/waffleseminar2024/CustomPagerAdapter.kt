package com.wafflestudio.waffleseminar2024

import android.content.Intent
import android.net.Uri
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import com.wafflestudio.waffleseminar2024.databinding.ActivityUserInformationBinding
import com.wafflestudio.waffleseminar2024.databinding.AppPageBinding
import com.wafflestudio.waffleseminar2024.databinding.GamePageBinding
import com.wafflestudio.waffleseminar2024.databinding.SearchPageBinding

class CustomPagerAdapter(private val workspaceUrl: String) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val GAME_PAGE = 0
    private val APP_PAGE = 1
    private val SEARCH_PAGE = 2
    private val PROFILE_PAGE = 3

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            GAME_PAGE -> {
                val binding = GamePageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                GameViewHolder(binding)
            }
            APP_PAGE -> {
                val binding = AppPageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                AppViewHolder(binding)
            }
            SEARCH_PAGE -> {
                val binding = SearchPageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                SearchViewHolder(binding, SearchViewModel())
            }
            PROFILE_PAGE -> {
                val binding = ActivityUserInformationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ProfileViewHolder(binding, workspaceUrl)
            }
            else -> throw IllegalArgumentException("Invalid page type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // 각 ViewHolder에 따른 작업을 추가할 수 있습니다.
    }

    override fun getItemCount(): Int {
        return 4 // 페이지 수
    }

    override fun getItemViewType(position: Int): Int {
        return position // 위치에 따른 페이지 타입 반환
    }

    // 각 페이지에 대한 ViewHolder 정의
    class GameViewHolder(binding: GamePageBinding) : RecyclerView.ViewHolder(binding.root)
    class AppViewHolder(binding: AppPageBinding) : RecyclerView.ViewHolder(binding.root)
    class SearchViewHolder(private val binding: SearchPageBinding, private val viewModel: SearchViewModel) : RecyclerView.ViewHolder(binding.root) {

        init {
            setupRecyclerView()
        }

        private fun setupRecyclerView() {
            val adapter = SearchButtonGridAdapter()
            binding.recyclerView.apply {
                layoutManager = GridLayoutManager(binding.root.context, 2) // 2*6 그리드 설정
                this.adapter = adapter
            }

            // ViewModel의 LiveData에서 데이터를 직접 가져와서 RecyclerView에 전달
            val data = viewModel.buttonData.value
            if (data != null) {
                adapter.submitList(data)
            }
        }
    }

    class ProfileViewHolder(private val binding: ActivityUserInformationBinding, workspaceUrl: String) : RecyclerView.ViewHolder(binding.root) {

        init {
            addToolbarOption()
            addWorkspaceUrl(workspaceUrl)
            addGithubLink()
        }

        private fun addToolbarOption() {
            binding.toolbarUserInformation.apply {
                title = "프로필"
                setNavigationIcon(R.drawable.baseline_arrow_back_ios_new_24)
                setNavigationOnClickListener { /* 뒤로가기 로직 추가 가능 */ }
            }
        }

        private fun addWorkspaceUrl(workspaceUrl: String) {
            binding.slackWorkspaceValue.text = workspaceUrl
        }

        private fun addGithubLink() {
            val text = "hjlim7831"
            val spannableString = SpannableString(text)

            val clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: android.view.View) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/hjlim7831"))
                    widget.context.startActivity(intent)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.isUnderlineText = false
                }
            }

            spannableString.setSpan(clickableSpan, 0, text.length, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
            binding.githubValue.text = spannableString
            binding.githubValue.movementMethod = LinkMovementMethod.getInstance()
        }
    }
}
