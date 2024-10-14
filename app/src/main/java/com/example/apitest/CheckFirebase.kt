package com.example.apitest

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.apitest.databinding.ActivityCheckFirebaseBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CheckFirebase : AppCompatActivity() {
    private lateinit var binding: ActivityCheckFirebaseBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCheckFirebaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Firebase Database 초기화
        database = FirebaseDatabase.getInstance("https://subway-e1881-default-rtdb.firebaseio.com/").reference
        Log.d("Firebase 연결 확인", database.toString()) // Firebase 연결 확인 로그

        // "서울역" 데이터 검색
        searchSeoulStation()

    }

    private fun searchSeoulStation() {
        database.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val result = StringBuilder()
                var found = false

                // 각 자식 데이터에서 "출발역"이 "서울역"인지 확인
                for (data in snapshot.children) {
                    // "출발역" 데이터에 접근
                    val departureStation = data.child("출발역").value.toString()
                    Log.d("Firebase", "Firebase Database URL: ${database.toString()}")

                    if (departureStation == "서울역") {
                        found = true
                        result.append("출발역: $departureStation\n")
                        result.append("역번호: ${data.child("역번호").value}\n")
                        result.append("호선: ${data.child("호선").value}\n")
                        result.append("상하구분: ${data.child("상하구분").value}\n")
                        result.append("요일구분: ${data.child("요일구분").value}\n")
                        result.append("연번: ${data.child("연번").value}\n")
                        Log.d("Firebase", "Firebase Database URL: ${database.toString()}")

                        // 시간 데이터 추가
                        for (hour in data.children) {
                            if (hour.key != "출발역" && hour.key != "역번호" &&
                                hour.key != "호선" && hour.key != "상하구분" &&
                                hour.key != "요일구분" && hour.key != "연번") {
                                result.append("${hour.key}: ${hour.value}\n")
                            }
                        }
                        result.append("\n")
                        Log.d("Firebase", "Firebase Database URL: ${database.toString()}")

                    }
                }

                if (found) {
                    binding.tvResult.text = result.toString()  // 결과를 TextView에 출력

                } else {
                    Toast.makeText(applicationContext, "서울역 데이터를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("Firebase", "데이터 읽기 실패: ${error.message}")
                Toast.makeText(applicationContext, "데이터 읽기 실패: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
