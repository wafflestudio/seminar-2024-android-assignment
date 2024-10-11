package com.wafflestudio.waffleseminar2024

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.wafflestudio.waffleseminar2024.databinding.ItemButtonBinding

class SearchButtonGridAdapter(
    private val viewModel: SearchViewModel,
    private val onGenreSelected: () -> Unit // 장르 선택 시 어댑터 스위치 콜백
) : RecyclerView.Adapter<SearchButtonGridAdapter.ButtonViewHolder>() {

    private var genreList: List<SearchViewModel.Genre> = emptyList()

    class ButtonViewHolder(private val binding: ItemButtonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: SearchViewModel.Genre, clickListener: (Int) -> Unit) {
            binding.button.text = genre.name
            binding.button.setOnClickListener {
                clickListener(genre.id) // 장르 ID 전달
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val binding = ItemButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ButtonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        holder.bind(genreList[position]) { genreId ->
            // 클릭된 장르 ID로 ViewModel의 필터링 메소드 호출
            viewModel.filterMoviesByGenre(genreId)
            onGenreSelected() // 어댑터 스위치 호출
        }
    }

    override fun getItemCount(): Int = genreList.size

    fun submitList(data: List<SearchViewModel.Genre>) {
        genreList = data
        notifyDataSetChanged()
    }
}


