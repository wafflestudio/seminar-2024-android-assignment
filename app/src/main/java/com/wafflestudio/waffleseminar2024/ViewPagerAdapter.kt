package com.wafflestudio.waffleseminar2024

import MovieAdapter
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson

class ViewPagerAdapter(private val context: Context) : RecyclerView.Adapter<ViewPagerAdapter.PageViewHolder>() {
    // 각 페이지 레이아웃의 ID 배열
    private val layouts = listOf(R.layout.tab_one, R.layout.tab_two, R.layout.tab_three, R.layout.activity_user_information)
    private var isGenreListVisible = true
    private var movieAdapter: MovieAdapter? = null

    // viewHolder
    inner class PageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        // 각 레이아웃을 inflate하여 ViewHolder 생성
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return PageViewHolder(view)

    }
    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        // position이 검색 탭일 때만 추가 UI 설정
        if (position == 2) {
            val recyclerView = holder.itemView.findViewById<RecyclerView>(R.id.recyclerViewGenres)
            val searchView = holder.itemView.findViewById<SearchView>(R.id.searchView)
            val toolbar = holder.itemView.findViewById<Toolbar>(R.id.toolbar)
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back)

            recyclerView.layoutManager = GridLayoutManager(context, 2)

            // 기본 장르 목록 어댑터 설정
            val adapter = GenreAdapter(genreList.genres,items){ genreId ->
                val filteredMovies = movieList.movies.filter { movie ->
                    genreId in movie.genre_ids
                }
                isGenreListVisible = false
                movieAdapter?.updateMovies(filteredMovies) ?: setMovieAdapter(recyclerView, filteredMovies)
            }
            toolbar.setNavigationOnClickListener{
                isGenreListVisible = true
                setGenreAdapter(recyclerView, adapter)
            }
            recyclerView.layoutManager = GridLayoutManager(context, 2)
            recyclerView.adapter = adapter

            // 검색어 입력 이벤트 처리
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.d("SearchTab", "Search query submitted: $query")
//                    adapter.showAllMovies(movieList.movies)
                    (recyclerView.layoutManager as GridLayoutManager).spanCount = 3
                    // val genreId = genreList.genres.find { genre -> genre.name.equals(query, ignoreCase = true) }?.id
                    if(!query.isNullOrEmpty()){
                        val filteredMovies = movieList.movies.filter{ movie->
                            movie.title.contains(query, ignoreCase = true)
                        }
                        isGenreListVisible = false
                        movieAdapter?.updateMovies(filteredMovies) ?:setMovieAdapter(recyclerView, filteredMovies)
                    }
                    else{
                        isGenreListVisible = false
                        movieAdapter?.updateMovies(movieList.movies) ?:setMovieAdapter(recyclerView, movieList.movies)

                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText.isNullOrEmpty()) {
                        // 검색어가 없을 때는 장르 목록을 보여줌
                        isGenreListVisible = true
                        setGenreAdapter(recyclerView, adapter)
                    }
                    return true
                }
            })

            searchView.setOnCloseListener {
                // SearchView를 닫으면 장르 목록으로 전환
                isGenreListVisible = true
                setGenreAdapter(recyclerView, adapter)
                false
            }

            // 초기 UI: 장르 목록 보여주기
            searchView.clearFocus()
        }
    }

    // 장르 어댑터 설정 메서드
    private fun setGenreAdapter(recyclerView: RecyclerView, adapter: GenreAdapter) {
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter
        adapter.showGenres()
    }

    // 영화 어댑터 설정 메서드
    private fun setMovieAdapter(recyclerView: RecyclerView, filteredMovies: List<Movie>) {
        val movieAdapter = MovieAdapter(filteredMovies)
        Log.d("movie","here")
        recyclerView.layoutManager = GridLayoutManager(context, 3)
        recyclerView.adapter = movieAdapter
    }


    override fun getItemCount(): Int = layouts.size

    // ViewType으로 각 페이지의 레이아웃 ID를 반환
    override fun getItemViewType(position: Int): Int = layouts[position]
}