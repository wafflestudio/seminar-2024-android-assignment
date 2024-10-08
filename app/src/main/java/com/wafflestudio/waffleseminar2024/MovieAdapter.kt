import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.wafflestudio.waffleseminar2024.Movie
import com.wafflestudio.waffleseminar2024.R


class MovieAdapter(
    private var movieList: List<Movie>
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun getItemCount(): Int = movieList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie)
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val movieTitle: TextView = itemView.findViewById(R.id.movieTitle)
        private val moviePoster: ImageView = itemView.findViewById(R.id.moviePoster)

        fun bind(movie: Movie) {
            movieTitle.text = movie.title
            moviePoster.load("https://image.tmdb.org/t/p/w185${movie.poster_path}")
        }
    }

    // 영화 목록 갱신 메서드
    fun updateMovies(newMovies: List<Movie>) {
        val diffCallback = MovieDiffCallback(movieList, newMovies)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        movieList = newMovies
        diffResult.dispatchUpdatesTo(this) // 애니메이션 적용
    }

    // DiffUtil 콜백 정의
    class MovieDiffCallback(
        private val oldList: List<Movie>,
        private val newList: List<Movie>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id // 동일한 아이템인지 비교
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition] // 내용이 동일한지 비교
        }
    }
}
