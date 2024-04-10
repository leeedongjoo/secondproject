package com.example.apitest

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.apitest.databinding.ActivityMainBinding
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import javax.xml.parsers.DocumentBuilderFactory

var testex =""
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var a = "https://api.odcloud.kr/api/15071311/v1/uddi:e477f1d9-2c3a-4dc8-b147-a55584583fa2?page=1&perPage=10&returnType=XML&serviceKey=EDOPjH4oTjGZ6Bzr6ylwb8Rl3LFddWjT8SRbfK4MVLzclnUfD7HEx6%2FXAIshUMmoB%2B9Jc%2FFaZi8Lgb97nKRq%2Fg%3D%3D"
        binding.testT.text = ""

        //키값
        val key = "EDOPjH4oTjGZ6Bzr6ylwb8Rl3LFddWjT8SRbfK4MVLzclnUfD7HEx6%2FXAIshUMmoB%2B9Jc%2FFaZi8Lgb97nKRq%2Fg%3D%3D"
        //현재 페이지번호
        val pageNo = "&pageNo=1"
        // 한 페이지 결과 수
        val numOfRows ="&numOfRows=5"
        // AND(안드로이드)
        val MobileOS = "&MobileOS=AND"
        // 서비스명 = 어플명
        val MobileApp = "&MobileApp=APITest"
        // API 정보를 가지고 있는 주소
        val url = "https://api.odcloud.kr/api/15071311/v1/uddi:e477f1d9-2c3a-4dc8-b147-a55584583fa2?page=1&perPage=10&returnType=XML&serviceKey="+key
        // 버튼을 누르면 쓰레드 동작
        binding.testBT.setOnClickListener{
            // 쓰레드 생성
            val thread = Thread(NetworkThread(url))
            thread.start() // 쓰레드 시작
            thread.join() // 멀티 작업 안되게 하려면 start 후 join 입력

            // 쓰레드에서 가져온 api 정보 텍스트에 뿌려주기
            binding.testT.text = testex
        }
    }
}

class NetworkThread(var url: String): Runnable {
    override fun run() {

        try {

            val xml : Document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url)


            xml.documentElement.normalize()

            //찾고자 하는 데이터가 어느 노드 아래에 있는지 확인
            val list:NodeList = xml.getElementsByTagName("data")

            //list.length-1 만큼 얻고자 하는 태그의 정보를 가져온다
            for(i in 0..list.length-1){

                val n:Node = list.item(i)

                if(n.getNodeType() == Node.ELEMENT_NODE){

                    val elem = n as Element

                    val map = mutableMapOf<String,String>()


                    // 이부분은 어디에 쓰이는지 잘 모르겠다.
                    for(j in 0..elem.attributes.length - 1) {

                        map.putIfAbsent(elem.attributes.item(j).nodeName, elem.attributes.item(j).nodeValue)

                    }


                    println("=========${i+1}=========")
                    testex += "${i + 1} \n"

                    println("요일 : ${elem.getElementsByTagName("DAY OF WEEK DIVISION").item(0).textContent}")
                    testex += "요일 : ${elem.getElementsByTagName("DAY OF WEEK DIVISION").item(0).textContent} \n"

                    println("호선 : ${elem.getElementsByTagName("LINE NAME").item(0).textContent}")
                    testex += "호선 : ${elem.getElementsByTagName("LINE NAME").item(0).textContent} \n"

                    println("출발역: ${elem.getElementsByTagName("DEPARTURE STATION").item(0).textContent}")
                    testex += "출발역 : ${elem.getElementsByTagName("DEPARTURE STATION").item(0).textContent} \n"

                    println("상하구분 : ${elem.getElementsByTagName("DIVISION NAME").item(0).textContent}")
                    testex += "상하구분 : ${elem.getElementsByTagName("DIVISION NAME").item(0).textContent} \n"

                    println("8:30혼잡도 : ${elem.getElementsByTagName("CONGESTION FROM 8:00 TO 8:30").item(0).textContent}")
                    testex += "8:30혼잡도 : ${elem.getElementsByTagName("CONGESTION FROM 8:00 TO 8:30").item(0).textContent} \n"

                }
            }
        } catch (e: Exception) {
            Log.d("TTT", "오픈API"+e.toString())
        }
    }
}