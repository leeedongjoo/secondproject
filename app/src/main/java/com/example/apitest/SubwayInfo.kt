package com.example.apitest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.text.TextWatcher
import android.text.Editable
import com.example.apitest.R
import com.example.apitest.SubwayAPI
import androidx.appcompat.app.AppCompatActivity

import com.example.apitest.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.example.apitest.databinding.SubwayInfoBinding
import com.opencsv.CSVReader
import com.opencsv.exceptions.CsvException
import java.io.IOException
import java.io.InputStreamReader
//class SubwayInfo : Fragment() {
//    private lateinit var binding: SubwayInfoBinding
//    private lateinit var buttons: MutableList<Button> // MutableList로 변경
//
//    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
//    private lateinit var bottomSheetLayout: LinearLayout
//    private lateinit var bottomSheetExpandPersistentButton: Button
//    private lateinit var bottomSheetHidePersistentButton: Button
//
//    // 각 버튼에 대한 텍스트 매핑
//    private val buttonTextMap = mutableMapOf<Button, String>()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = SubwayInfoBinding.inflate(inflater, container, false)
//        val view = binding.root
//
//        buttons = mutableListOf() // 버튼을 저장할 수정 가능한 리스트 생성
//
//        for (i in 0..446) {
//            val buttonId = resources.getIdentifier("button_$i", "id", requireActivity().packageName)
//            val button = binding.root.findViewById<Button>(buttonId)
//            buttons.add(button) // 수정 가능한 리스트에 버튼 추가
//
//            // 각 버튼에 대한 텍스트 매핑
//            buttonTextMap[button] = "텍스트 $i"
//
//            // 각 버튼에 대한 클릭 이벤트 처리
//            button.setOnClickListener {
//                // 클릭한 버튼에 해당하는 텍스트 가져오기
//                val buttonText = button.text.toString()
//                // 바텀 시트에 텍스트 업데이트
//                updateBottomSheetText(buttonText)
//                // 바텀 시트 보이기
//                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
//
//            }
//        }
//
//        // 바텀 시트와 관련된 요소 초기화
//        initializeBottomSheet()
//
//        return view
//    }
//
//
//
//    private fun initializeBottomSheet() {
//        bottomSheetLayout = binding.root.findViewById(R.id.bottom_sheet_layout)
//        bottomSheetExpandPersistentButton = binding.root.findViewById(R.id.page_1)
//        bottomSheetHidePersistentButton = binding.root.findViewById(R.id.page_4)
//
//
//        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout)
//        bottomSheetBehavior.addBottomSheetCallback(object :
//            BottomSheetBehavior.BottomSheetCallback() {
//            override fun onStateChanged(bottomSheet: View, newState: Int) {
//                // BottomSheetBehavior state에 따른 이벤트
//                when (newState) {
//                    BottomSheetBehavior.STATE_HIDDEN -> {
//                        Log.d("MainActivity", "state: hidden")
//                    }
//
//                    BottomSheetBehavior.STATE_EXPANDED -> {
//                        Log.d("MainActivity", "state: expanded")
//                    }
//
//                    BottomSheetBehavior.STATE_COLLAPSED -> {
//                        Log.d("MainActivity", "state: collapsed")
//                    }
//
//                    BottomSheetBehavior.STATE_DRAGGING -> {
//                        Log.d("MainActivity", "state: dragging")
//                    }
//
//                    BottomSheetBehavior.STATE_SETTLING -> {
//                        Log.d("MainActivity", "state: settling")
//                    }
//
//                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
//                        Log.d("MainActivity", "state: half expanded")
//                    }
//                }
//            }
//
//            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//            }
//        })
//
//        // BottomSheet와 관련된 이벤트 설정
//        persistentBottomSheetEvent()
//    }
//
//
//    private fun persistentBottomSheetEvent() {
//        bottomSheetExpandPersistentButton.setOnClickListener {
//            // BottomSheet의 최대 높이만큼 보여주기
//            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
//        }
//        bottomSheetHidePersistentButton.setOnClickListener {
//            // BottomSheet 숨김
//            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
//        }
//        // bottomSheetShowModalButton.setOnClickListener {
//        // 추후 modal bottomSheet 띄울 버튼
//        // }
//    }
//
//    // 바텀 시트의 텍스트 업데이트
//    private fun updateBottomSheetText(text: String?) {
//        // 바텀 시트의 텍스트 뷰를 찾아 텍스트 업데이트
//        val textView = bottomSheetLayout.findViewById<TextView>(R.id.sheettxt)
//        textView.text = text
//    }
//
//    fun filterButtons(query: String) {
//        for (button in buttons) {
//            val buttonText = button.text.toString()
//            if (buttonText.contains(query, ignoreCase = true)) {
//                button.visibility = View.VISIBLE
//            } else {
//                button.visibility = View.GONE
//            }
//        }
//    }
//
//}
//
//class SubwayInfo : Fragment() {
//    private lateinit var binding: SubwayInfoBinding
//    private lateinit var buttons: MutableList<Button> // MutableList로 변경
//
//    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
//    private lateinit var bottomSheetLayout: LinearLayout
//    private lateinit var bottomSheetExpandPersistentButton: Button
//    private lateinit var bottomSheetHidePersistentButton: Button
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding = SubwayInfoBinding.inflate(inflater, container, false)
//        val view = binding.root
//
//        // 바텀 시트 레이아웃 초기화 (R.id.bottom_sheet_layout 존재 확인)
//        bottomSheetLayout = view.findViewById(R.id.bottom_sheet_layout)
//        bottomSheetExpandPersistentButton = view.findViewById(R.id.page_1)
//        bottomSheetHidePersistentButton = view.findViewById(R.id.page_4)
//
//        // R.id.page_2의 TextView 초기화 여부 확인
//        val textView = view.findViewById<TextView>(R.id.page_2)
//        if (textView == null) {
//            Log.e("SubwayInfo", "TextView with ID R.id.page_2 not found")
//        }
//
//        // 바텀 시트 초기화 및 이벤트 설정
//        initializeBottomSheet()
//
//        return view
//    }
//
//    private fun initializeBottomSheet() {
//        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout)
//        bottomSheetBehavior.addBottomSheetCallback(object :
//            BottomSheetBehavior.BottomSheetCallback() {
//            override fun onStateChanged(bottomSheet: View, newState: Int) {
//                // BottomSheetBehavior state에 따른 이벤트
//                when (newState) {
//                    BottomSheetBehavior.STATE_HIDDEN -> {
//                        Log.d("SubwayInfo", "state: hidden")
//                    }
//
//                    BottomSheetBehavior.STATE_EXPANDED -> {
//                        Log.d("SubwayInfo", "state: expanded")
//                    }
//
//                    BottomSheetBehavior.STATE_COLLAPSED -> {
//                        Log.d("SubwayInfo", "state: collapsed")
//                    }
//
//                    BottomSheetBehavior.STATE_DRAGGING -> {
//                        Log.d("SubwayInfo", "state: dragging")
//                    }
//
//                    BottomSheetBehavior.STATE_SETTLING -> {
//                        Log.d("SubwayInfo", "state: settling")
//                    }
//
//                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
//                        Log.d("SubwayInfo", "state: half expanded")
//                    }
//                }
//            }
//
//            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//                // 슬라이드 중인 상태 처리
//            }
//        })
//    }
//
//    fun updateBottomSheetText(text: String?) {
//        val textView = bottomSheetLayout.findViewById<TextView>(R.id.sheettxt)
//        textView.text = text
//    }
//
//    fun filterButtons(query: String) {
//        for (button in buttons) {
//            val buttonText = button.text.toString()
//            if (buttonText.contains(query, ignoreCase = true)) {
//                button.visibility = View.VISIBLE
//            } else {
//                button.visibility = View.GONE
//            }
//        }
//    }
//}
class SubwayInfo : Fragment() {
    private lateinit var binding: SubwayInfoBinding
    private lateinit var buttons: MutableList<Button> // MutableList로 변경하여 버튼을 저장할 리스트 선언

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var bottomSheetLayout: LinearLayout
    private lateinit var bottomSheetExpandPersistentButton: Button
    private lateinit var bottomSheetHidePersistentButton: Button

    // 각 버튼에 대한 텍스트 매핑
    private val buttonTextMap = mutableMapOf<Button, String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 레이아웃 바인딩 초기화
        binding = SubwayInfoBinding.inflate(inflater, container, false)
        val view = binding.root

        // buttons 리스트 초기화
        buttons = mutableListOf()

        // 버튼들을 리스트에 추가
        for (i in 0..446) {
            val buttonId = resources.getIdentifier("button_$i", "id", requireActivity().packageName)
            val button = binding.root.findViewById<Button>(buttonId)

            // 버튼이 존재할 경우에만 리스트에 추가
            button?.let {
                buttons.add(it)
                // 각 버튼에 대한 텍스트 매핑
                buttonTextMap[button] = "텍스트 $i"

                // 각 버튼에 대한 클릭 이벤트 처리
                button.setOnClickListener {
                    val buttonText = button.text.toString()
                    // 바텀 시트에 텍스트 업데이트
                    updateBottomSheetText(buttonText)
                    // 바텀 시트 보이기
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                }
            }
        }

        // 바텀 시트와 관련된 요소 초기화
        initializeBottomSheet()

        return view
    }

    private fun initializeBottomSheet() {
        // 바텀 시트 레이아웃 초기화
        bottomSheetLayout = binding.root.findViewById(R.id.bottom_sheet_layout)
        bottomSheetExpandPersistentButton = binding.root.findViewById(R.id.page_1)
        bottomSheetHidePersistentButton = binding.root.findViewById(R.id.page_4)

        // 바텀 시트 비헤이비어 설정
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout)
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        Log.d("SubwayInfo", "state: hidden")
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        Log.d("SubwayInfo", "state: expanded")
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        Log.d("SubwayInfo", "state: collapsed")
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                        Log.d("SubwayInfo", "state: dragging")
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                        Log.d("SubwayInfo", "state: settling")
                    }
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                        Log.d("SubwayInfo", "state: half expanded")
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                // 슬라이드 중인 상태를 처리할 수 있음
            }
        })

        // BottomSheet와 관련된 이벤트 설정
        persistentBottomSheetEvent()
    }

    private fun persistentBottomSheetEvent() {
        // BottomSheet의 최대 높이만큼 보여주기
        bottomSheetExpandPersistentButton.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        // BottomSheet 숨김
        bottomSheetHidePersistentButton.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }
    }

    // 바텀 시트의 텍스트 업데이트 메서드
    private fun updateBottomSheetText(text: String?) {
        val textView = bottomSheetLayout.findViewById<TextView>(R.id.sheettxt)
        textView.text = text
    }

    // 검색어를 필터링하는 메서드
    fun filterButtons(query: String) {
        for (button in buttons) {
            val buttonText = button.text.toString()
            if (buttonText.contains(query, ignoreCase = true)) {
                button.visibility = View.VISIBLE
            } else {
                button.visibility = View.GONE
            }
        }
    }
}
