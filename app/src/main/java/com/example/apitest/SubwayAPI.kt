package com.example.apitest

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.apitest.databinding.ActivityCheckFirebaseBinding
import com.example.apitest.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.opencsv.CSVReader
import com.opencsv.exceptions.CsvException
import java.io.IOException
import java.io.InputStreamReader

//class SubwayAPI : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding
//    private lateinit var subwayInfoFragment: SubwayInfo
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val etSearch = binding.etSearch
//        val btnSearch = binding.btnSearch
//        subwayInfoFragment = SubwayInfo()
//
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.frame_container, subwayInfoFragment)
//            .commit()
//
//        etSearch.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
//
//            override fun afterTextChanged(s: Editable?) {
//                val query = s.toString().trim()
//                subwayInfoFragment.filterButtons(query)
//            }
//        })
//
//        btnSearch.setOnClickListener {
//
//
//            val searchText = binding.etSearch.text.toString().trim()
//            try {
//                if (searchText.isNotEmpty()) {
//                    searchDepartureStation(searchText) // 출발역 검색 메서드 호출
//
//                } else {
//                    Toast.makeText(applicationContext, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
//                }
//            } catch (e: IOException) {
//                Toast.makeText(applicationContext, "데이터 로드 실패", Toast.LENGTH_SHORT).show()
//            } catch (e: CsvException) {
//                Toast.makeText(applicationContext, "CSV 파싱 오류", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    @Throws(IOException::class, CsvException::class)
//    private fun searchDepartureStation(departureStation: String) {
//        val assetManager = this.assets
//        val inputStream = assetManager.open("subway.csv")
//        val csvReader = CSVReader(InputStreamReader(inputStream, "EUC-KR"))
//        val allContent = csvReader.readAll()
//
//        var found = false
//        for (row in allContent.drop(1)) {
//            if (row[4].equals(departureStation, ignoreCase = true)) {
//                var textchange=binding.root.findViewById<TextView>(R.id.page_2)
//                textchange.text = "시간당 밀집도: ${row[6]}%"
//                found = true
//                break
//            }
//        }
//
//        if (!found) {
//            Toast.makeText(applicationContext, "정확한 역이름을 검색해주세요.", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//}
//
//class SubwayAPI : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding
//    private lateinit var subwayInfoFragment: SubwayInfo
//    private lateinit var database: DatabaseReference
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        // Firebase Database 초기화
//        database = FirebaseDatabase.getInstance("https://subway-e1881-default-rtdb.firebaseio.com/").reference
//
//        val etSearch = binding.etSearch
//        val btnSearch = binding.btnSearch
//        subwayInfoFragment = SubwayInfo()
//
//        // 프래그먼트 추가
//        supportFragmentManager.beginTransaction()
//            .replace(R.id.frame_container, subwayInfoFragment)
//            .commit()
//
//        // 검색어 입력 감지 및 프래그먼트 필터링
//        etSearch.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
//            override fun afterTextChanged(s: Editable?) {
//                val query = s.toString().trim()
//                subwayInfoFragment.filterButtons(query)
//            }
//        })
//
//        // 검색 버튼 클릭 시 Firebase에서 출발역 검색
//        btnSearch.setOnClickListener {
//            val searchText = etSearch.text.toString().trim()
//
//            if (searchText.isNotEmpty()) {
//                searchDepartureStation(searchText)  // 출발역 검색 메서드 호출
//            } else {
//                Toast.makeText(applicationContext, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//    // Firebase에서 출발역을 검색하는 메서드
//    private fun searchDepartureStation(departureStation: String) {
//        database.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                var found = false
//
//                // 각 자식 데이터에서 "출발역"이 사용자가 입력한 값인지 확인
//                for (data in snapshot.children) {
//                    val firebaseDepartureStation = data.child("출발역").value?.toString()
//
//                    if (firebaseDepartureStation.equals(departureStation, ignoreCase = true)) {
//                        found = true
//
//                        // TextView에 데이터를 출력 (기존 로직 유지)
//                        val textchange = binding.root.findViewById<TextView>(R.id.page_2)
//                        val congestion = data.child("12:00").value?.toString() ?: "N/A"  // 예시로 12:00 밀집도 가져오기
//                        textchange.text = "시간당 밀집도: $congestion%"
//
//                        break  // 첫 번째 일치하는 데이터를 찾으면 중단
//                    }
//                }
//
//                if (!found) {
//                    Toast.makeText(applicationContext, "정확한 역이름을 검색해주세요.", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(applicationContext, "데이터 읽기 실패: ${error.message}", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
//}
class SubwayAPI : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var subwayInfoFragment: SubwayInfo
    private lateinit var database: DatabaseReference
    private lateinit var firestore: FirebaseFirestore  // Firestore 인스턴스 선언

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Firebase Database 초기화
        database = FirebaseDatabase.getInstance("https://subway-e1881-default-rtdb.firebaseio.com/").reference
        firestore = FirebaseFirestore.getInstance()
        val etSearch = binding.etSearch
        val btnSearch = binding.btnSearch
        subwayInfoFragment = SubwayInfo()

        // 프래그먼트 추가
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_container, subwayInfoFragment)
            .commit()

        // 검색어 입력 감지 및 프래그먼트 필터링
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().trim()
                Log.d("SubwayAPI", "AfterTextChanged - 검색어: $query")
                subwayInfoFragment.filterButtons(query)
            }
        })

        // 검색 버튼 클릭 시 Firebase에서 출발역 검색
        btnSearch.setOnClickListener {
            val searchText = etSearch.text.toString().trim()
            Log.d("SubwayAPI", "ButtonClick - 검색어: $searchText")

            if (searchText.isNotEmpty()) {
                // Firestore에 검색 기록 저장
                saveSearchToFirestore(searchText)
                searchDepartureStation(searchText)  // 출발역 검색 메서드 호출
            } else {
                Toast.makeText(applicationContext, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    // Firestore에 검색 기록을 저장하는 메서드
    private fun saveSearchToFirestore(searchText: String) {
        val searchData = hashMapOf(
            "name" to searchText,
            "timestamp" to System.currentTimeMillis()  // 검색 시간을 함께 저장
        )

        firestore.collection("searchRecent")  // "searchRecent" 컬렉션에 저장
            .add(searchData)
            .addOnSuccessListener { Log.d("Firestore", "검색 기록 저장 성공: $searchText") }
            .addOnFailureListener { e -> Log.e("Firestore", "검색 기록 저장 실패", e) }
    }

    // Firebase에서 출발역을 검색하는 메서드
    private fun searchDepartureStation(departureStation: String) {
        Log.d("SubwayAPI", "searchDepartureStation 호출 - 검색어: $departureStation")

        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var found = false

                // 각 자식 데이터에서 "출발역"이 사용자가 입력한 값인지 확인
                for (data in snapshot.children) {
                    val firebaseDepartureStation = data.child("출발역").value?.toString()
                    Log.d("SubwayAPI", "Firebase 데이터 확인 - 출발역: $firebaseDepartureStation")

                    if (firebaseDepartureStation.equals(departureStation, ignoreCase = true)) {
                        found = true


                        // TextView에 데이터를 출력
                        val textchange = binding.root.findViewById<TextView>(R.id.page_2)
                        val namechange = binding.root.findViewById<TextView>(R.id.sheettxt)
                        val congestion = data.child("12:00").value?.toString() ?: "N/A"  // 예시로 12:00 밀집도 가져오기
                        textchange.text = "시간당 밀집도: $congestion%"
                        namechange.text = "$departureStation"
                        Log.d("SubwayAPI", "검색 성공 - 검색어: $departureStation, 밀집도: $congestion")

                        break  // 첫 번째 일치하는 데이터를 찾으면 중단
                    }
                }

                if (!found) {
                    Log.d("SubwayAPI", "검색 실패 - 검색어: $departureStation")
                    Toast.makeText(applicationContext, "정확한 역이름을 검색해주세요.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("SubwayAPI", "Firebase 데이터베이스 오류: ${error.message}")
                Toast.makeText(applicationContext, "데이터 읽기 실패: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
