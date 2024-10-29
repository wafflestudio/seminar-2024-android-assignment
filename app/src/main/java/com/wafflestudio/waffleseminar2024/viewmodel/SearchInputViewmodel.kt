package com.wafflestudio.waffleseminar2024.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SearchInputViewmodel(application: Application) : AndroidViewModel(application) {
    private val _searchTerms = MutableLiveData<List<String>>()
    val searchTerms: LiveData<List<String>> get() = _searchTerms

    private val sharedPreferences = application.getSharedPreferences("search_terms", Context.MODE_PRIVATE)

    init {
        loadSearchTerms()
    }

    private fun loadSearchTerms() {
        val json = sharedPreferences.getString("terms_json", "[]")
        val terms: List<String> = Gson().fromJson(json, object : TypeToken<List<String>>() {}.type)
        _searchTerms.value = terms
    }

    fun saveSearchTermsList(terms: List<String>) {
        val editor = sharedPreferences.edit()
        val json = Gson().toJson(terms)
        editor.putString("terms_json", json)
        editor.apply()
    }

    fun saveSearchTerm(term: String) {
        val currentTerms = getSearchTermsList().toMutableList()
        currentTerms.remove(term) // 중복된 경우 제거
        currentTerms.add(0, term) // 최신 검색어를 맨 앞에 추가
        _searchTerms.value = currentTerms
        saveSearchTermsList(currentTerms) // 업데이트된 리스트 저장
    }

    fun getSearchTermsList(): List<String> {
        val json = sharedPreferences.getString("terms_json", "[]")
        return Gson().fromJson(json, object : TypeToken<List<String>>() {}.type)
    }

}