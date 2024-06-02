package com.example.apitest

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.CheckBox
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.apitest.databinding.ActivitySubwayBinding
import kotlin.math.log

class SubwayActivity : AppCompatActivity() {
    private lateinit var imageViewOverlay1: ImageView
    private lateinit var imageViewOverlay2: ImageView
    private lateinit var imageViewOverlay3: ImageView
    private lateinit var imageViewOverlay4: ImageView
    private lateinit var imageViewOverlay5: ImageView
    private lateinit var imageViewOverlay6: ImageView
    private lateinit var imageViewOverlay7: ImageView
    private lateinit var imageViewOverlay8: ImageView
    private lateinit var imageViewOverlay9: ImageView

    private lateinit var checkBox1: CheckBox
    private lateinit var checkBox2: CheckBox
    private lateinit var checkBox3: CheckBox
    private lateinit var checkBox4: CheckBox
    private lateinit var checkBox5: CheckBox
    private lateinit var checkBox6: CheckBox
    private lateinit var checkBox7: CheckBox
    private lateinit var checkBox8: CheckBox
    private lateinit var checkBox9: CheckBox

    private var scaleGestureDetector: ScaleGestureDetector? = null
    private var scaleFactor = 1.0f
    private lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_subway)
        enableEdgeToEdge()
        var binding = ActivitySubwayBinding.inflate(layoutInflater)
        setContentView(binding.root)

    // ImageView 인스턴스 초기화
        imageViewOverlay1 = findViewById(R.id.img_subwayone)
        imageViewOverlay2 = findViewById(R.id.img_subwaytwo)
        imageViewOverlay3 = findViewById(R.id.img_subwaythree)
        imageViewOverlay4 = findViewById(R.id.img_subwayfour)
        imageViewOverlay5 = findViewById(R.id.img_subwayfive)
        imageViewOverlay6 = findViewById(R.id.img_subwaysix)
        imageViewOverlay7 = findViewById(R.id.img_subwayseven)
        imageViewOverlay8 = findViewById(R.id.img_subwayeight)
        imageViewOverlay9 = findViewById(R.id.img_subwaynine)

        // CheckBox 인스턴스 초기화
        checkBox1 = findViewById(R.id.checkBox1)
        checkBox2 = findViewById(R.id.checkBox2)
        checkBox3 = findViewById(R.id.checkBox3)
        checkBox4 = findViewById(R.id.checkBox4)
        checkBox5 = findViewById(R.id.checkBox5)
        checkBox6 = findViewById(R.id.checkBox6)
        checkBox7 = findViewById(R.id.checkBox7)
        checkBox8 = findViewById(R.id.checkBox8)
        checkBox9 = findViewById(R.id.checkBox9)

        // CheckBox 리스너 설정
        checkBox1.setOnCheckedChangeListener { _, isChecked ->
            updateImageVisibility(isChecked, imageViewOverlay1)
        }
        checkBox2.setOnCheckedChangeListener { _, isChecked ->
            updateImageVisibility(isChecked, imageViewOverlay2)
        }
        checkBox3.setOnCheckedChangeListener { _, isChecked ->
            updateImageVisibility(isChecked, imageViewOverlay3)
        }
        checkBox4.setOnCheckedChangeListener { _, isChecked ->
            updateImageVisibility(isChecked, imageViewOverlay4)
        }
        checkBox5.setOnCheckedChangeListener { _, isChecked ->
            updateImageVisibility(isChecked, imageViewOverlay5)
        }
        checkBox6.setOnCheckedChangeListener { _, isChecked ->
            updateImageVisibility(isChecked, imageViewOverlay6)
        }
        checkBox7.setOnCheckedChangeListener { _, isChecked ->
            updateImageVisibility(isChecked, imageViewOverlay7)
        }
        checkBox8.setOnCheckedChangeListener { _, isChecked ->
            updateImageVisibility(isChecked, imageViewOverlay8)
        }
        checkBox9.setOnCheckedChangeListener { _, isChecked ->
            updateImageVisibility(isChecked, imageViewOverlay9)
        }


        binding.menu1.setOnClickListener {
            var intent = Intent(this, SubwayAPI::class.java)
            startActivity(intent)
        }
        binding.menu2.setOnClickListener {
            var intenttt = Intent(this, TimeTest::class.java)
            startActivity(intenttt)
        }


    }

    private fun updateImageVisibility(isChecked: Boolean, imageView: ImageView) {
        imageView.visibility = if (isChecked) ImageView.VISIBLE else ImageView.GONE
    }
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return event?.let {
            scaleGestureDetector?.onTouchEvent(it) ?: false
        } ?: false
    }
    inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            scaleFactor *= detector.scaleFactor
            scaleFactor = scaleFactor.coerceIn(1.0f, 9.0f)
            imageView.scaleX = scaleFactor
            imageView.scaleY = scaleFactor
            return true
        }
    }
}