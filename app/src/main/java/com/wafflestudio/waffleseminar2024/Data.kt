import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class Genre(
    val id: Int,
    val name: String,
)

val GenreList: List<Genre> =
    listOf(
        Genre(28, "액션"),
        Genre(12, "모험"),
        Genre(16, "애니메이션"),
        Genre(35, "코미디"),
        Genre(80, "범죄"),
        Genre(99, "다큐멘터리"),
        Genre(18, "드라마"),
        Genre(10751, "가족"),
        Genre(14, "판타지"),
        Genre(36, "역사"),
        Genre(27, "공포"),
        Genre(10402, "음악"),
        Genre(9648, "미스터리"),
        Genre(10749, "로맨스"),
        Genre(878, "SF"),
        Genre(10770, "TV 영화"),
        Genre(53, "스릴러"),
        Genre(10752, "전쟁"),
        Genre(37, "서부")
    )