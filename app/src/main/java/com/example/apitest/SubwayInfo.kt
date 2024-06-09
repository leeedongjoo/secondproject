package com.example.apitest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.apitest.R
import com.example.apitest.SubwayAPI
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.example.apitest.databinding.SubwayInfoBinding

class SubwayInfo : Fragment() {
    private lateinit var binding: SubwayInfoBinding
    private lateinit var buttons: MutableList<Button> // MutableList로 변경

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
        binding = SubwayInfoBinding.inflate(inflater, container, false)
        val view = binding.root

        buttons = mutableListOf() // 버튼을 저장할 수정 가능한 리스트 생성

        for (i in 0..50) {
            val buttonId = resources.getIdentifier("button_$i", "id", requireActivity().packageName)
            val button = binding.root.findViewById<Button>(buttonId)
            buttons.add(button) // 수정 가능한 리스트에 버튼 추가

            // 각 버튼에 대한 텍스트 매핑
            buttonTextMap[button] = "텍스트 $i"

            // 각 버튼에 대한 클릭 이벤트 처리
            button.setOnClickListener {
                // 클릭한 버튼에 해당하는 텍스트 가져오기
                val buttonText = button.text.toString()
                // 바텀 시트에 텍스트 업데이트
                updateBottomSheetText(buttonText)
                // 바텀 시트 보이기
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

            }
        }

        // 바텀 시트와 관련된 요소 초기화
        initializeBottomSheet()

        return view
    }

    private fun initializeBottomSheet() {
        bottomSheetLayout = binding.root.findViewById(R.id.bottom_sheet_layout)
        bottomSheetExpandPersistentButton = binding.root.findViewById(R.id.page_1)
        bottomSheetHidePersistentButton = binding.root.findViewById(R.id.page_4)

        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetLayout)
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                // BottomSheetBehavior state에 따른 이벤트
                when (newState) {
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        Log.d("MainActivity", "state: hidden")
                    }

                    BottomSheetBehavior.STATE_EXPANDED -> {
                        Log.d("MainActivity", "state: expanded")
                    }

                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        Log.d("MainActivity", "state: collapsed")
                    }

                    BottomSheetBehavior.STATE_DRAGGING -> {
                        Log.d("MainActivity", "state: dragging")
                    }

                    BottomSheetBehavior.STATE_SETTLING -> {
                        Log.d("MainActivity", "state: settling")
                    }

                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                        Log.d("MainActivity", "state: half expanded")
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }
        })

        // BottomSheet와 관련된 이벤트 설정
        persistentBottomSheetEvent()
    }

    private fun persistentBottomSheetEvent() {
        bottomSheetExpandPersistentButton.setOnClickListener {
            // BottomSheet의 최대 높이만큼 보여주기
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
        bottomSheetHidePersistentButton.setOnClickListener {
            // BottomSheet 숨김
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        }
        // bottomSheetShowModalButton.setOnClickListener {
        // 추후 modal bottomSheet 띄울 버튼
        // }
    }

    // 바텀 시트의 텍스트 업데이트
    private fun updateBottomSheetText(text: String?) {
        // 바텀 시트의 텍스트 뷰를 찾아 텍스트 업데이트
        val textView = bottomSheetLayout.findViewById<TextView>(R.id.sheettxt)
        textView.text = text
    }

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

//제발 바인딩 써주세요 제발제제바어벶러젣ㅂㄱ러ㅐㅔㅈ
//        val button = binding.button
//        buttons = listOf(
//            view.findViewById(R.id.button),
//            view.findViewById(R.id.button_1),
//            view.findViewById(R.id.button_2),
//            view.findViewById(R.id.button_3),
//            view.findViewById(R.id.button_4),
//            view.findViewById(R.id.button_5),
//            view.findViewById(R.id.button_6),
//            view.findViewById(R.id.button_7),
//            view.findViewById(R.id.button_8),
//            view.findViewById(R.id.button_9),
//            view.findViewById(R.id.button_10),
//            view.findViewById(R.id.button_11),
//            view.findViewById(R.id.button_12),
//            view.findViewById(R.id.button_13),
//            view.findViewById(R.id.button_14),
//            view.findViewById(R.id.button_15),
//            view.findViewById(R.id.button_16),
//            view.findViewById(R.id.button_17),
//            view.findViewById(R.id.button_18),
//            view.findViewById(R.id.button_19),
//            view.findViewById(R.id.button_20),
//            view.findViewById(R.id.button_21),
//            view.findViewById(R.id.button_22),
//            view.findViewById(R.id.button_23),
//            view.findViewById(R.id.button_24),
//            view.findViewById(R.id.button_25),
//            view.findViewById(R.id.button_26),
//            view.findViewById(R.id.button_27),
//            view.findViewById(R.id.button_28),
//            view.findViewById(R.id.button_29),
//            view.findViewById(R.id.button_30),
//            view.findViewById(R.id.button_31),
//            view.findViewById(R.id.button_32),
//            view.findViewById(R.id.button_33),
//            view.findViewById(R.id.button_34),
//            view.findViewById(R.id.button_35),
//            view.findViewById(R.id.button_36),
//            view.findViewById(R.id.button_37),
//            view.findViewById(R.id.button_38),
//            view.findViewById(R.id.button_39),
//            view.findViewById(R.id.button_40),
//            view.findViewById(R.id.button_41),
//            view.findViewById(R.id.button_42),
//            view.findViewById(R.id.button_43),
//            view.findViewById(R.id.button_44),
//            view.findViewById(R.id.button_45),
//            view.findViewById(R.id.button_46),
//            view.findViewById(R.id.button_47),
//            view.findViewById(R.id.button_48),
//            view.findViewById(R.id.button_49),
//            view.findViewById(R.id.button_50),
//        )