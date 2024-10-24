package com.example.apitest

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.preference.PreferenceManager
import com.example.apitest.databinding.ActivitySettingBinding
import com.google.firebase.firestore.FirebaseFirestore

class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding  // 뷰 바인딩 객체
    private lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Firestore 초기화
        firestore = FirebaseFirestore.getInstance()

        // 최근 검색 기록을 가져와서 SharedPreferences에 저장
        fetchRecentSearches()
        val shared = PreferenceManager.getDefaultSharedPreferences(this)

        val checkboxValue = shared.getBoolean("key_add_shortcut",false)
        val switchValue = shared.getBoolean("key_switch_on",false)
        val name = shared.getString("key_edit_name","")
        val start = shared.getString("key_edit_nameStart","")
        val destination = shared.getString("key_edit_nameDestination","")

        val selected = shared.getString("key_set_item","")

    }
    // Firestore에서 최근 검색 기록 가져오기
    private fun fetchRecentSearches() {
        firestore.collection("searchRecent")
            .orderBy("timestamp", com.google.firebase.firestore.Query.Direction.DESCENDING)
            .limit(4)  // 최근 4개의 검색어만 가져오기
            .get()
            .addOnSuccessListener { querySnapshot ->
                val searchList = mutableListOf<String>()
                val searchValues = mutableListOf<String>()

                for (document in querySnapshot) {
                    val searchName = document.getString("name")
                    searchName?.let {
                        searchList.add(it)
                        searchValues.add(it)  // 필요한 경우 값을 따로 처리
                    }
                }

                // 검색 기록이 있으면 SharedPreferences에 저장
                if (searchList.isNotEmpty()) {
                    saveToSharedPreferences(searchList, searchValues)
                }

            }.addOnFailureListener { exception ->
                Log.e("Firestore", "검색 기록을 가져오지 못했습니다.", exception)
            }
    }

    // 가져온 데이터를 SharedPreferences에 저장하는 메서드
    private fun saveToSharedPreferences(entries: List<String>, entryValues: List<String>) {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = sharedPreferences.edit()

        // 리스트를 Set<String>으로 변환하여 저장
        editor.putStringSet("recent_search_entries", entries.toSet())
        editor.putStringSet("recent_search_values", entryValues.toSet())
        editor.apply()
    }
}