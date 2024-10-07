package com.wafflestudio.waffleseminar2024

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class SearchViewModel : ViewModel() {
    @Serializable
    data class Genre(
        val id: Int,
        val name: String
    )

    private val _buttonData = MutableLiveData<List<Genre>>()
    val buttonData: LiveData<List<Genre>> = _buttonData

    init {
        loadJsonData()
    }

    private fun parseUserList(jsonString: String): List<Genre> {
        return Json.decodeFromString(jsonString)
    }

    private fun loadJsonData() {

        val jsonArrayString = """
[
    {
      "id": 28,
      "name": "액션"
    },
    {
      "id": 12,
      "name": "모험"
    },
    {
      "id": 16,
      "name": "애니메이션"
    },
    {
      "id": 35,
      "name": "코미디"
    },
    {
      "id": 80,
      "name": "범죄"
    },
    {
      "id": 99,
      "name": "다큐멘터리"
    },
    {
      "id": 18,
      "name": "드라마"
    },
    {
      "id": 10751,
      "name": "가족"
    },
    {
      "id": 14,
      "name": "판타지"
    },
    {
      "id": 36,
      "name": "역사"
    },
    {
      "id": 27,
      "name": "공포"
    },
    {
      "id": 10402,
      "name": "음악"
    },
    {
      "id": 9648,
      "name": "미스터리"
    },
    {
      "id": 10749,
      "name": "로맨스"
    },
    {
      "id": 878,
      "name": "SF"
    },
    {
      "id": 10770,
      "name": "TV 영화"
    },
    {
      "id": 53,
      "name": "스릴러"
    },
    {
      "id": 10752,
      "name": "전쟁"
    },
    {
      "id": 37,
      "name": "서부"
    }
]
"""
        val genreList = parseUserList(jsonArrayString)
        _buttonData.value = genreList
    }
}
