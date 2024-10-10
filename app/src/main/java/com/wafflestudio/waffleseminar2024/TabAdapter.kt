package com.wafflestudio.waffleseminar2024

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TabAdapter(
    private val activity: AppCompatActivity,
    private val searchViewModel: SearchViewModel,
    private val workspaceUrl: String?
) : RecyclerView.Adapter<TabAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_page, parent, false)
        Log.d("tab adapter", "create view holder")
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        when (position) {
            0 -> holder.bindText("page1")
            1 -> holder.bindText("page2")
            2 -> holder.bindSearchTab()
            3 -> holder.bindUserInformationTab()
        }
    }

    override fun getItemCount(): Int {
        return 4
    }

    inner class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = view.findViewById(R.id.textView)
        private val searchView: androidx.appcompat.widget.SearchView = view.findViewById(R.id.searchView)
        private val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        private val searchGenre: TextView = view.findViewById(R.id.searchGenre)
        private val viewStub = view.findViewById<ViewStub>(R.id.user_information_stub)

        fun bindText(text: String) {
            textView.visibility = View.VISIBLE
            textView.text = text
            searchView.visibility = View.GONE
            recyclerView.visibility = View.GONE
            searchGenre.visibility = View.GONE
        }

        fun bindSearchTab() {
            textView.visibility = View.GONE
            searchView.visibility = View.VISIBLE
            recyclerView.visibility = View.VISIBLE
            searchGenre.visibility = View.VISIBLE

            searchView.isIconified = false
            searchView.requestFocus()
            searchView.queryHint = "영화 및 시리즈 탐색"
            
            recyclerView.layoutManager = GridLayoutManager(itemView.context, 2)
            val adapter = GenreAdapter(listOf())
            recyclerView.adapter = adapter

            searchViewModel.genres.observe(activity) { genres ->
                Log.d("TabAdapter", "Genres received: ${genres.size}")
                adapter.updateGenres(genres)
                Log.d("TabAdapter", "Adapter updated with new genres")
            }

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.d("queryListener", "query: $query")
                    query?.let {
                        val genreId = getGenreIdByName(it)
                        if (genreId != null) {
                            searchViewModel.filterMoviesByGenre(genreId)
                        } else {
                            searchViewModel.searchMovies(it)
                        }
                        
                        searchGenre.text = "영화 & 시리즈"

                        recyclerView.layoutManager = GridLayoutManager(itemView.context, 3)
                        val movieAdapter = MovieAdapter(listOf())
                        recyclerView.adapter = movieAdapter

                        searchViewModel.movies.observe(activity) { movies ->
                            Log.d("TabAdapter", "Movies received: ${movies.size}")
                            movieAdapter.updateMovies(movies)
                            Log.d("TabAdapter", "Movie adapter updated with new movies")
                        }
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }

        fun getGenreIdByName(genreName: String): Int? {
            val genreMap = mapOf(
                "액션" to 28,
                "모험" to 12,
                "애니메이션" to 16,
                "코미디" to 35,
                "범죄" to 80,
                "다큐멘터리" to 99,
                "드라마" to 18,
                "가족" to 10751,
                "판타지" to 14,
                "역사" to 36,
                "공포" to 27,
                "음악" to 10402,
                "미스터리" to 9648,
                "로맨스" to 10749,
                "SF" to 878,
                "TV 영화" to 10770,
                "스릴러" to 53,
                "전쟁" to 10752,
                "서부" to 37
            )
            return genreMap[genreName]
        }


        fun bindUserInformationTab() {
            viewStub.visibility = View.VISIBLE

            val inflater = LayoutInflater.from(activity)
            val userInformationLayout = inflater.inflate(R.layout.activity_user_information, null)
            (itemView as ViewGroup).removeAllViews()
            (itemView as ViewGroup).addView(userInformationLayout)

            addToolbarOption(userInformationLayout)
            addWorkspaceUrl(userInformationLayout)
            addGithubLink(userInformationLayout)
        }

        private fun addToolbarOption(view: View) {
            val toolbar: Toolbar = view.findViewById(R.id.toolbarUserInformation)
            activity.setSupportActionBar(toolbar)
            activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            activity.supportActionBar?.title = "프로필"
            activity.supportActionBar?.setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_new_24)
        }

        private fun addWorkspaceUrl(view: View) {
            val slackWorkspaceValueView: TextView = view.findViewById(R.id.slackWorkspaceValue)
            slackWorkspaceValueView.text = workspaceUrl
        }

        private fun addGithubLink(view: View) {
            val textView: TextView = view.findViewById(R.id.githubValue)
            val text = "hjlim7831"
            val spannableString = SpannableString(text)

            val clickableSpan = object : ClickableSpan() {
                override fun onClick(widget: View) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/hjlim7831"))
                    activity.startActivity(intent)
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
}
