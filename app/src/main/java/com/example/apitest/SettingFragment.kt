package com.example.apitest

import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

class SettingFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
        // SharedPreferences에서 최근 검색 기록을 불러와서 ListPreference에 설정
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        val recentSearchEntries = sharedPreferences.getStringSet("recent_search_entries", emptySet())
        val recentSearchValues = sharedPreferences.getStringSet("recent_search_values", emptySet())

        val listPreference = findPreference<ListPreference>("key_set_item")

        // 검색 기록이 있을 경우 ListPreference에 설정
        listPreference?.entries = recentSearchEntries?.toTypedArray()
        listPreference?.entryValues = recentSearchValues?.toTypedArray()

    }
}